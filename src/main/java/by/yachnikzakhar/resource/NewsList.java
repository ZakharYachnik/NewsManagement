package by.yachnikzakhar.resource;

import by.yachnikzakhar.beans.News;
import by.yachnikzakhar.dao.DAOProvider;

import java.util.ArrayList;
import java.util.List;

public class NewsList {

    private static NewsList instance;
    private List<News> newsList;
    public NewsList() {
        this.newsList = new ArrayList<>(){
            {
                this.add(new News(0, "В мире нашли самого милого котика",
                        "10.11.2023",
                        "Ученые объявили о находке самого милого" +
                                " котика на Земле. Его милые черты и игривый характер покорили" +
                                " сердца многих.",
                        "Котик просто супер!"));
                this.add(new News(1, "Ученые открыли новый вид растения",
                        "10.11.2023",
                        "Ученые сообщили о своем открытии нового" +
                                " вида растения, которое обладает уникальными свойствами и может" +
                                " использоваться в медицине.",
                        "Очень интересное растение!"));
                this.add(new News(2, "Новый фильм стал блокбастером",
                        "10.11.2023",
                        "Вышедший недавно фильм собрал огромную" +
                                " аудиторию зрителей и стал настоящим хитом сезона. Критики отмечают\n" +
                                " его высокое качество и захватывающий сюжет..",
                        "Фильм супер!"));
            }
        };
    }

    public static NewsList getInstance(){
        if(instance == null){
            instance = new NewsList();
        }
        return instance;
    }
    public List<News> getNewsList() {
        return newsList;
    }

    public News getById(int id){
        return newsList.get(id);
    }
}