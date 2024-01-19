package by.yachnikzakhar.newsmanagement.controller.impl;

import by.yachnikzakhar.newsmanagement.controller.Command;
import by.yachnikzakhar.newsmanagement.controller.session.SessionConstants;
import by.yachnikzakhar.newsmanagement.service.NewsService;
import by.yachnikzakhar.newsmanagement.service.ServiceException;
import by.yachnikzakhar.newsmanagement.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static by.yachnikzakhar.newsmanagement.controller.session.SessionConstants.PREV_REQUEST;
import static by.yachnikzakhar.newsmanagement.controller.session.SessionConstants.USER_LOGIN;

public class GoToMainPageCommand implements Command {

    private NewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String prevRequest = request.getRequestURI() + "?" + request.getQueryString();
        request.getSession().setAttribute(PREV_REQUEST, prevRequest);


        try {
            HttpSession session = request.getSession(false);
            if(session != null && session.getAttribute(USER_LOGIN) != null){
                request.setAttribute("newsList", newsService.getFromEnd(5));
            }
            else{
                request.setAttribute("newsList", newsService.getFromEnd(3));
            }

            request.getRequestDispatcher("WEB-INF/jsp/pages/main_page.jsp").forward(request, response);
        } catch (ServiceException e) {
            // TODO: Add error logging here
            response.sendRedirect("Controller?command=go_to_error_page");
        }

    }
}
