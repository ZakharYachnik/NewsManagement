<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавить новость</title>
    <link rel="stylesheet" href="./css/header_footer.css">
    <link rel="stylesheet" href="./css/add_new.css">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/css/bootstrap.min.css">
</head>
<body>

<c:import url="/WEB-INF/jsp/blocks/header.jsp"/>

<div style="margin-top: 20px; margin-bottom: 20px;" class="container adding">
    <h1 style="text-align: center">Добавить новость</h1>
    <form action="Controller?command=add_news" method="post">
        <div class="form-group">
            <label for="title">Заголовок:</label>
            <input type="text" id="title" name="title" required>
        </div>
        <div class="form-group">
            <label for="date">Дата:</label>
            <input type="date" id="date" name="date" required>
        </div>
        <div class="form-group">
            <label for="brief">Краткое содержание:</label>
            <textarea id="brief" name="brief" rows="3" required></textarea>
        </div>
        <div class="form-group">
            <label for="content">Контент:</label>
            <textarea id="content" name="content" rows="6" required></textarea>
        </div>
        <button type="submit" class="submit-button">Добавить</button>
    </form>
</div>

<c:import url="/WEB-INF/jsp/blocks/footer.jsp"/>
</body>
</html>