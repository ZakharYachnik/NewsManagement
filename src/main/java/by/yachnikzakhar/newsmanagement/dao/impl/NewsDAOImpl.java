package by.yachnikzakhar.newsmanagement.dao.impl;

import by.yachnikzakhar.newsmanagement.beans.News;
import by.yachnikzakhar.newsmanagement.dao.connection.ConnectionPool;
import by.yachnikzakhar.newsmanagement.dao.connection.ConnectionPoolException;
import by.yachnikzakhar.newsmanagement.dao.exceptions.DAOException;
import by.yachnikzakhar.newsmanagement.dao.NewsDAO;
import by.yachnikzakhar.newsmanagement.dao.exceptions.NewsNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NewsDAOImpl implements NewsDAO {
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_BRIEF = "brief";
    private static final String COLUMN_PUBLICATION_DATE = "publication_date";
    private static final String COLUMN_CONTENT = "content";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String STATUS_BLOCKED = "BLOCKED";

    private static final String INSERT_NEWS_QUERY = "insert into news(title, brief, publication_date, content, status, user_id) values(?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_NEWS_QUERY = "select * from news";
    private static final String SELECT_NEWS_BY_ID_QUERY = "select * from news where id=?";
    private static final String SELECT_NEWS_BY_USER_ID_QUERY = "select * from news where user_id=?";
    private static final String SELECT_NEWS_BY_PUBLICATION_DATE_QUERY = "select * from news where publication_date=?";
    private static final String SELECT_NEWS_BY_TITLE_QUERY = "select * from news where title=?";
    private static final String SELECT_NEWS_IN_RANGE = "select * from news where id between ? and ?";
    private static final String SELECT_NEWS_FROM_END = "select * from news order by id desc limit ?";
    private static final String UPDATE_NEWS_QUERY = "update news set title=?, brief=?, publication_date=?, content=?, status=?, user_id=? where id=?";
    private static final String BLOCK_NEWS_QUERY = "update news set status=? where id=?";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void add(News news) throws DAOException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEWS_QUERY);) {
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getBrief());
            preparedStatement.setObject(3, news.getPublicationDate());
            preparedStatement.setString(4, news.getContent());
            preparedStatement.setString(5, news.getStatus());
            preparedStatement.setInt(6, news.getUserId());

            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new DAOException("Error adding news in the system");
            }
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
            throw new DAOException("Error in the news adding process", e);
        }
    }

    @Override
    public List<News> getInRange(int startId, int endId) throws NewsNotFoundException, DAOException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NEWS_IN_RANGE);) {

            preparedStatement.setInt(1, startId);
            preparedStatement.setInt(2, endId);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                if (!resultSet.next()) {
                    throw new NewsNotFoundException("News in range not found");
                }

                List<News> newsList = new ArrayList<>();
                do {
                    News news = new News();
                    news.setId(resultSet.getInt(COLUMN_ID));
                    news.setTitle(resultSet.getString(COLUMN_TITLE));
                    news.setBrief(resultSet.getString(COLUMN_BRIEF));
                    news.setPublicationDate(resultSet.getObject(COLUMN_PUBLICATION_DATE, LocalDate.class));
                    news.setContent(resultSet.getString(COLUMN_CONTENT));
                    news.setStatus(resultSet.getString(COLUMN_STATUS));
                    news.setUserId(resultSet.getInt(COLUMN_USER_ID));

                    newsList.add(news);
                } while (resultSet.next());

                return newsList;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error in the news getting process", e);
        }
    }

    @Override
    public List<News> getFromEnd(int count) throws NewsNotFoundException, DAOException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NEWS_FROM_END);) {

            preparedStatement.setInt(1, count);

            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                if (!resultSet.next()) {
                    throw new NewsNotFoundException("News from end not found");
                }

                List<News> newsList = new ArrayList<>();
                do {
                    News news = new News();
                    news.setId(resultSet.getInt(COLUMN_ID));
                    news.setTitle(resultSet.getString(COLUMN_TITLE));
                    news.setBrief(resultSet.getString(COLUMN_BRIEF));
                    news.setPublicationDate(resultSet.getObject(COLUMN_PUBLICATION_DATE, LocalDate.class));
                    news.setContent(resultSet.getString(COLUMN_CONTENT));
                    news.setStatus(resultSet.getString(COLUMN_STATUS));
                    news.setUserId(resultSet.getInt(COLUMN_USER_ID));

                    newsList.add(news);
                } while (resultSet.next());

                return newsList;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error in the news getting process", e);
        }
    }

    @Override
    public List<News> findByPublicationDate(LocalDate date) throws NewsNotFoundException, DAOException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NEWS_BY_PUBLICATION_DATE_QUERY);) {

            preparedStatement.setObject(1, date);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                if (!resultSet.next()) {
                    throw new NewsNotFoundException("News with publication date " + date + " not found");
                }

                List<News> newsList = new ArrayList<>();
                do {
                    News news = new News();
                    news.setId(resultSet.getInt(COLUMN_ID));
                    news.setTitle(resultSet.getString(COLUMN_TITLE));
                    news.setBrief(resultSet.getString(COLUMN_BRIEF));
                    news.setPublicationDate(resultSet.getObject(COLUMN_PUBLICATION_DATE, LocalDate.class));
                    news.setContent(resultSet.getString(COLUMN_CONTENT));
                    news.setStatus(resultSet.getString(COLUMN_STATUS));
                    news.setUserId(resultSet.getInt(COLUMN_USER_ID));

                    newsList.add(news);
                } while (resultSet.next());

                return newsList;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error in the news getting process", e);
        }
    }

    @Override
    public List<News> findByTitle(String title) throws NewsNotFoundException, DAOException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NEWS_BY_TITLE_QUERY);) {

            preparedStatement.setString(1, title);
            try(ResultSet resultSet = preparedStatement.executeQuery();) {
                if (!resultSet.next()) {
                    throw new NewsNotFoundException("News with title " + title + " not found");
                }
                List<News> newsList = new ArrayList<>();

                do {
                    News news = new News();
                    news.setId(resultSet.getInt(COLUMN_ID));
                    news.setTitle(resultSet.getString(COLUMN_TITLE));
                    news.setBrief(resultSet.getString(COLUMN_BRIEF));
                    news.setPublicationDate(resultSet.getObject(COLUMN_PUBLICATION_DATE, LocalDate.class));
                    news.setContent(resultSet.getString(COLUMN_CONTENT));
                    news.setStatus(resultSet.getString(COLUMN_STATUS));
                    news.setUserId(resultSet.getInt(COLUMN_USER_ID));

                    newsList.add(news);
                } while (resultSet.next());

                return newsList;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error in the news getting process", e);
        }
    }

    @Override
    public List<News> findByUserId(int userId) throws DAOException, NewsNotFoundException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NEWS_BY_USER_ID_QUERY)) {

            preparedStatement.setInt(1, userId);
            try(ResultSet resultSet = preparedStatement.executeQuery();) {
                if (!resultSet.next()) {
                    throw new NewsNotFoundException("News with user id: " + userId + " not found");
                }

                List<News> newsList = new ArrayList<>();
                do {
                    News news = new News();
                    news.setId(resultSet.getInt(COLUMN_ID));
                    news.setTitle(resultSet.getString(COLUMN_TITLE));
                    news.setBrief(resultSet.getString(COLUMN_BRIEF));
                    news.setPublicationDate(resultSet.getObject(COLUMN_PUBLICATION_DATE, LocalDate.class));
                    news.setContent(resultSet.getString(COLUMN_CONTENT));
                    news.setStatus(resultSet.getString(COLUMN_STATUS));
                    news.setUserId(resultSet.getInt(COLUMN_USER_ID));

                    newsList.add(news);
                } while (resultSet.next());

                return newsList;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error in the news getting process", e);
        }
    }

    @Override
    public News getById(int id) throws NewsNotFoundException, DAOException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NEWS_BY_ID_QUERY);) {
            preparedStatement.setInt(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery();) {
                if (!resultSet.next()) {
                    throw new NewsNotFoundException("News with id: " + id + " not found");
                }

                News news = new News();
                news.setId(resultSet.getInt(COLUMN_ID));
                news.setTitle(resultSet.getString(COLUMN_TITLE));
                news.setBrief(resultSet.getString(COLUMN_BRIEF));
                news.setPublicationDate(resultSet.getObject(COLUMN_PUBLICATION_DATE, LocalDate.class));
                news.setContent(resultSet.getString(COLUMN_CONTENT));
                news.setStatus(resultSet.getString(COLUMN_STATUS));
                news.setUserId(resultSet.getInt(COLUMN_USER_ID));

                return news;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error in the news getting process", e);
        }
    }

    @Override
    public List<News> getAll() throws NewsNotFoundException, DAOException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_NEWS_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<News> newsList = new ArrayList<>();
            if (!resultSet.next()) {
                throw new NewsNotFoundException("News not found");
            }

            do {
                News news = new News();
                news.setId(resultSet.getInt(COLUMN_ID));
                news.setTitle(resultSet.getString(COLUMN_TITLE));
                news.setBrief(resultSet.getString(COLUMN_BRIEF));
                news.setPublicationDate(resultSet.getObject(COLUMN_PUBLICATION_DATE, LocalDate.class));
                news.setContent(resultSet.getString(COLUMN_CONTENT));
                news.setStatus(resultSet.getString(COLUMN_STATUS));
                news.setUserId(resultSet.getInt(COLUMN_USER_ID));

                newsList.add(news);
            } while (resultSet.next());

            return newsList;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error in the news getting process", e);
        }
    }

    @Override
    public void update(News news) throws DAOException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NEWS_QUERY);) {

            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getBrief());
            preparedStatement.setObject(3, news.getPublicationDate());
            preparedStatement.setString(4, news.getContent());
            preparedStatement.setString(5, news.getStatus());
            preparedStatement.setInt(6, news.getUserId());
            preparedStatement.setInt(7, news.getId());

            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new DAOException("Error updating news in the system");
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error in the news updating process", e);
        }
    }

    @Override
    public void block(int id) throws DAOException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(BLOCK_NEWS_QUERY);) {

            preparedStatement.setString(1, STATUS_BLOCKED);
            preparedStatement.setInt(2, id);

            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new DAOException("Error blocking news in the system");
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Error in the news blocking process", e);
        }
    }
}
