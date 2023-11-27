package by.yachnikzakhar.newsmanagement.service;

import by.yachnikzakhar.newsmanagement.beans.User;

import java.util.List;

public interface UserService {
    void add(User user) throws ServiceException;
    User getById(int id) throws ServiceException;
    List<User> getAll() throws ServiceException;
    List<User> getInRange(int startId, int endId) throws ServiceException;
    List<User> getFromEnd(int count) throws ServiceException;
    User getByRoleId(int roleId) throws ServiceException;
    User getByLogin(String login) throws ServiceException;
    void update(User user) throws ServiceException;
    void remove(User user) throws ServiceException;

    User authentication(String login, String password) throws ServiceException;
}
