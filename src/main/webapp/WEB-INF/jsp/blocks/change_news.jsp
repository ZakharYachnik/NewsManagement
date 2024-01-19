<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="container adding">
    <h1 style="text-align: center;">Изменить новость</h1>
    <form action="Controller?command=change_news&id=${news.getId()}&user_id=${news.getUserId()}" method="post">
        <div class="form-group">
            <label for="title">Заголовок:</label>
            <input type="text" id="title" name="title" value="${news.getTitle()}" required/>
        </div>
        <div class="form-group">
            <label for="date">Дата:</label>
            <input type="date" id="date" name="date" value="${news.getPublicationDate()}" required/>
        </div>
        <div class="form-group">
            <label for="brief">Краткое содержание:</label>
            <textarea id="brief" name="brief" rows="3" required> ${news.getBrief()}</textarea>
        </div>
        <div class="form-group">
            <label for="content">Контент:</label>
            <textarea id="content" name="content" rows="6" required> ${news.getContent()}</textarea>
        </div>
        <button type="submit" class="submit-button">Сохранить изменения</button>
    </form>
</div>