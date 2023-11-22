package by.yachnikzakhar.controller.impl;

import by.yachnikzakhar.controller.Command;
import by.yachnikzakhar.service.ServiceProvider;
import by.yachnikzakhar.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToRegistrationCommand implements Command {
    private UserService userService = ServiceProvider.getInstance().getUserService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/registration_page.jsp").forward(request, response);
    }
}
