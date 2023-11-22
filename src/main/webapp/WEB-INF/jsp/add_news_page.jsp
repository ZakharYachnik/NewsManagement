<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавить новость</title>
    <link rel="stylesheet" href="./css/header_foooter.css">
    <link rel="stylesheet" href="./css/add_news.css">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/css/bootstrap.min.css">
</head>
<body>
<header>
    <nav class="header_nav">
        <ul class="header_ul">
            <h1>News Management</h1>
            <li class="header_li"><a href="Controller?command=go_to_add_news" class="button add">Добавить новость</a>
            </li>
            <li class="header_li"><a href="Controller?command=go_to_sing_in" class="button login">Авторизация</a>
            </li>
            <li class="header_li"><a href="Controller?command=go_to_registration" class="button registration">Регистрация</a>
            </li>
        </ul>
    </nav>
</header>
<div class="container adding">
    <h1>Добавить новость</h1>
    <form>
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