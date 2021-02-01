<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Oops! Something went wrong :(</title>
    <c:url value="/styles/bootstrap.css" var="cssUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssUrl}" />
</head>
<body class="badge bg-danger">
<jsp:include page="nav-language.jsp"/>
    <h1><b><fmt:message key="error.unexpected"/></b></h1>
    <h2><i><fmt:message key="error.wrontRequest"/></i></h2>
</body>
</html>
