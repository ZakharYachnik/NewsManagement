<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.footer.about_us.title" var="about_us_title"/>
<fmt:message bundle="${loc}" key="local.footer.about_us.text" var="about_us_text"/>
<fmt:message bundle="${loc}" key="local.footer.contacts.title" var="contacts_title"/>
<fmt:message bundle="${loc}" key="local.footer.contacts.address" var="contacts_address"/>
<fmt:message bundle="${loc}" key="local.footer.social.title" var="social_title"/>
<fmt:message bundle="${loc}" key="local.footer.navigation.title" var="navigation_title"/>
<fmt:message bundle="${loc}" key="local.footer.navigation.auth" var="auth_navigation"/>
<fmt:message bundle="${loc}" key="local.footer.navigation.registration" var="registration_navigation"/>
<fmt:message bundle="${loc}" key="local.footer.copywrite" var="copywrite"/>

<footer class="footer">
    <div class="container">
        <div class="footer__grid">
            <div class="footer__column">
                <h4 class="footer__title">${about_us_title}</h4>
                <p class="footer__text">${about_us_text}</p>
            </div>
            <div class="footer__column">
                <h4 class="footer__title">${contacts_title}</h4>
                <p class="footer__text">
                    <i class="fas fa-map-marker-alt"></i> ${contacts_address}
                </p>
                <p class="footer__text">
                    <i class="fas fa-phone"></i> +375 (33) 330-53-20
                </p>
            </div>
            <div class="footer__column">
                <h4 class="footer__title">${social_title}</h4>
                <ul class="footer__social">
                    <li><a href="https://instagram.com/zaherushka?igshid=ZGUzMzM3NWJiOQ=="><i class="fab fa-instagram">instagram: @zaharushka</i></a></li>
                    <li><a href="#"><i class="fab fa-linkedin-in"></i></a></li>
                </ul>
            </div>
            <div class="footer__column">
                <h4 class="footer__title">${navigation_title}</h4>
                <ul class="footer__nav">
                    <li><a href="#">${auth_navigation}</a></li>
                    <li><a href="#">${registration_navigation}</a></li>
                </ul>
            </div>
        </div>
        <p class="footer__copy">${copywrite}</p>
    </div>
</footer>

