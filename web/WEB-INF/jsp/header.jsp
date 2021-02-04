<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent" />
<c:url value="/styles/header.css" var="cssUrl"/>
<link rel="stylesheet" type="text/css" href="${cssUrl}" />
<%--<html>--%>
<%--<head>--%>
<%--    <style> <%@include file="../styles/bootstrap.css" %> </style>--%>
<%--    <link rel="stylesheet" href="../styles/bootstrap.css" />--%>
<%--<link rel="stylesheet" href="../styles/bootstrap.css" />--%>

<%--    <link rel="stylesheet" href="https://unpkg.com/papercss@1.8.1/dist/paper.css">--%>
<%--</head>--%>
<%--<body class="p-3 mb-2 bg-info text-white">--%>
<body>
<figure class="text-center my-header">
    <blockquote class="blockquote">
        <p class="h3"><fmt:message key="header.welcome"/> </p>
    </blockquote>
</figure>
<%--</body>--%>
<%--</html>--%>
