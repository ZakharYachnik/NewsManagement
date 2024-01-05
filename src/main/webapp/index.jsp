<%@ page language="java" contentType="text/html; charset=utf-8"
import="by.yachnikzakhar.newsmanagement.beans.User" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en" >
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main page</title>
    <link rel="stylesheet" href="./css/header_foooter.css">
    <link rel="stylesheet" href="./css/main_page.css">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/css/bootstrap.min.css">
</head>
<body>
<header>
    <nav class="header_nav">
        <ul class="header_ul">
            <h1>News Management</h1>

            <% if (request.getSession(false) == null || request.getSession().getAttribute("userId") == null) { %>
            <li class="header_li"><a href="Controller?command=go_to_add_news" class="button add">Добавить новость</a>
            </li>
            <li class="header_li"><a href="Controller?command=go_to_sign_in" class="button login">Авторизация</a></li>
            <li class="header_li"><a href="Controller?command=go_to_registration" class="button registration">Регистрация</a>
            </li>
            <% } else { %>
            <li class="header_li"><a href="Controller?command=go_to_add_news" class="button add">Добавить новость</a>
            </li>
            <% if ((Boolean) request.getSession().getAttribute("isAdmin")) { %>
            <li class="header_li"><a href="Controller?command=go_to_administration" class="button administration add">Администрирование</a></li>
            <li class="header_li"><a href="Controller?command=logout" class="button logout-with-add login">Выйти</a></li>
            <% } else {%>
            <li class="header_li"><a href="Controller?command=logout" class="button logout-without-add login">Выйти</a></li>
            <% } %>
            <% } %>
        </ul>
    </nav>
</header>

<div class="container">
    <a href="Controller?command=show_news&id=0" class="a-news">
        <div class="row g-0">
            <div class="col-md-4">
                <img src="./resources/news1.jpg" class="img-fluid"
                     alt="Фотография новости 1">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h2 class="card-title">В мире нашли самого милого котика</h2>
                    <p class="card-text">Ученые объявили о находке самого милого
                        котика на Земле. Его милые черты и игривый характер покорили
                        сердца многих.</p>
                </div>
            </div>
        </div>
    </a>
    <a href="Controller?command=show_news&id=1" class="a-news">
        <div class="row g-0">
            <div class="col-md-4">
                <img src="./resources/news2.jpg" class="img-fluid"
                     alt="Фотография новости 2">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h2 class="card-title">Ученые открыли новый вид растения</h2>
                    <p class="card-text">Ученые сообщили о своем открытии нового
                        вида растения, которое обладает уникальными свойствами и может
                        использоваться в медицине.</p>
                </div>
            </div>
        </div>
    </a>
    <a href="Controller?command=show_news&id=2" class="a-news">
        <div class="row g-0">
            <div class="col-md-4">
                <img src="./resources/new3.jpg" class="img-fluid"
                     alt="Фотография новости 3">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h2 class="card-title">Новый фильм стал блокбастером</h2>
                    <p class="card-text">Вышедший недавно фильм собрал огромную
                        аудиторию зрителей и стал настоящим хитом сезона. Критики отмечают
                        его высокое качество и захватывающий сюжет.</p>
                </div>
            </div>
        </div>
    </a>
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
        <p class="footer__copy">&copy; 2023 NewsManagement. Все права защищены.</p>
    </div>
</footer>
</body>
</html>

