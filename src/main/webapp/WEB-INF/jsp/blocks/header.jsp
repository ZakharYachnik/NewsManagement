<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header>
    <nav class="header_nav">
        <ul class="header_ul">
            <a href="Controller?command=go_to_main_page" style="text-decoration: none; color : white;">
                <h1>News Management</h1>
            </a>
            <c:if test="${empty sessionScope.userLogin}">
                <li class="header_li"><a href="Controller?command=go_to_sign_in"
                                         class="button auth login">Авторизация</a></li>
                <li class="header_li"><a href="Controller?command=go_to_registration" class="button registration">Регистрация</a>
                </li>
            </c:if>

            <c:if test="${not empty sessionScope.userLogin}">
                <li class="header_li"><a href="Controller?command=go_to_add_news" class="button add">Добавить
                    новость</a></li>

                <c:if test="${sessionScope.isAdmin}">
                    <li class="header_li"><a href="Controller?command=go_to_administration"
                                             class="button administration add">Администрирование</a></li>
                    <li class="header_li"><a href="Controller?command=go_to_logout"
                                             class="button logout-with-add exit login">Выйти</a></li>
                </c:if>

                <c:if test="${not sessionScope.isAdmin}">
                    <li class="header_li"><a href="Controller?command=logout"
                                             class="button logout-with-add login">Выйти</a></li>
                </c:if>
            </c:if>
        </ul>
    </nav>
</header>