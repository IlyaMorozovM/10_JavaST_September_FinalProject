<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "custom" uri = "/WEB-INF/tld/conditionalMsg.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Testing system</title>
    <style> <%@include file="../styles/mainAuthorized.css" %> </style>
    <link rel="shortcut icon" href="#">
</head>
<body>
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
            <input type="submit" value="Sign In!" class="floating-button"/>
        </form>
    </div>

    <div align="center">    <!-- buttons holder -->
<%--        <button onclick="location.href='Controller?command=go_to_signup'" class="floating-button">Sign Up!</button>--%>
<%--        <a href="Controller?command=go_to_signup" class="floating-button">Sign up!</a>--%>
<%--        <a href='<c:url value="Controller?command=go_to_signup" />' class="floating-button">Sign up!</a>--%>
    </div>
</body>
</html>
