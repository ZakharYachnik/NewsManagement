package by.yachnikzakhar.newsmanagement.controller.impl;

import by.yachnikzakhar.newsmanagement.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static by.yachnikzakhar.newsmanagement.controller.session.SessionConstants.PREV_REQUEST;

public class ChangeLocaleCommand implements Command {
    private static final String LOCALE_PARAM = "locale";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute(LOCALE_PARAM, request.getParameter(LOCALE_PARAM));

        response.sendRedirect((String) session.getAttribute(PREV_REQUEST));
    }
}
