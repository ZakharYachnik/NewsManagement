package by.yachnikzakhar.newsmanagement.service.impl;

import by.yachnikzakhar.newsmanagement.beans.User;
import by.yachnikzakhar.newsmanagement.dao.exceptions.DAOException;
import by.yachnikzakhar.newsmanagement.dao.DAOProvider;
import by.yachnikzakhar.newsmanagement.dao.UserDAO;
import by.yachnikzakhar.newsmanagement.dao.exceptions.UserNotFoundException;
import by.yachnikzakhar.newsmanagement.service.ServiceException;
import by.yachnikzakhar.newsmanagement.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO = DAOProvider.getInstance().getUserDAO();

    @Override
    public void add(User user) throws ServiceException {
        try {
            userDAO.getByLogin(user.getLogin());
            throw new ServiceException("User with this login already exists");
        } catch (UserNotFoundException e) {
            try {
                userDAO.add(user);
            } catch (DAOException exception) {
                throw new ServiceException(exception);
            }
        }catch (DAOException exception) {
            throw new ServiceException(exception);
        }

    }

    @Override
    public User getById(int id) throws ServiceException {
        try {
            return userDAO.getById(id);
        } catch (DAOException | UserNotFoundException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public List<User> getAll() throws ServiceException {
        try {
            return userDAO.getAll();
        } catch (DAOException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public void update(User user) throws ServiceException {
        try {
            userDAO.update(user);
        } catch (DAOException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public void remove(User user) throws ServiceException {
        try {
            userDAO.block(user);
        } catch (DAOException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public User authentication(String login, String password) throws ServiceException {
        try {
            return userDAO.authentication(login, password);
        } catch (DAOException | UserNotFoundException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public List<User> getInRange(int startId, int endId) throws ServiceException {
        return null;
    }

    @Override
    public List<User> getFromEnd(int count) throws ServiceException {
        return null;
    }

    @Override
    public User getByRoleId(int roleId) throws ServiceException {
        return null;
    }

    @Override
    public User getByLogin(String login) throws ServiceException {
        try {
            return userDAO.getByLogin(login);
        } catch (DAOException | UserNotFoundException e) {
            throw new ServiceException(e);
        }
    }
}
