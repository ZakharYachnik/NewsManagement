package by.yachnikzakhar.newsmanagement.beans;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class News {

    private int id;
    private String title;
    private LocalDate publicationDate;
    private String brief;
    private String content;

    public News() {
    }

    public News(int id, String title, LocalDate publicationDate, String brief, String content) {
        this.id = id;
        this.title = title;
        this.publicationDate = publicationDate;
        this.brief = brief;
        this.content = content;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return getId() == news.getId() && Objects.equals(getTitle(), news.getTitle()) && Objects.equals(getDate(), news.getDate()) && Objects.equals(getBrief(), news.getBrief()) && Objects.equals(getContent(), news.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDate(), getBrief(), getContent());
    }
}
