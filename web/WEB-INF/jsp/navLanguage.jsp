<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>

<%--<html>--%>
<%--<head>--%>
<%--    <style> <%@include file="../styles/bootstrap.css" %> </style>--%>
<%--</head>--%>
<%--<body>--%>

<%--    <ul class="nav justify-content-end">--%>
<%--        <li class="nav-item">--%>
<%--            <a class="nav-link active" aria-current="page" href="Controller?command=change_language&lang=en_EN&page=${pageContext.request.requestURL}">--%>
<%--                <fmt:message key="label.languageEn"/>--%>
<%--            </a>--%>
<%--        </li>--%>
<%--        <li class="nav-item">--%>
<%--            <a class="nav-link" aria-current="page" href="Controller?command=change_language&lang=ru_RU&page=${pageContext.request.requestURL}">--%>
<%--                <fmt:message key="label.languageRu"/>--%>
<%--            </a>--%>
<%--        </li>--%>
<%--    </ul>--%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <%--<nav class="navbar navbar-dark bg-dark">--%>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="Controller?command=change_language&lang=en_EN&page=${pageContext.request.requestURL}"><fmt:message key="label.languageEn"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="Controller?command=change_language&lang=ru_RU&page=${pageContext.request.requestURL}"><fmt:message key="label.languageRu"/></a>
            </li>
        </ul>
    </div>
</nav>


<%--</body>--%>
<%--</html>--%>
