<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container adding">
    <h1 style="text-align: center; text-shadow: 1px 1px 1px #ccc    ;">
        <c:out value="${news.getTitle()}"/>
    </h1>
    <form>
        <div class="form-group">
            <label for="date">Дата публикации:</label>
            <input type="text" id="date" name="date" value="${news.getPublicationDate()}" readonly/>
        </div>
        <div class="form-group">
            <label for="brief">Краткое содержание:</label>
            <textarea id="brief" name="brief" rows="3" readonly><c:out value="${news.getBrief()}"/></textarea>
        </div>
        <div class="form-group">
            <label for="content">Текст новости:</label>
            <textarea id="content" name="content" rows="6" readonly><c:out value="${news.getContent()}"/></textarea>
        </div>
    </form>

    <c:if test="${sessionScope.isAdmin}">
        <div class="buttons">
            <a href="Controller?command=go_to_change_news&id=${news.getId()}" class="button edit" style="text-decoration: none;">Редактировать</a>
            <a href="Controller?command=delete_news&id=${news.getId()}" class="button delete" style="text-decoration: none;">Заблокировать</a>
        </div>
    </c:if>
</div>