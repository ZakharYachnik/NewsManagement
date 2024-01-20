<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Form</title>
    <link rel="stylesheet" href="./css/sing_in_reg.css">
    <link rel="stylesheet" href="./css/header_foooter.css">

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.registration.title" var="registration_title"/>
    <fmt:message bundle="${loc}" key="local.registration.label.full_name" var="label_full_name"/>
    <fmt:message bundle="${loc}" key="local.registration.label.phone" var="label_phone"/>
    <fmt:message bundle="${loc}" key="local.registration.label.email" var="label_email"/>
    <fmt:message bundle="${loc}" key="local.registration.label.username" var="label_username"/>
    <fmt:message bundle="${loc}" key="local.registration.label.password" var="label_password"/>
    <fmt:message bundle="${loc}" key="local.registration.button.register" var="button_register"/>
    <fmt:message bundle="${loc}" key="local.error.validation_error" var="error_validation"/>
    <fmt:message bundle="${loc}" key="local.error.registration_error" var="error_registration"/>

    <title>${registration_title}</title>
</head>
<body>

<div class="container">
    <div class="registration-form">
        <a href="Controller?command=change_locale&locale=en" class="button registration ">EN</a>
        <a href="Controller?command=change_locale&locale=ru" class="button registration ">RU</a>
        <h1>${registration_title}</h1>
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="registration">

            <label for="full_name">${label_full_name}</label>
            <input type="text" id="full_name" name="full_name" required>

            <label for="phone">${label_phone}</label>
            <input type="tel" id="phone" name="phone" required>

            <label for="email">${label_email}</label>
            <input type="email" id="email" name="email" required>

            <label for="username">${label_username}</label>
            <input type="text" id="username" name="login" required>

            <label for="password">${label_password}</label>
            <input type="password" id="password" name="password" required>

            <input type="submit" id="button" value="${button_register}">
        </form>
        <c:if test="${not empty param.error}">
            <c:if test="${param.error eq 'validation_error'}">
                <p style="color: red;">${error_validation}</p>
            </c:if>
            <c:if test="${param.error eq 'registration_error'}">
                <p style="color: red;">${error_registration}</p>
            </c:if>
        </c:if>
    </div>
</div>
</body>
</html>
            
