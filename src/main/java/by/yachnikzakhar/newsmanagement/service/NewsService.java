package by.yachnikzakhar.newsmanagement.service;

import by.yachnikzakhar.newsmanagement.beans.News;
import by.yachnikzakhar.newsmanagement.dao.exceptions.DAOException;
import by.yachnikzakhar.newsmanagement.dao.exceptions.NewsNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface NewsService {
    void add(News news) throws ServiceException;
    News getById(int id) throws ServiceException;
    List<News> getAll() throws ServiceException;
    List<News> getInRange(int startId, int endId) throws ServiceException;
    List<News> getFromEnd(int count) throws ServiceException;
    List<News> findByPublicationDate(LocalDate date) throws ServiceException;
    List<News> findByTitle(String title) throws ServiceException;
    List<News> findByUserId(int userId) throws ServiceException;
    void update(News news) throws ServiceException;
    void blockById(int id) throws ServiceException;
}
