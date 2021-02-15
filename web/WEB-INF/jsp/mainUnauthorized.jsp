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
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <c:url value="/styles/bootstrap.min.css" var="cssUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssUrl}" />
    <c:url value="/img/icon.png" var="iconUrl"/>
    <link rel="shortcut icon" href="${iconUrl}" type="image/png">
    <c:url value="/styles/mainUnauthorized.css" var="cssMainU"/>
    <link href="${cssMainU}" rel="stylesheet">
</head>
<%--<body class="p-3 mb-2 bg-info text-white">--%>
<body class="text-center">

    <!-- change language -->
    <jsp:include page="navLanguage.jsp"/>

    <!-- header -->
    <header>
        <jsp:include page="header.jsp"/>
    </header>

    <c:set var="successSignup"><fmt:message key="label.successSignup"/></c:set>
    <c:set var="failLogin"><fmt:message key="label.failLogin"/></c:set>
    <custom:condMsg condition="${param.register == 'success'}" message="${successSignup}"/>
    <custom:condMsg condition="${param.signin == 'error'}" message="${failLogin}"/>

<%--    <div align="center"> <!-- login form -->--%>
    <figure class="text-center">
        <blockquote class="blockquote">
        <h3 class="h3 mb-3 font-weight-normal my-signin"><fmt:message key="label.signin"/></h3>
        <form action="Controller" method="post" class="form-signin">
            <input type="hidden" name="command" value="signin"/>

            <label for="login" class="sr-only"><fmt:message key="label.login"/></label>
            <input type="text" id="login" name="login" class="form-control" placeholder="Login" required autofocus/>

            <label for="password" class="sr-only"><fmt:message key="label.password"/></label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Password" required/>

            <br/>
<%--            <input type="submit" value=<fmt:message key="button.signin"/> class="btn-dark" class="btn" />--%>
            <button  type="submit"  class="btn btn-lg btn-primary btn-block"><fmt:message key="button.signin"/></button>
        </form>
        </blockquote>
    </figure>
<%--    </div>--%>

    <!-- footer -->
    <footer>
        <jsp:include page="footer.jsp"/>
    </footer>

</body>
</html>
