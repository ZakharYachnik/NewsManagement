package by.yachnikzakhar.newsmanagement.service.impl;

import by.yachnikzakhar.newsmanagement.beans.News;
import by.yachnikzakhar.newsmanagement.dao.exceptions.DAOException;
import by.yachnikzakhar.newsmanagement.dao.DAOProvider;
import by.yachnikzakhar.newsmanagement.dao.NewsDAO;
import by.yachnikzakhar.newsmanagement.dao.exceptions.NewsNotFoundException;
import by.yachnikzakhar.newsmanagement.service.NewsService;
import by.yachnikzakhar.newsmanagement.service.ServiceException;

import java.time.LocalDate;
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
        catch (DAOException | NewsNotFoundException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public List<News> getAll() throws ServiceException {
        try{
            return newsDAO.getAll();
        }
        catch (DAOException | NewsNotFoundException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public void update(News news) throws ServiceException {
        try{
            newsDAO.update(news);
        }
        catch (DAOException exception){
            throw new ServiceException(exception);
        }
    }

    @Override
    public void blockById(int id) throws ServiceException {
        try{
            newsDAO.block(id);
        }
        catch (DAOException exception){
            throw new ServiceException(exception);
        }
    }

    @Override
    public List<News> getInRange(int startId, int endId) throws ServiceException {
        try {
            return newsDAO.getInRange(startId, endId);
        } catch (NewsNotFoundException | DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> getFromEnd(int count) throws ServiceException {
        try {
            return newsDAO.getFromEnd(count);
        } catch (NewsNotFoundException | DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> findByPublicationDate(LocalDate date) throws ServiceException {
        try {
            return newsDAO.findByPublicationDate(date);
        } catch (NewsNotFoundException | DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> findByTitle(String title) throws ServiceException {
        try {
            return newsDAO.findByTitle(title);
        } catch (NewsNotFoundException | DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> findByUserId(int userId) throws ServiceException {
        try {
            return newsDAO.findByUserId(userId);
        } catch (NewsNotFoundException | DAOException e) {
            throw new ServiceException(e);
        }
    }
}
