<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.news.label.date" var="label_date"/>
<fmt:message bundle="${loc}" key="local.news.label.brief" var="label_brief"/>
<fmt:message bundle="${loc}" key="local.news.label.content" var="label_content"/>
<fmt:message bundle="${loc}" key="local.news.button.edit" var="button_edit"/>
<fmt:message bundle="${loc}" key="local.news.button.delete" var="button_delete"/>

<div class="container adding">
    <h1 style="text-align: center; text-shadow: 1px 1px 1px #ccc;">
        <c:out value="${news.getTitle()}"/>
    </h1>
    <form>
        <div class="form-group">
            <label for="date">${label_date}:</label>
            <input type="text" id="date" name="date" value="${news.getPublicationDate()}" readonly/>
        </div>
        <div class="form-group">
            <label for="brief">${label_brief}:</label>
            <textarea id="brief" name="brief" rows="3" readonly><c:out value="${news.getBrief()}"/></textarea>
        </div>
        <div class="form-group">
            <label for="content">${label_content}:</label>
            <textarea id="content" name="content" rows="6" readonly><c:out value="${news.getContent()}"/></textarea>
        </div>
    </form>

    <c:if test="${sessionScope.isAdmin}">
        <div class="buttons">
            <a href="Controller?command=go_to_change_news&id=${news.getId()}" class="button edit" style="text-decoration: none;">${button_edit}</a>
            <a href="Controller?command=delete_news&id=${news.getId()}" class="button delete" style="text-decoration: none;">${button_delete}</a>
        </div>
    </c:if>
</div>