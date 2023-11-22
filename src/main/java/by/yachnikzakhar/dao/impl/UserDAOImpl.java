package by.yachnikzakhar.dao.impl;

import by.yachnikzakhar.beans.User;
import by.yachnikzakhar.dao.ConnectionFactory;
import by.yachnikzakhar.dao.DAOException;
import by.yachnikzakhar.dao.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public void add(User user) throws DAOException {

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users(login, password, full_name, phone_number, email, roles_id) values(?, ?, ?, ?, ?, ?)"); )
        {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setInt(6, user.getRoleId());

            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new DAOException("Не удалось добавить пользователя");
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Ошибка в базе данных", e);
        }
    }

    @Override
    public List<User> getInRange(int startId, int endId) throws DAOException {
        return null;
    }

    @Override
    public List<User> getFromEnd(int count) throws DAOException {
        return null;
    }

    @Override
    public User getByRoleId(int roleId) throws DAOException{
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where roles_id=?");
        ){
            preparedStatement.setInt(1, roleId);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return new User(
                            resultSet.getInt("id"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            resultSet.getString("full_name"),
                            resultSet.getString("phone_number"),
                            resultSet.getString("email"),
                            resultSet.getInt("roles_id")
                    );
                }
                else{
                    return null;
                }
            }
        }catch (SQLException e) {
            throw new DAOException();
        }
    }

    @Override
    public User getByLogin(String login) throws DAOException{
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where login=?");
            ){
            preparedStatement.setString(1, login);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return new User(
                            resultSet.getInt("id"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            resultSet.getString("full_name"),
                            resultSet.getString("phone_number"),
                            resultSet.getString("email"),
                            resultSet.getInt("roles_id")
                    );
                }
                else{
                    return null;
                }
            }
        }catch (SQLException e) {
            throw new DAOException();
        }
    }

    @Override
    public User getById(int id) throws DAOException {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id=?");
        ){
            preparedStatement.setInt(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return new User(
                            resultSet.getInt("id"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            resultSet.getString("full_name"),
                            resultSet.getString("phone_number"),
                            resultSet.getString("email"),
                            resultSet.getInt("roles_id")
                    );
                }
                else{
                    return null;
                }
            }
        }catch (SQLException e) {
            throw new DAOException();
        }
    }

    @Override
    public List<User> getAll() throws DAOException {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users");
        ){

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                List<User> userList = new ArrayList<>();
                while(resultSet.next()){
                    userList.add(new User(
                            resultSet.getInt("id"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            resultSet.getString("full_name"),
                            resultSet.getString("phone_number"),
                            resultSet.getString("email"),
                            resultSet.getInt("roles_id")
                    ));
                }
                return userList;
            }
        }catch (SQLException e) {
            throw new DAOException();
        }
    }

    @Override
    public void update(User user) throws DAOException {

    }

    @Override
    public void remove(User user) throws DAOException {

    }
}
