<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "custom" uri = "/WEB-INF/tld/conditionalMsg.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Sign Up to Testing system</title>
    <c:url value="/styles/bootstrap.css" var="cssUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssUrl}" />
</head>
<body class="p-3 mb-2 bg-info text-white">
<jsp:include page="nav-language.jsp"/>

    <!-- header -->
    <header>
        <jsp:include page="header.jsp"/>
    </header>

    <!-- main -->
    <main>

        <c:if test="${param.error == 'error'}">
            <h2><fmt:message key="error.something"/></h2>
        </c:if>
        <c:if test="${param.error == 'unique'}">
            <h2><fmt:message key="error.unique"/></h2>
        </c:if>
        <c:set var="successRegistration"><fmt:message key="label.successRegistration"/></c:set>
        <custom:condMsg condition="${param.register == 'success'}" message="${successRegistration}"/>


        <div> <!-- registration container -->
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="signup"/>

                <br/>
                <label for="login"><fmt:message key="label.login"/></label>
                <input type="text" id="login" name="login"/>

                <br/>
                <label for="email"><fmt:message key="label.email"/></label>
                <input type="text" id="email" name="email"/>

                <br/>
                <label for="name"><fmt:message key="label.name"/></label>
                <input type="text" id="name" name="name"/>

                <br/>
                <label for="lastname"><fmt:message key="label.lastname"/></label>
                <input type="text" id="lastname" name="lastname"/>

                <br/>
                <label for="password"><fmt:message key="label.password"/></label>
                <input type="password" id="password" name="password"/>

                <br/>
                <label for="role"><fmt:message key="label.signupRole"/></label>
                <input type="number" id="role" name="role"/>

                <br/>
                <input type="submit" value=<fmt:message key="button.signup"/> class="btn-success" class="btn" />
            </form>
        </div>

        <div>    <!-- buttons holder -->
            <button onclick="location.href='Controller?command=go_to_main&signin=success'" class="btn" class="btn-dark" class="btn-lg"><fmt:message key="button.back"/></button>
        </div>
    </main>

    <!-- footer -->
    <footer>
        <jsp:include page="footer.jsp"/>
    </footer>
</body>
</html>
