package by.yachnikzakhar.newsmanagement.dao.impl;

import by.yachnikzakhar.newsmanagement.beans.User;
import by.yachnikzakhar.newsmanagement.dao.exceptions.DAOException;
import by.yachnikzakhar.newsmanagement.dao.UserDAO;
import by.yachnikzakhar.newsmanagement.dao.exceptions.UserNotFoundException;
import by.yachnikzakhar.newsmanagement.service.ServiceException;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_LOGIN = "login";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_FULL_NAME = "full_name";
    private static final String COLUMN_PHONE_NUMBER = "phone_number";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_STATUS = "status";
    private static final String STATUS_BLOCKED = "BLOCKED";

    private static final String INSERT_USER_QUERY = "insert into users(login, password, full_name, phone_number, email, status) values(?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_LOGIN_QUERY = "select * from users where login=?";
    private static final String SELECT_USER_BY_ID_QUERY = "select * from users where id=?";
    private static final String SELECT_ALL_USERS_QUERY = "select * from users";
    private static final String UPDATE_USER_QUERY = "update users set login=?, password=?, full_name=?, phone_number=?, email=?, status=? where id=?";
    private static final String BLOCK_USER_QUERY = "update users set status=? where id=?";

    @Override
    public void add(User user) throws DAOException {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_QUERY); )
        {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getStatus());

            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new DAOException("User registration error in the system");
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("User registration process error", e);
        }
    }

    @Override
    public User authentication(String login, String password) throws DAOException, UserNotFoundException {
        User user = getByLogin(login);

        if(!BCrypt.checkpw(password, user.getPassword())){
            throw new DAOException("Wrong password");
        }

        if(user.getStatus().equalsIgnoreCase(STATUS_BLOCKED)){
            throw new UserNotFoundException("The user is blocked");
        }
        return user;
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
    public User getByLogin(String login) throws UserNotFoundException, DAOException{
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN_QUERY);
            ){
            preparedStatement.setString(1, login);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(!resultSet.next()){
                    throw new UserNotFoundException("The user with this login was not found");
                }

                return new User(
                        resultSet.getInt(COLUMN_ID),
                        resultSet.getString(COLUMN_LOGIN),
                        resultSet.getString(COLUMN_PASSWORD),
                        resultSet.getString(COLUMN_FULL_NAME),
                        resultSet.getString(COLUMN_PHONE_NUMBER),
                        resultSet.getString(COLUMN_EMAIL),
                        resultSet.getString(COLUMN_STATUS)
                );
            }
        }catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public User getById(int id) throws UserNotFoundException, DAOException {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID_QUERY);
        ){
            preparedStatement.setInt(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(!resultSet.next()){
                    throw new UserNotFoundException("The user with this id was not found");
                }

                return new User(
                        resultSet.getInt(COLUMN_ID),
                        resultSet.getString(COLUMN_LOGIN),
                        resultSet.getString(COLUMN_PASSWORD),
                        resultSet.getString(COLUMN_FULL_NAME),
                        resultSet.getString(COLUMN_PHONE_NUMBER),
                        resultSet.getString(COLUMN_EMAIL),
                        resultSet.getString(COLUMN_STATUS)
                );
            }
        }catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<User> getAll() throws DAOException {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS_QUERY);
        ){

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                List<User> userList = new ArrayList<>();
                while(resultSet.next()){
                    userList.add(new User(
                            resultSet.getInt(COLUMN_ID),
                            resultSet.getString(COLUMN_LOGIN),
                            resultSet.getString(COLUMN_PASSWORD),
                            resultSet.getString(COLUMN_FULL_NAME),
                            resultSet.getString(COLUMN_PHONE_NUMBER),
                            resultSet.getString(COLUMN_EMAIL),
                            resultSet.getString(COLUMN_STATUS)
                    ));
                }
                return userList;
            }
        }catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(User user) throws DAOException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_QUERY)) {
            
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getStatus());
            preparedStatement.setInt(7, user.getId());

            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new DAOException("User update error in the system");
            }
        } catch (SQLException e) {
            throw new DAOException("User update process error", e);
        }
    }

    @Override
    public void block(User user) throws DAOException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(BLOCK_USER_QUERY)) {

            preparedStatement.setString(1, STATUS_BLOCKED);
            preparedStatement.setInt(2, user.getId());

            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new DAOException("User block error in the system");
            }
        } catch (SQLException e) {
            throw new DAOException("User block process error", e);
        }
    }
}
