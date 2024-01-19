<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
    <c:forEach var="news" items="${requestScope.newsList}">
        <a href="Controller?command=go_to_sign_in" class="a-news">
            <div class="row g-0">
                <div class="col-md-4">
                        <img src="./resources/news1.jpg" class="img-fluid"
                             alt="PHOTO">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h2 class="card-title">
                            <c:out value="${news.getTitle()}"/>
                        </h2>
                        <p class="card-text">
                            <c:out value="${news.getBrief()}"/>
                        </p>
                    </div>
                </div>
            </div>
        </a>
    </c:forEach>
</div>