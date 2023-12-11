package by.yachnikzakhar.newsmanagement.controller.impl;

import by.yachnikzakhar.newsmanagement.controller.Command;
import by.yachnikzakhar.newsmanagement.beans.User;
import by.yachnikzakhar.newsmanagement.service.ServiceException;
import by.yachnikzakhar.newsmanagement.service.ServiceProvider;
import by.yachnikzakhar.newsmanagement.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class SignInCommand implements Command {
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private final UserService userService = ServiceProvider.getInstance().getUserService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);

        if(login == null || login.isEmpty() || password == null || password.isEmpty()){
            response.sendRedirect("Controller?command=go_to_sign_in&error");
            return;
        }

        try {
            User user = userService.authentication(login, password);
            HttpSession session = request.getSession(true);
            session.setAttribute("userId", user.getId());
            session.setAttribute("roles", user.getRoles());
            session.setAttribute("isAdmin", user.isAdmin());

            response.sendRedirect("Controller?command=go_to_main_page_after_sign_in");
        } catch (ServiceException e) {
            response.sendRedirect("Controller?command=go_to_sign_in&error");
        }

    }

}
