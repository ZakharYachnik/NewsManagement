package by.yachnikzakhar.controller.impl;

import by.yachnikzakhar.controller.Command;
import by.yachnikzakhar.service.NewsService;
import by.yachnikzakhar.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ChangeNewsCommand implements Command {
    private NewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

//        News news = NewsList.getInstance().getById(id);

//        try {
//            newsService.update(news);
//            request.getRequestDispatcher("index.jsp").forward(request, response);
//        } catch (ServiceException e) {
//            System.err.println("Ошибка изменения новости!");
//            response.sendRedirect("WEB-INF/jsp/error.jsp");
//        }

    }
}
