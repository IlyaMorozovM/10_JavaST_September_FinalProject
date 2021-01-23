<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent" />
<html>
<head>
    <style> <%@include file="../styles/bootstrap.css" %> </style>
<%--    <link rel="stylesheet" href="https://unpkg.com/papercss@1.8.1/dist/paper.css">--%>
</head>
<body class="p-3 mb-2 bg-info text-white">
<figure class="text-center">
    <blockquote class="blockquote">
        <p class="h3"><fmt:message key="header.welcome"/></p>
    </blockquote>
</figure>
</body>
</html>
