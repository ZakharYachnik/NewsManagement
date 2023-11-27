<%@ page language="java" contentType="text/html; charset=utf-8"
import="by.yachnikzakhar.newsmanagement.beans.User" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Form</title>
    <link rel="stylesheet" href="./css/styles.css">
</head>
<body>
<div class="container">
    <div class="entered-values">
        <label>Данные пользователя:</label>
        <% User user = (User) request.getAttribute("user");%>
        <p>
            Значение поля "ФИО":
            <%= user.getFullName() %></p>
        <p>
            Значение поля "Логин":
            <%= user.getLogin() %></p>
        <p>
            Значение поля "Номер телефона":
            <%= user.getPhoneNumber() %></p>
        <p>
            Значение поля "Email":
            <%= user.getEmail() %></p>
        <p>
            Значение поля "Пароль":
            <%= user.getPassword() %>
        </p>
        <% } %>
    </div>
</div>
</body>
</html>

