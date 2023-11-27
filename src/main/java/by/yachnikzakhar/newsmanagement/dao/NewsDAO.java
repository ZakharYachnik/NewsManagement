package by.yachnikzakhar.newsmanagement.dao;

import by.yachnikzakhar.newsmanagement.beans.News;
import by.yachnikzakhar.newsmanagement.dao.exceptions.DAOException;

import java.util.List;

public interface NewsDAO {
    void add(News news) throws DAOException;
    News getById(int id) throws DAOException;
    List<News> getAll() throws DAOException;
    List<News> getInRange(int startId, int endId) throws DAOException;
    List<News> getFromEnd(int count) throws DAOException;
    List<News> findByDate(String date) throws DAOException;
    List<News> findByTitle(String date) throws DAOException;
    void update(News news) throws DAOException;
    void remove(News news) throws DAOException;
}
