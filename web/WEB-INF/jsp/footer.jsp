<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <style> <%@include file="../styles/bootstrap.css" %> </style>
<%--    <link rel="stylesheet" href="https://unpkg.com/papercss@1.8.1/dist/paper.css">--%>
</head>
<body>
<%--    <h3>Copyright Ilya Morozov 2020.</h3>--%>
    <fmt:message key="footer.copyright"/>
</body>
</html>
