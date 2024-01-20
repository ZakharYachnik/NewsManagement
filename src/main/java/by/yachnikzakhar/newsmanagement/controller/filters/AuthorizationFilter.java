package by.yachnikzakhar.newsmanagement.controller.filters;

import by.yachnikzakhar.newsmanagement.controller.CommandName;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class AuthorizationFilter implements Filter {
    private static final List<CommandName> NON_AUTHORIZED_COMMANDS = Arrays.asList(
            CommandName.SIGN_IN,
            CommandName.REGISTRATION,
            CommandName.GO_TO_REGISTRATION,
            CommandName.GO_TO_SIGN_IN,
            CommandName.GO_TO_MAIN_PAGE,
            CommandName.GO_TO_ERROR_PAGE,
            CommandName.CHANGE_LOCALE
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String command = request.getParameter("command").toUpperCase();
        if(NON_AUTHORIZED_COMMANDS.contains(CommandName.valueOf(command))) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("Controller?command=go_to_sign_in");
            return;
        }

        chain.doFilter(request, response);
    }
}
