package by.yachnikzakhar.newsmanagement.controller.impl;

import by.yachnikzakhar.newsmanagement.beans.Roles;
import by.yachnikzakhar.newsmanagement.controller.Command;
import by.yachnikzakhar.newsmanagement.beans.User;
import by.yachnikzakhar.newsmanagement.controller.validators.RegistrationValidator;
import by.yachnikzakhar.newsmanagement.service.ServiceException;
import by.yachnikzakhar.newsmanagement.service.ServiceProvider;
import by.yachnikzakhar.newsmanagement.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RegistrationCommand implements Command {
    private UserService userService = ServiceProvider.getInstance().getUserService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login").trim();
        String password = request.getParameter("password").trim();
        String fullName = request.getParameter("full_name").trim();
        String phoneNumber = request.getParameter("phone").trim();
        String email = request.getParameter("email");

        RegistrationValidator validator = RegistrationValidator.getValidator();
        if(!validator.isValidLogin(login) || !validator.isValidPassword(password) || !validator.isValidFullName(fullName) || !validator.isValidPhoneNumber(phoneNumber)){
            System.out.println("Ошибка валидации");
            request.setAttribute("error", "Ошибка регистрации!");
            request.getRequestDispatcher("WEB-INF/jsp/registration_page.jsp").forward(request, response);
            return;
        }

        User user = new User(
                login,
                password,
                fullName,
                phoneNumber,
                email,
                "ACTIVE",
                Arrays.asList(Roles.USER.toString())
        );

        try{
            userService.add(user);
            request.setAttribute("user", user);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }catch (ServiceException exception){
            request.setAttribute("error", "Ошибка регистрации!");
            request.getRequestDispatcher("WEB-INF/jsp/registration_page.jsp").forward(request, response);
        }

    }
}
