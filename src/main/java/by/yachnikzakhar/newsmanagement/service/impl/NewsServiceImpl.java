package by.yachnikzakhar.newsmanagement.service.impl;

import by.yachnikzakhar.newsmanagement.beans.News;
import by.yachnikzakhar.newsmanagement.dao.exceptions.DAOException;
import by.yachnikzakhar.newsmanagement.dao.DAOProvider;
import by.yachnikzakhar.newsmanagement.dao.NewsDAO;
import by.yachnikzakhar.newsmanagement.service.NewsService;
import by.yachnikzakhar.newsmanagement.service.ServiceException;

import java.util.List;

public class NewsServiceImpl implements NewsService {
    private final NewsDAO newsDAO = DAOProvider.getInstance().getNewsDAO();
    @Override
    public void add(News user) throws ServiceException {
        try{
            newsDAO.add(user);
        }
        catch (DAOException exception){
            throw new ServiceException(exception);
        }
    }

    @Override
    public News getById(int id) throws ServiceException {
        try{
            return newsDAO.getById(id);
        }
        catch (DAOException exception){
            throw new ServiceException(exception);
        }
    }

    @Override
    public List<News> getAll() throws ServiceException {
        try{
            return newsDAO.getAll();
        }
        catch (DAOException exception){
            throw new ServiceException(exception);
        }
    }

    @Override
    public void update(News user) throws ServiceException {
        try{
            newsDAO.update(user);
        }
        catch (DAOException exception){
            throw new ServiceException(exception);
        }
    }

    @Override
    public void remove(News user) throws ServiceException {
        try{
            newsDAO.remove(user);
        }
        catch (DAOException exception){
            throw new ServiceException(exception);
        }
    }
}
