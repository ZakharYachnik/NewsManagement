package by.yachnikzakhar.newsmanagement.dao.impl;

import by.yachnikzakhar.newsmanagement.beans.News;
import by.yachnikzakhar.newsmanagement.dao.exceptions.DAOException;
import by.yachnikzakhar.newsmanagement.dao.NewsDAO;
import by.yachnikzakhar.newsmanagement.resource.NewsList;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class NewsDAOImpl implements NewsDAO {
    @Override
    public void add(News news) throws DAOException {
        System.out.println("Запись id: " + news.getId() + " добавлена в бд");
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
