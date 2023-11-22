package by.yachnikzakhar.service;

import by.yachnikzakhar.beans.News;

import java.util.List;

public interface NewsService {
    void add(News news) throws ServiceException;
    News getById(int id) throws ServiceException;
    List<News> getAll() throws ServiceException;
    void update(News news) throws ServiceException;
    void remove(News news) throws ServiceException;
}
