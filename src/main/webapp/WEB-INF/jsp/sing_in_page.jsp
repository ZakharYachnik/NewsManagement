<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Authorization</title>
    <link rel="stylesheet" href="./css/styles.css">
</head>
<body>
<div class="container">
    <div class="registration-form">
        <h1>Авторизация</h1>
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="sing_in">

            <label for="username">Логин:</label>
            <input type="text" id="username" name="login" required>

            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password" required>

            <input type="submit" id="button" value="Войти">
        </form>
    </div>
    <div id="error-message">
        <%
        if (request.getAttribute("error") != null && !((String)request.getAttribute("error")).isEmpty()) {
        %>
        <p><%= request.getAttribute("error") %></p>
        <%
        }
        %>
    </div>
</div>
</body>
</html>
            
