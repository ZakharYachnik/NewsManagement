<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Новость</title>
    <link rel="stylesheet" href="./css/header_foooter.css">
    <link rel="stylesheet" href="./css/new.css">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/css/bootstrap.min.css">
</head>
<body>

<c:import url="/WEB-INF/jsp/blocks/header.jsp"/>

<c:if test="${requestScope.presenter eq 'show_news'}">
    <c:import url="/WEB-INF/jsp/blocks/show_news.jsp"/>
</c:if>
<c:if test="${requestScope.presenter eq 'change_news'}">
    <c:import url="/WEB-INF/jsp/blocks/change_news.jsp"/>
</c:if>

<c:import url="/WEB-INF/jsp/blocks/footer.jsp"/>
</body>
</html>