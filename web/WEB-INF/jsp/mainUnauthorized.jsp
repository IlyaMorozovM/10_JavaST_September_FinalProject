<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix = "custom" uri = "/WEB-INF/tld/conditionalMsg.tld"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Testing system</title>
<%--    <style> <%@include file="../styles/mainAuthorized.css" %> </style>--%>
    <style> <%@include file="../styles/bootstrap.css" %> </style>
    <link rel="shortcut icon" href="#">
</head>
<body>
    <jsp:include page="nav-language.jsp"/>
    <custom:condMsg condition="${param.register == 'success'}" message="You have signed up successfully!"/>
    <custom:condMsg condition="${param.signin == 'error'}" message="Invalid login or password."/>

    <div align="center"> <!-- login form -->
        <h3>Sign In:</h3>
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="signin"/>

            <br/>
            <label for="login">Login:</label>
            <input type="text" id="login" name="login"/>

            <br/>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password"/>

            <br/>
            <input type="submit" value=<fmt:message key="button.signin"/> class="floating-button"/>
        </form>
    </div>

</body>
</html>
