package by.yachnikzakhar.service.impl;

import by.yachnikzakhar.beans.User;
import by.yachnikzakhar.dao.DAOException;
import by.yachnikzakhar.dao.DAOProvider;
import by.yachnikzakhar.dao.UserDAO;
import by.yachnikzakhar.service.ServiceException;
import by.yachnikzakhar.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
    @Override
    public void add(User user) throws ServiceException {
        try{
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userDAO.add(user);
        }
        catch (DAOException exception){
            throw new ServiceException(exception);
        }
    }

    @Override
    public User getById(int id) throws ServiceException {
        try{
            return userDAO.getById(id);
        }
        catch (DAOException exception){
            throw new ServiceException(exception);
        }
    }

    @Override
    public List<User> getAll() throws ServiceException {
        try{
            return userDAO.getAll();
        }
        catch (DAOException exception){
            throw new ServiceException(exception);
        }
    }

    @Override
    public void update(User user) throws ServiceException {
        try{
            userDAO.update(user);
        }
        catch (DAOException exception){
            throw new ServiceException(exception);
        }
    }

    @Override
    public void remove(User user) throws ServiceException {
        try{
            userDAO.remove(user);
        }
        catch (DAOException exception){
            throw new ServiceException(exception);
        }
    }

    @Override
    public User authentication(String login, String password) throws ServiceException {
        try{
            User user = userDAO.getByLogin(login);
            if(user == null){
                throw new ServiceException("Пользователь не найден");
            }

            if(BCrypt.checkpw(password, user.getPassword())){
                return user;
            }else {
                throw new ServiceException("Неверный пароль!");
            }
        }
        catch (DAOException exception){
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
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
