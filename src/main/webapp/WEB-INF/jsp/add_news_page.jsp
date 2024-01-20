<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавить новость</title>
    <link rel="stylesheet" href="./css/header_foooter.css">
    <link rel="stylesheet" href="./css/add_new.css">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/css/bootstrap.min.css">

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.news.title.add_news" var="add_news_title"/>
    <fmt:message bundle="${loc}" key="local.news.label.title" var="label_title"/>
    <fmt:message bundle="${loc}" key="local.news.label.brief" var="label_brief"/>
    <fmt:message bundle="${loc}" key="local.news.label.content" var="label_content"/>
    <fmt:message bundle="${loc}" key="local.news.button.add" var="button_add"/>

</head>
<body>

<c:import url="/WEB-INF/jsp/blocks/header.jsp"/>

<div style="margin-top: 20px; margin-bottom: 20px;" class="container adding">
    <h1 style="text-align: center">
        ${add_news_title}
    </h1>
    <form action="Controller?command=add_news" method="post">
        <div class="form-group">
            <label for="title">${label_title}</label>
            <input type="text" id="title" name="title" required>
        </div>
        <div class="form-group">
            <label for="brief">${label_brief}</label>
            <textarea id="brief" name="brief" rows="3" required></textarea>
        </div>
        <div class="form-group">
            <label for="content">${label_content}</label>
            <textarea id="content" name="content" rows="6" required></textarea>
        </div>
        <button type="submit" class="submit-button">${button_add}</button>
    </form>
</div>

<c:import url="/WEB-INF/jsp/blocks/footer.jsp"/>
</body>
</html>