package by.yachnikzakhar.newsmanagement.controller.impl;

import by.yachnikzakhar.newsmanagement.beans.News;
import by.yachnikzakhar.newsmanagement.controller.Command;
import by.yachnikzakhar.newsmanagement.service.NewsService;
import by.yachnikzakhar.newsmanagement.service.ServiceException;
import by.yachnikzakhar.newsmanagement.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteNewsCommand implements Command {
    private NewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

//        News news = NewsList.getInstance().getById(id);
//
//        try {
//            newsService.remove(news);
//            request.getRequestDispatcher("index.jsp").forward(request, response);
//        } catch (ServiceException e) {
//            System.err.println("Ошибка удаления новости!");
//            response.sendRedirect("WEB-INF/jsp/error.jsp");
//        }


    }
}
