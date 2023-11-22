package by.yachnikzakhar.dao.impl;

import by.yachnikzakhar.beans.News;
import by.yachnikzakhar.dao.DAOException;
import by.yachnikzakhar.dao.NewsDAO;
import by.yachnikzakhar.resource.NewsList;

import java.util.List;

public class NewsDAOImpl implements NewsDAO {
    @Override
    public void add(News news) throws DAOException {

    }

    @Override
    public List<News> getInRange(int startId, int endId) throws DAOException {
        return null;
    }

    @Override
    public List<News> getFromEnd(int count) throws DAOException {
        return null;
    }

    @Override
    public List<News> findByDate(String date) throws DAOException {
        return null;
    }

    @Override
    public List<News> findByTitle(String date) throws DAOException {
        return null;
    }

    @Override

    public News getById(int id) throws DAOException {
        return NewsList.getInstance().getById(id);
    }

    @Override
    public List<News> getAll() throws DAOException {
        return null;
    }

    @Override
    public void update(News news) throws DAOException {
        System.out.println("Запись id: " + news.getId() + " изменена в бд");
    }

    @Override
    public void remove(News news) throws DAOException {
        System.out.println("Запись id: " + news.getId() + " удалена из бд");
    }
}
