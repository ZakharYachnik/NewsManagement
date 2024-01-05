package by.yachnikzakhar.newsmanagement.controller.impl;

import by.yachnikzakhar.newsmanagement.beans.Roles;
import by.yachnikzakhar.newsmanagement.controller.Command;
import by.yachnikzakhar.newsmanagement.beans.User;
import by.yachnikzakhar.newsmanagement.controller.session.SaveParametersInSession;
import by.yachnikzakhar.newsmanagement.util.validation.UserDataValidation;
import by.yachnikzakhar.newsmanagement.util.validation.ValidationProvider;
import by.yachnikzakhar.newsmanagement.util.validation.impl.RegistrationValidator;
import by.yachnikzakhar.newsmanagement.service.ServiceException;
import by.yachnikzakhar.newsmanagement.service.ServiceProvider;
import by.yachnikzakhar.newsmanagement.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

public class RegistrationCommand implements Command {
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String FULL_NAME_PARAM = "full_name";
    private static final String PHONE_NUMBER_PARAM = "phone";
    private static final String EMAIL_PARAM = "email";

    private UserDataValidation userDataValidation = ValidationProvider.getInstance().getUserDataValidation();

    private UserService userService = ServiceProvider.getInstance().getUserService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN_PARAM).trim();
        String password = request.getParameter(PASSWORD_PARAM).trim();
        String fullName = request.getParameter(FULL_NAME_PARAM).trim();
        String phoneNumber = request.getParameter(PHONE_NUMBER_PARAM).trim();
        String email = request.getParameter(EMAIL_PARAM).trim();

        if(userDataValidation.checkRegistrationData(login, password, fullName, phoneNumber)){
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
            SaveParametersInSession.saveAuthorisationParametersInSession(request.getSession(true), login, user.getRoles(), user.isAdmin());

            response.sendRedirect("Controller?command=go_to_main_page_after_sign_in");
        }catch (ServiceException exception){
            response.sendRedirect("Controller?command=go_to_registration&error=registration_error");
        }

    }
}
