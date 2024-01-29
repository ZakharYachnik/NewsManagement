package by.yachnikzakhar.newsmanagement.controller.impl;

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

import static by.yachnikzakhar.newsmanagement.controller.session.SessionConstants.PREV_REQUEST;

public class ShowNewsCommand implements Command {
    private static final String PRESENTER_ATTRIBUTE_NAME = "presenter";
    private static final String SHOW_NEWS_PRESENTER = "show_news";
    private static final String NEWS_ATTRIBUTE_NAME = "news";
    private static final String REQUEST_PARAMETER_ID = "id";

    private static final Logger logger = LogManager.getLogger(ShowNewsCommand.class);

    private NewsService newsService = ServiceProvider.getInstance().getNewsService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String prevRequest = request.getRequestURI() + "?" + request.getQueryString();
        request.getSession().setAttribute(PREV_REQUEST, prevRequest);

        int id = Integer.parseInt(request.getParameter(REQUEST_PARAMETER_ID));

        try {
            request.setAttribute(PRESENTER_ATTRIBUTE_NAME, SHOW_NEWS_PRESENTER);
            request.setAttribute(NEWS_ATTRIBUTE_NAME, newsService.getById(id));
            request.getRequestDispatcher("WEB-INF/jsp/news_page.jsp").forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
            response.sendRedirect("Controller?command=go_to_error_page");
        }
    }
}
