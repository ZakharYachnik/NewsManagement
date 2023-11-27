package by.yachnikzakhar.newsmanagement.dao;

import by.yachnikzakhar.newsmanagement.dao.impl.NewsDAOImpl;
import by.yachnikzakhar.newsmanagement.dao.impl.UserDAOImpl;

public class DAOProvider {
    private static DAOProvider provider;

    private NewsDAO newsDAO = new NewsDAOImpl();
    private UserDAO userDAO = new UserDAOImpl();

    private DAOProvider(){
    }
    public static DAOProvider getInstance(){
        if(provider == null){
            provider = new DAOProvider();
        }
        return provider;
    }

    public NewsDAO getNewsDAO() {
        return newsDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
