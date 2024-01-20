<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.header.button.login" var="button_login"/>
<fmt:message bundle="${loc}" key="local.header.button.registration" var="button_registration"/>
<fmt:message bundle="${loc}" key="local.header.button.add_news" var="button_add_news"/>
<fmt:message bundle="${loc}" key="local.header.button.administration" var="button_administration"/>
<fmt:message bundle="${loc}" key="local.header.button.sign_out" var="button_sign_out"/>

<header>
    <nav class="header_nav">
        <ul class="header_ul">
            <a href="Controller?command=go_to_main_page" style="text-decoration: none; color: white;">
                <h1>News Management</h1>
            </a>
            <c:if test="${empty sessionScope.userLogin}">
                <li class="header_li"><a href="Controller?command=go_to_sign_in" class="button auth login">${button_login}</a></li>
                <li class="header_li"><a href="Controller?command=go_to_registration" class="button registration">${button_registration}</a></li>
            </c:if>

            <c:if test="${not empty sessionScope.userLogin}">

                <c:if test="${sessionScope.isAdmin}">
                    <li class="header_li"><a href="Controller?command=go_to_add_news" class="button add">${button_add_news}</a></li>
                    <li class="header_li"><a href="Controller?command=go_to_administration" class="button administration add">${button_administration}</a></li>
                    <li class="header_li"><a href="Controller?command=logout" class="button logout-with-add exit login">${button_sign_out}</a></li>
                </c:if>

                <c:if test="${not sessionScope.isAdmin}">
                    <li class="header_li"><a href="Controller?command=logout" class="button logout-without-add login">${button_sign_out}</a></li>
                </c:if>
            </c:if>

            <li class="header_li locale"><a href="Controller?command=change_locale&locale=en" class="button registration ">EN</a></li>
            <li class="header_li locale"><a href="Controller?command=change_locale&locale=ru" class="button registration ">RU</a></li>
        </ul>
    </nav>
</header>