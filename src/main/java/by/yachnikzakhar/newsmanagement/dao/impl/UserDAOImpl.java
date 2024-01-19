package by.yachnikzakhar.newsmanagement.dao.impl;

import by.yachnikzakhar.newsmanagement.beans.Roles;
import by.yachnikzakhar.newsmanagement.beans.User;
import by.yachnikzakhar.newsmanagement.dao.connection.ConnectionPool;
import by.yachnikzakhar.newsmanagement.dao.connection.ConnectionPoolException;
import by.yachnikzakhar.newsmanagement.dao.exceptions.DAOException;
import by.yachnikzakhar.newsmanagement.dao.UserDAO;
import by.yachnikzakhar.newsmanagement.dao.exceptions.UserNotFoundException;
import by.yachnikzakhar.newsmanagement.service.ServiceException;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
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
    private static final String SELECT_USER_ROLES_QUERY = "select r.role from roles r join users_has_roles ur on r.id = ur.roles_id join users u on u.id = ur.users_id where u.id = ?";
    private static final String INSERT_USER_ROLE_QUERY = "insert into users_has_roles (users_id, roles_id) values(?, ?)";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void add(User user) throws DAOException {
        Connection connection = null;
        int generatedId;

        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
                preparedStatement.setString(3, user.getFullName());
                preparedStatement.setString(4, user.getPhoneNumber());
                preparedStatement.setString(5, user.getEmail());
                preparedStatement.setString(6, user.getStatus());

                int result = preparedStatement.executeUpdate();

                if (result == 0) {
                    throw new DAOException("User registration error in the system");
                }

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                generatedKeys.next();
                generatedId = generatedKeys.getInt(1);
                user.setId(generatedId);
                generatedKeys.close();
            }

            insertUserRoles(generatedId, user.getRoles(), connection);

            connection.commit();
        } catch (SQLException | ConnectionPoolException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    throw new DAOException("Transaction rollback error", e);
                }
            }
            throw new DAOException("User registration process error", e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void insertUserRoles(int userId, List<String> roles, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_ROLE_QUERY)) {

            for (String role : roles) {
                preparedStatement.setInt(1, userId);
                preparedStatement.setInt(2, Roles.valueOf(role).ordinal() + 1);
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new SQLException("User roles insert error", e);
        }
    }

    @Override
    public User authentication(String login, String password) throws DAOException, UserNotFoundException {
        User user = getByLogin(login);

        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new DAOException("Wrong password");
        }

        if (user.getStatus().equalsIgnoreCase(STATUS_BLOCKED)) {
            throw new UserNotFoundException("The user is blocked");
        }

        user.setRoles(getUserRolesById(user.getId()));
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
    public User getByLogin(String login) throws UserNotFoundException, DAOException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN_QUERY);
        ) {
            preparedStatement.setString(1, login);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
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
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public User getById(int id) throws UserNotFoundException, DAOException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID_QUERY);
        ) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
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
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<User> getAll() throws DAOException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS_QUERY);
        ) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<User> userList = new ArrayList<>();
                while (resultSet.next()) {
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
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(User user) throws DAOException {
        try (Connection connection = connectionPool.takeConnection();
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
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("User update process error", e);
        }
    }

    @Override
    public void block(User user) throws DAOException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(BLOCK_USER_QUERY)) {

            preparedStatement.setString(1, STATUS_BLOCKED);
            preparedStatement.setInt(2, user.getId());

            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new DAOException("User block error in the system");
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("User block process error", e);
        }
    }


    @Override
    public List<String> getUserRolesById(int userId) throws DAOException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_ROLES_QUERY)) {

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new DAOException("User roles not found");
            }

            List<String> roles = new ArrayList<>();

            do {
                roles.add(resultSet.getString(1));
            } while (resultSet.next());

            return roles;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }
}
