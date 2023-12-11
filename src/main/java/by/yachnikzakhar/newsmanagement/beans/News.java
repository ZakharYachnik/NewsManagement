package by.yachnikzakhar.newsmanagement.beans;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class News implements Serializable {
    @Serial
    private static final long serialVersionUID = -7727223409531121595L;

    private int id;
    private String title;
    private LocalDate publicationDate;
    private String brief;
    private String content;
    private String status;
    private int userId;

    public News() {
    }

    public News(int id, String title, LocalDate publicationDate, String brief, String content, String status, int userId) {
        this.id = id;
        this.title = title;
        this.publicationDate = publicationDate;
        this.brief = brief;
        this.content = content;
        this.status = status;
        this.userId = userId;
    }

    public News(String title, LocalDate publicationDate, String brief, String content, String status, int userId) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.brief = brief;
        this.content = content;
        this.status = status;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return getId() == news.id && Objects.equals(title, news.title) && Objects.equals(publicationDate, news.publicationDate) && Objects.equals(brief, news.brief) && Objects.equals(content, news.content) && Objects.equals(status, news.status) && Objects.equals(userId, news.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, publicationDate, brief, content, status, userId);
    }
}
