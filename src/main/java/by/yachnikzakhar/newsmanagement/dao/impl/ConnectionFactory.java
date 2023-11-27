package by.yachnikzakhar.newsmanagement.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionFactory {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Drier");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load MySQL JDBC driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/news_management", "root", "12asdfg12");
        } catch (SQLException e) {
            throw new SQLException("Failed to establish a database connection", e);
        }
        return connection;
    }
}
