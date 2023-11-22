package by.yachnikzakhar.controller.impl;

import by.yachnikzakhar.controller.Command;
import by.yachnikzakhar.service.ServiceException;
import by.yachnikzakhar.service.ServiceProvider;
import by.yachnikzakhar.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToAdministration implements Command {
    UserService userService = ServiceProvider.getInstance().getUserService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("users", userService.getAll());
            request.getRequestDispatcher("WEB-INF/jsp/administration.jsp").forward(request, response);
        } catch (ServiceException e) {
            System.err.println("Ошибка получения новости!");
            response.sendRedirect("WEB-INF/jsp/error.jsp");
        }

    }
}
