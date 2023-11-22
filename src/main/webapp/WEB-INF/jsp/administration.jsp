<%@ page language="java" contentType="text/html; charset=utf-8"
import="by.yachnikzakhar.beans.User" import="java.util.List" pageEncoding="utf-8"%>
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
<header>
    <nav class="header_nav">
        <ul class="header_ul">
            <h1>News Management</h1>
            <li class="header_li"><a href="Controller?command=go_to_add_news" class="button add">Добавить
                новость</a>
            </li>
            <li class="header_li"><a href="Controller?command=administration" class="button administration add">Администрирование</a>
            </li>
            <li class="header_li"><a href="Controller?command=logout" class="button logout-with-add login">Выйти</a>
            </li>
        </ul>
    </nav>
</header>

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

<footer class="footer">
    <div class="container">
        <div class="footer__grid">
            <div class="footer__column">
                <h4 class="footer__title">О нас</h4>
                <p class="footer__text">Наш сайт - это инновационная платформа,
                    предназначенная для эффективного управления новостным контентом.</p>
            </div>
            <div class="footer__column">
                <h4 class="footer__title">Контакты</h4>
                <p class="footer__text">
                    <i class="fas fa-map-marker-alt"></i> Минск, Беларусь
                </p>
                <p class="footer__text">
                    <i class="fas fa-phone"></i> +375 (33) 330-53-20
                </p>
            </div>
            <div class="footer__column">
                <h4 class="footer__title">Социальные сети</h4>
                <ul class="footer__social">
                    <li><a
                            href="https://instagram.com/zaherushka?igshid=ZGUzMzM3NWJiOQ=="><i
                            class="fab fa-instagram">instagram: @zaharushka</i></a></li>
                    <li><a href="#"><i class="fab fa-linkedin-in"></i></a></li>
                </ul>
            </div>
            <div class="footer__column">
                <h4 class="footer__title">Навигация</h4>
                <ul class="footer__nav">
                    <li><a href="#">Авторизация</a></li>
                    <li><a href="#">Регистрация</a></li>
                </ul>
            </div>
        </div>
        <p class="footer__copy">&copy; 2023 NewsManagment. Все права защищены.</p>
    </div>
</footer>
</body>
</html>