<%@ page language="java" contentType="text/html; charset=utf-8"
import="by.yachnikzakhar.newsmanagement.beans.User" import="java.util.List" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/header_foooter.css">
    <link rel="stylesheet" href="./css/administrationn.css">
    <title>Administration</title>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="local.administration.block_user" var="block_user_text"/>
    <fmt:message bundle="${loc}" key="local.administration.input_id" var="input_id_placeholder"/>

    <fmt:message bundle="${loc}" key="local.administration.table.id" var="table_id_text"/>
    <fmt:message bundle="${loc}" key="local.administration.table.login" var="table_login_text"/>
    <fmt:message bundle="${loc}" key="local.administration.table.full_name" var="table_full_name_text"/>
    <fmt:message bundle="${loc}" key="local.administration.table.phone_number" var="table_phone_number_text"/>
    <fmt:message bundle="${loc}" key="local.administration.table.status" var="table_status_text"/>

    <fmt:message bundle="${loc}" key="local.administration.no_registered_users" var="no_registered_users_text"/>

    <fmt:message bundle="${loc}" key="local.header.button.login" var="button_login"/>
    <fmt:message bundle="${loc}" key="local.header.button.registration" var="button_registration"/>
    <fmt:message bundle="${loc}" key="local.header.button.add_news" var="button_add_news"/>
    <fmt:message bundle="${loc}" key="local.header.button.administration" var="button_administration"/>
    <fmt:message bundle="${loc}" key="local.header.button.sign_out" var="button_sign_out"/>
</head>
<c:import url="/WEB-INF/jsp/blocks/header.jsp"/>

<div class="container formDel">
    <form action="Controller?command=block_user" method="post" autocomplete="off">
        <div class="container-fluid del_div">
            <span class="del">${block_user_text}</span>
            <input name="id" type="number" min="0" class="num" placeholder="${input_id_placeholder}">
            <button type="submit" class="delete">Заблокировать</button>
        </div>
    </form>
</div>

<div class="container">
    <c:if test="${not empty users}">
        <table>
            <thead>
            <tr>
                <th>${table_id_text}</th>
                <th>${table_login_text}</th>
                <th>${table_full_name_text}</th>
                <th>${table_phone_number_text}</th>
                <th>${table_status_text}</th>
                <c:if test="${sessionScope.userLogin eq 'admin'}">
                    <th>Администрирование</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.login}</td>
                    <td>${user.fullName}</td>
                    <td>${user.phoneNumber}</td>
                    <td>${user.status}</td>
                    <c:if test="${sessionScope.userLogin eq 'admin'}">
                        <td style="text-align: center">
                            <c:if test="${not user.isAdmin()}">
                                <form action="Controller?command=add_user_admin_role" method="post">
                                    <input type="hidden" name="user_id" value="${user.id}">
                                    <input type="submit" value="Сделать администратором">
                                </form>
                            </c:if>
                            <c:if test="${user.isAdmin()}">
                                <form action="Controller?command=remove_user_admin_role" method="post">
                                    <input type="hidden" name="user_id" value="${user.id}">
                                    <input type="submit" value="Убрать роль администратора">
                                </form>
                            </c:if>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty users}">
        <h2>${no_registered_users_text}</h2>
    </c:if>
</div>


<c:import url="/WEB-INF/jsp/blocks/footer.jsp"/>
</body>
</html>