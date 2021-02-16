<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="Controller?command=change_language&lang=en_EN&page=${pageContext.request.requestURL}"><fmt:message key="label.languageEn"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="Controller?command=change_language&lang=ru_RU&page=${pageContext.request.requestURL}"><fmt:message key="label.languageRu"/></a>
            </li>
        </ul>
        <ul class = "navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="Controller?command=signout"><fmt:message key="button.signOut"/></a>
            </li>
        </ul>
    </div>
</nav>
