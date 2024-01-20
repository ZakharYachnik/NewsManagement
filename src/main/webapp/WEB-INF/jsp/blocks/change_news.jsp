<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.news.title.change_news" var="change_news_title"/>
<fmt:message bundle="${loc}" key="local.news.label.title" var="label_title"/>
<fmt:message bundle="${loc}" key="local.news.label.date" var="label_date"/>
<fmt:message bundle="${loc}" key="local.news.label.brief" var="label_brief"/>
<fmt:message bundle="${loc}" key="local.news.label.content" var="label_content"/>
<fmt:message bundle="${loc}" key="local.news.button.edit" var="button_save_changes"/>

<div class="container adding">
    <h1 style="text-align: center;">${change_news_title}</h1>
    <form action="Controller?command=change_news&id=${news.getId()}&user_id=${news.getUserId()}" method="post">
        <div class="form-group">
            <label for="title">${label_title}:</label>
            <input type="text" id="title" name="title" value="${news.getTitle()}" required/>
        </div>
        <div class="form-group">
            <label for="date">${label_date}:</label>
            <input type="date" id="date" name="date" value="${news.getPublicationDate()}" required/>
        </div>
        <div class="form-group">
            <label for="brief">${label_brief}:</label>
            <textarea id="brief" name="brief" rows="3" required>${news.getBrief()}</textarea>
        </div>
        <div class="form-group">
            <label for="content">${label_content}:</label>
            <textarea id="content" name="content" rows="6" required>${news.getContent()}</textarea>
        </div>
        <button type="submit" class="submit-button">${button_save_changes}</button>
    </form>
</div>
