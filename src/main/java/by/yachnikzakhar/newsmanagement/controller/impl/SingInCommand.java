package by.yachnikzakhar.newsmanagement.controller.impl;

import by.yachnikzakhar.newsmanagement.controller.Command;
import by.yachnikzakhar.newsmanagement.beans.User;
import by.yachnikzakhar.newsmanagement.service.ServiceException;
import by.yachnikzakhar.newsmanagement.service.ServiceProvider;
import by.yachnikzakhar.newsmanagement.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SingInCommand implements Command {
    private final UserService userService = ServiceProvider.getInstance().getUserService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if(login == null || login.isEmpty() || password == null || password.isEmpty()){
            showErrorMessage(request, response, "Ошибка ввода данных");
            return;
        }

        try {
            User user = userService.authentication(login, password);
            request.setAttribute("user", user);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            //request.getRequestDispatcher("WEB-INF/jsp/welcome_page.jsp").forward(request, response);
        } catch (ServiceException e) {
            showErrorMessage(request, response, e.getMessage());
        }

    }

    private void showErrorMessage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("error", message);
        request.getRequestDispatcher("WEB-INF/jsp/sing_in_page.jsp").forward(request, response);
    }
}
