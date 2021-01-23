<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Oops! Something went wrong :(</title>
</head>
<body>
<jsp:include page="nav-language.jsp"/>
    <h1><b><fmt:message key="error.unexpected"/></b></h1>
    <h2><i><fmt:message key="error.wrontRequest"/></i></h2>
</body>
</html>
