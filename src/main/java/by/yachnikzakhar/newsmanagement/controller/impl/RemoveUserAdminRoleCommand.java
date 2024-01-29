package by.yachnikzakhar.newsmanagement.controller.impl;

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

public class RemoveUserAdminRoleCommand implements Command {
    private static final String USER_ID_PARAM = "user_id";
    private UserService userService = ServiceProvider.getInstance().getUserService();
    private static final Logger logger = LogManager.getLogger(RemoveUserAdminRoleCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter(USER_ID_PARAM);

        if(userId == null || userId.isEmpty()){
            response.sendRedirect("Controller?command=go_to_administration&error=invalid_data");
            return;
        }

        try {
            userService.removeUserAdminRole(Integer.parseInt(userId));
            response.sendRedirect("Controller?command=go_to_administration");
        } catch (ServiceException e) {
            logger.error(e);
            response.sendRedirect("Controller?command=go_to_administration&error=process_error");
        }
    }
}
