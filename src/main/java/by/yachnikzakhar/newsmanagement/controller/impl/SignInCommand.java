package by.yachnikzakhar.newsmanagement.controller.impl;

import by.yachnikzakhar.newsmanagement.beans.User;
import by.yachnikzakhar.newsmanagement.controller.Command;
import by.yachnikzakhar.newsmanagement.controller.session.WorkWithParametersInSession;
import by.yachnikzakhar.newsmanagement.service.ServiceException;
import by.yachnikzakhar.newsmanagement.service.ServiceProvider;
import by.yachnikzakhar.newsmanagement.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class SignInCommand implements Command {
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private final UserService userService = ServiceProvider.getInstance().getUserService();
    private static final Logger logger = LogManager.getLogger(SignInCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);

        if(login == null || login.isEmpty() || password == null || password.isEmpty()){
            response.sendRedirect("Controller?command=go_to_sign_in&error=invalid_data");
            return;
        }

        try {
            User user = userService.authentication(login, password);
            WorkWithParametersInSession.saveAuthorisationParametersInSession(request.getSession(true), user.getId(), login, user.getRoles(), user.isAdmin());

            response.sendRedirect("Controller?command=go_to_main_page");
        } catch (ServiceException e) {
            logger.error(e);
            response.sendRedirect("Controller?command=go_to_sign_in&error=invalid_data");
        }

    }

}
