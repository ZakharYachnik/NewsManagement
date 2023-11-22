package by.yachnikzakhar.controller.impl;

import by.yachnikzakhar.controller.Command;
import by.yachnikzakhar.service.NewsService;
import by.yachnikzakhar.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToAddNewsCommand implements Command {

    private NewsService newsService = ServiceProvider.getInstance().getNewsService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/add_news_page.jsp").forward(request, response);
    }
}
