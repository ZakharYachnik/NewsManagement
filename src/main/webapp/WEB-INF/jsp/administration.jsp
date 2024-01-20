<%@ page language="java" contentType="text/html; charset=utf-8"
import="by.yachnikzakhar.newsmanagement.beans.User" import="java.util.List" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/header_foooter.css">
    <link rel="stylesheet" href="./css/administration.css">
    <title>Administration</title>
</head>
<c:import url="/WEB-INF/jsp/blocks/header.jsp"/>

<div class="container formDel">
    <form action="/Controller?command?=delete_user_account"  method="post" autocomplete="off">
        <div class="container-fluid del_div">
            <span class="del">Удалить аккаунт пользователя по id</span> <input name ="del_id" type="number" min="0" class="num" placeholde="Введите id">
            <button type="submit" class="delete">Удалить</button>
        </div>
    </form>
</div>

<div class="container">
    <% if (request.getAttribute("users") != null && ((List
    <User>)request.getAttribute("users")).size() > 0) { %>
        <table>
            <thead>
            <tr>
                <th>id</th>
                <th>Логин</th>
                <th>ФИО</th>
                <th>Телефон</th>
            </tr>
            </thead>
            <tbody>
            <% List <User> users = (List<User>)request.getAttribute("users"); %>
                    <% for (User user : users) { %>
                    <tr>
                        <td><%= user.getId() %></td>
                        <td><%= user.getLogin() %></td>
                        <td><%= user.getFullName() %></td>
                        <td><%= user.getPhoneNumber() %></td>
                    </tr>
                    <% } %>
            </tbody>
        </table>
        <% } else { %>
        <h2>Зарегистрированных пользователей нет!!!</h2>
        <% } %>
</div>

<c:import url="/WEB-INF/jsp/blocks/footer.jsp"/>
</body>
</html>