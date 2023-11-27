package by.yachnikzakhar.newsmanagement.service;

import by.yachnikzakhar.newsmanagement.service.impl.NewsServiceImpl;
import by.yachnikzakhar.newsmanagement.service.impl.UserServiceImpl;

public class ServiceProvider {
    private static ServiceProvider provider;

    private final NewsService newsService = new NewsServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private ServiceProvider() {
    }

    public static ServiceProvider getInstance(){
        if(provider == null){
            provider = new ServiceProvider();
        }
        return provider;
    }

    public NewsService getNewsService() {
        return newsService;
    }

    public UserService getUserService() {
        return userService;
    }
}
