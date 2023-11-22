package by.yachnikzakhar.service.impl;

import by.yachnikzakhar.beans.News;
import by.yachnikzakhar.dao.DAOException;
import by.yachnikzakhar.dao.DAOProvider;
import by.yachnikzakhar.dao.NewsDAO;
import by.yachnikzakhar.service.NewsService;
import by.yachnikzakhar.service.ServiceException;

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
