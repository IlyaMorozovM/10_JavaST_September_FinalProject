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
    <style> <%@include file="../styles/bootstrap.css" %> </style>
    <link rel="shortcut icon" href="#">
</head>
<body class="p-3 mb-2 bg-info text-white">
    <c:set var="successSignup"><fmt:message key="label.successSignup"/></c:set>
    <c:set var="failLogin"><fmt:message key="label.failLogin"/></c:set>
    <custom:condMsg condition="${param.register == 'success'}" message="${successSignup}"/>
    <custom:condMsg condition="${param.signin == 'error'}" message="${failLogin}"/>

<%--    <div align="center"> <!-- login form -->--%>
    <figure class="text-center">
        <blockquote class="blockquote">
        <h3><fmt:message key="label.signin"/></h3>
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="signin"/>

            <br/>
            <label for="login"><fmt:message key="label.login"/></label>
            <input type="text" id="login" name="login"/>

            <br/>
            <label for="password"><fmt:message key="label.password"/></label>
            <input type="password" id="password" name="password"/>

            <br/>
            <input type="submit" value=<fmt:message key="button.signin"/> class="btn-dark" class="btn" />
        </form>
        </blockquote>
    </figure>
<%--    </div>--%>

</body>
</html>
