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

public class BlockUserCommand implements Command {

    private static final String ID_PARAM = "id";

    private UserService userService = ServiceProvider.getInstance().getUserService();
    private static final Logger logger = LogManager.getLogger(BlockUserCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter(ID_PARAM);

        if(id == null || id.isEmpty()){
            response.sendRedirect("Controller?command=go_to_administration&error=invalid_data");
            return;
        }

        try {
            userService.blockByLogin(Integer.parseInt(id));
            response.sendRedirect("Controller?command=go_to_administration&message=user_blocked");
        } catch (ServiceException e) {
            response.sendRedirect("Controller?command=go_to_administration&error=blocking_error");
        }
    }
}
