package by.yachnikzakhar.newsmanagement.controller.impl;

import by.yachnikzakhar.newsmanagement.beans.News;
import by.yachnikzakhar.newsmanagement.controller.Command;
import by.yachnikzakhar.newsmanagement.service.NewsService;
import by.yachnikzakhar.newsmanagement.service.ServiceException;
import by.yachnikzakhar.newsmanagement.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.LocalDate;

import static by.yachnikzakhar.newsmanagement.controller.constants.NewsParameters.*;
import static by.yachnikzakhar.newsmanagement.controller.constants.StatusParameters.ACTIVE;


public class ChangeNewsCommand implements Command {
    private NewsService newsService = ServiceProvider.getInstance().getNewsService();
    private static final Logger logger = LogManager.getLogger(ChangeNewsCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int newsId = Integer.parseInt(request.getParameter(ID));

        News news = new News(newsId, request.getParameter(TITLE),
                LocalDate.parse(request.getParameter(DATE)),
                request.getParameter(BRIEF), request.getParameter(CONTENT),
                ACTIVE, Integer.parseInt(request.getParameter(USER_ID)));

        try {
            newsService.update(news);
            response.sendRedirect("Controller?command=show_news&id=" + newsId);
        } catch (ServiceException e) {
            logger.error(e);
            response.sendRedirect("Controller?command=go_to_error_page");
        }
    }
}
