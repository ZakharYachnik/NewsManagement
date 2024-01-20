<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Authorization</title>
    <link rel="stylesheet" href="./css/sing_in_reg.css">
    <link rel="stylesheet" href="./css/header_foooter.css">

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.sing_in.title" var="sing_in_title"/>
    <fmt:message bundle="${loc}" key="local.sing_in.label.login" var="label_login"/>
    <fmt:message bundle="${loc}" key="local.sing_in.label.password" var="label_password"/>
    <fmt:message bundle="${loc}" key="local.sing_in.button.login" var="button_login"/>
    <fmt:message bundle="${loc}" key="local.sing_in.link.registration" var="link_registration"/>
    <fmt:message bundle="${loc}" key="local.sing_in.error.invalid_data" var="error_invalid_data"/>
</head>
<body>

<div class="container">
    <div class="registration-form">
        <a href="Controller?command=change_locale&locale=en" class="button registration ">EN</a>
        <a href="Controller?command=change_locale&locale=ru" class="button registration ">RU</a>
        <h1>${sing_in_title}</h1>
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="sign_in">

            <label for="username">${label_login}</label>
            <input type="text" id="username" name="login" required>

            <label for="password">${label_password}</label>
            <input type="password" id="password" name="password" required>

            <input type="submit" id="button" value="${button_login}">
            <a href="Controller?command=go_to_registration" class="registration-link">${link_registration}</a>
        </form>
    </div>
    <c:if test="${not empty param.error}">
        <div id="error-message">
            <p style="color: red; font-weight: bold">${error_invalid_data}</p>
        </div>
    </c:if>
</div>
</body>
</html>
            
