package by.yachnikzakhar.newsmanagement.controller.impl;

import by.yachnikzakhar.newsmanagement.beans.News;
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
import java.time.LocalDate;

import static by.yachnikzakhar.newsmanagement.controller.constants.NewsParameters.*;
import static by.yachnikzakhar.newsmanagement.controller.constants.StatusParameters.ACTIVE;

public class AddNewsCommand implements Command {

    NewsService newsService = ServiceProvider.getInstance().getNewsService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userId;
        HttpSession session = request.getSession();
        userId = (int) session.getAttribute(SessionConstants.USER_ID);

        News news = new News(request.getParameter(TITLE),
                LocalDate.now(),
                request.getParameter(BRIEF), request.getParameter(CONTENT),
                ACTIVE, userId);

        try {
            newsService.add(news);
            response.sendRedirect("Controller?command=go_to_main_page");
        } catch (ServiceException e) {
            //TODO: add error logging here
            response.sendRedirect("Controller?command=go_to_error_page&error=add_news_error");
        }
    }
}
