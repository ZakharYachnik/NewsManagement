package by.yachnikzakhar.newsmanagement.dao;

import by.yachnikzakhar.newsmanagement.beans.News;
import by.yachnikzakhar.newsmanagement.dao.exceptions.DAOException;
import by.yachnikzakhar.newsmanagement.dao.exceptions.NewsNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface NewsDAO {
    void add(News news) throws DAOException;
    News getById(int id) throws DAOException, NewsNotFoundException;
    List<News> getAll() throws DAOException, NewsNotFoundException;
    List<News> getInRange(int startId, int endId) throws NewsNotFoundException, DAOException;
    List<News> getFromEnd(int count) throws NewsNotFoundException, DAOException;
    List<News> findByPublicationDate(LocalDate date) throws DAOException, NewsNotFoundException;
    List<News> findByTitle(String titile) throws DAOException, NewsNotFoundException;

    List<News> findByUserId(int userId) throws DAOException, NewsNotFoundException;
    void update(News news) throws DAOException;
    void block(int id) throws DAOException;
}
