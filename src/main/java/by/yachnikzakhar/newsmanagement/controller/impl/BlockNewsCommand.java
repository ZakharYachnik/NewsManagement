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

import static by.yachnikzakhar.newsmanagement.controller.constants.NewsParameters.ID;

public class BlockNewsCommand implements Command {
    private NewsService newsService = ServiceProvider.getInstance().getNewsService();
    private static final Logger logger = LogManager.getLogger(BlockNewsCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(ID));

        try {
            newsService.blockById(id);
            response.sendRedirect("Controller?command=go_to_main_page");
        } catch (ServiceException e) {
            logger.error(e);
            response.sendRedirect("Controller?command=go_to_error_page&error=block_news_error");
        }
    }
}
