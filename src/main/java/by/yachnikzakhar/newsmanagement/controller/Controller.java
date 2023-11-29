package by.yachnikzakhar.newsmanagement.controller;

import by.yachnikzakhar.newsmanagement.dao.connection.ConnectionPool;
import by.yachnikzakhar.newsmanagement.dao.connection.ConnectionPoolException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Controller extends HttpServlet {

    private final CommandProvider provider = CommandProvider.getInstance();

    @Override
    public void destroy() {
        ConnectionPool.getInstance().dispose();
        super.destroy();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter("command");
        provider.getCommand(commandName).execute(req, resp);
    }
}
