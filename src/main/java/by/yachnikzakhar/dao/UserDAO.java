package by.yachnikzakhar.dao;

import by.yachnikzakhar.beans.User;

import java.util.List;

public interface UserDAO {
    void add(User user) throws DAOException;
    User getById(int id) throws DAOException;
    List<User> getAll() throws DAOException;
    List<User> getInRange(int startId, int endId) throws DAOException;
    List<User> getFromEnd(int count) throws DAOException;
    User getByRoleId(int roleId) throws DAOException;
    User getByLogin(String login) throws DAOException;
    void update(User user) throws DAOException;
    void remove(User user) throws DAOException;

}
