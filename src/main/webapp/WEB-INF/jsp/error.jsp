<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/error.css">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/css/bootstrap.min.css">
    <title>Error Page</title>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.error.message" var="error_message"/>
    <fmt:message bundle="${loc}" key="local.error.link.go_to_main_page" var="main_page_link"/>
</head>
<body>
<div class="container">
    <h1>Error</h1>
    <p>
        ${error_message}
    </p>
    <a href="Controller?command=go_to_main_page">${main_page_link}</a>
</div>
</body>
</html>
