package by.yachnikzakhar.newsmanagement.dao;

import by.yachnikzakhar.newsmanagement.beans.User;
import by.yachnikzakhar.newsmanagement.dao.exceptions.DAOException;
import by.yachnikzakhar.newsmanagement.dao.exceptions.UserNotFoundException;

import java.util.List;

public interface UserDAO {
    void add(User user) throws DAOException;
    User getById(int id) throws DAOException, UserNotFoundException;
    List<User> getAll() throws DAOException;
    List<User> getInRange(int startId, int endId) throws DAOException;
    List<User> getFromEnd(int count) throws DAOException;
    User getByLogin(String login) throws DAOException, UserNotFoundException;
    void update(User user) throws DAOException;
    void block(User user) throws DAOException;

    User authentication(String login, String password) throws DAOException, UserNotFoundException;
}
