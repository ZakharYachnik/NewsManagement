package by.yachnikzakhar.newsmanagement.controller.impl;

import by.yachnikzakhar.newsmanagement.beans.User;
import by.yachnikzakhar.newsmanagement.controller.Command;
import by.yachnikzakhar.newsmanagement.service.ServiceException;
import by.yachnikzakhar.newsmanagement.service.ServiceProvider;
import by.yachnikzakhar.newsmanagement.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static by.yachnikzakhar.newsmanagement.controller.session.SessionConstants.PREV_REQUEST;

public class GoToAdministration implements Command {
    UserService userService = ServiceProvider.getInstance().getUserService();
    private static final Logger logger = LogManager.getLogger(GoToAdministration.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String prevRequest = request.getRequestURI() + "?" + request.getQueryString();
        request.getSession().setAttribute(PREV_REQUEST, prevRequest);

        try {
            request.setAttribute("users", userService.getAll());
            request.getRequestDispatcher("WEB-INF/jsp/administration.jsp").forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
            response.sendRedirect("WEB-INF/jsp/error.jsp");
        }

    }
}
