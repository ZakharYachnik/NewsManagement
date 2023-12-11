package by.yachnikzakhar.newsmanagement.controller.impl;

import by.yachnikzakhar.newsmanagement.beans.Roles;
import by.yachnikzakhar.newsmanagement.controller.Command;
import by.yachnikzakhar.newsmanagement.beans.User;
import by.yachnikzakhar.newsmanagement.controller.validators.RegistrationValidator;
import by.yachnikzakhar.newsmanagement.dao.exceptions.UserNotFoundException;
import by.yachnikzakhar.newsmanagement.service.ServiceException;
import by.yachnikzakhar.newsmanagement.service.ServiceProvider;
import by.yachnikzakhar.newsmanagement.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RegistrationCommand implements Command {
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String FULL_NAME_PARAM = "full_name";
    private static final String PHONE_NUMBER_PARAM = "phone";
    private static final String EMAIL_PARAM = "email";

    private UserService userService = ServiceProvider.getInstance().getUserService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN_PARAM).trim();
        String password = request.getParameter(PASSWORD_PARAM).trim();
        String fullName = request.getParameter(FULL_NAME_PARAM).trim();
        String phoneNumber = request.getParameter(PHONE_NUMBER_PARAM).trim();
        String email = request.getParameter(EMAIL_PARAM).trim();

        RegistrationValidator validator = RegistrationValidator.getValidator();
        if(!validator.isValidLogin(login) || !validator.isValidPassword(password) || !validator.isValidFullName(fullName) || !validator.isValidPhoneNumber(phoneNumber)){
            response.sendRedirect("Controller?command=go_to_registration&error=validation_error");
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
            HttpSession session = request.getSession(true);
            session.setAttribute("userId", user.getId());
            session.setAttribute("roles", user.getRoles());
            session.setAttribute("isAdmin", user.isAdmin());

            response.sendRedirect("Controller?command=go_to_main_page_after_sign_in");
        }catch (ServiceException exception){
            response.sendRedirect("Controller?command=go_to_registration&error=registration_error");
        }

    }
}
