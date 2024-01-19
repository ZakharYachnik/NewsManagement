package by.yachnikzakhar.newsmanagement.controller.listeners;

import by.yachnikzakhar.newsmanagement.dao.connection.ConnectionPool;
import by.yachnikzakhar.newsmanagement.dao.connection.ConnectionPoolException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ServletLifecycleListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().initPoolData();
        } catch (ConnectionPoolException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().dispose();
    }
}
