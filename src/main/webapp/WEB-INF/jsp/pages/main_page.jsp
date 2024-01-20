<%@ page language="java" contentType="text/html; charset=utf-8"
import="by.yachnikzakhar.newsmanagement.beans.News" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
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
<c:import url="/WEB-INF/jsp/blocks/header.jsp"/>

<c:if test="${empty sessionScope.userLogin}">
    <c:import url="/WEB-INF/jsp/blocks/guestInfo.jsp"/>
</c:if>

<c:if test="${not empty sessionScope.userLogin}">
    <c:import url="/WEB-INF/jsp/blocks/customerInfo.jsp"/>
</c:if>
<c:import url="/WEB-INF/jsp/blocks/footer.jsp"/>
</body>
</html>

