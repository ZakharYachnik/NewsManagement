package by.yachnikzakhar.newsmanagement.controller.filters;

import by.yachnikzakhar.newsmanagement.controller.CommandName;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AdminFilter implements Filter {

    private static final List<CommandName> ADMIN_COMMANDS = Arrays.asList(
            CommandName.GO_TO_ADMINISTRATION,
            CommandName.CHANGE_NEWS,
            CommandName.DELETE_NEWS,
            CommandName.GO_TO_CHANGE_NEWS,
            CommandName.ADD_NEWS,
            CommandName.GO_TO_ADD_NEWS,
            CommandName.BLOCK_USER,
            CommandName.ADD_USER_ADMIN_ROLE,
            CommandName.REMOVE_USER_ADMIN_ROLE
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String command = request.getParameter("command").toUpperCase();
        if (!ADMIN_COMMANDS.contains(CommandName.valueOf(command))) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession();
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");

        if (isAdmin == null || !isAdmin) {
            response.sendRedirect("Controller?command=go_to_error_page&error=access_denied");
            return;
        }

        chain.doFilter(request, response);
    }

}
