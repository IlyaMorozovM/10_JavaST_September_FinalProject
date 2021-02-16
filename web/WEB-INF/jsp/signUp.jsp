<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "custom" uri = "/WEB-INF/tld/conditionalMsg.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Sign Up to Testing system</title>
    <c:url value="/styles/bootstrap.min.css" var="cssUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssUrl}" />
    <c:url value="/styles/signUp.css" var="cssSignUpUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssSignUpUrl}" />
    <c:url value="/img/icon.png" var="iconUrl"/>
    <link rel="shortcut icon" href="${iconUrl}" type="image/png">
</head>
<body class="text-center">
<jsp:include page="navLanguageAndSignOut.jsp"/>

    <!-- header -->
    <header class="my-header">
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

        <!-- validation messages -->
        <div>
            <c:set var="successRegistration"><fmt:message key="label.successRegistration"/></c:set>
            <custom:condMsg condition="${param.register == 'success'}" message="${successRegistration}"/>
            <c:set var="invalidUserLogin"><fmt:message key="label.invalidLogin"/></c:set>
            <custom:condMsg condition="${param.error == 'login'}" message="${invalidUserLogin}"/>
            <c:set var="invalidUserEmail"><fmt:message key="label.invalidEmail"/></c:set>
            <custom:condMsg condition="${param.error == 'email'}" message="${invalidUserEmail}"/>
            <c:set var="invalidUserPassword"><fmt:message key="label.invalidPassword"/></c:set>
            <custom:condMsg condition="${param.error == 'password'}" message="${invalidUserPassword}"/>
            <c:set var="invalidUserName"><fmt:message key="label.invalidName"/></c:set>
            <custom:condMsg condition="${param.error == 'name'}" message="${invalidUserName}"/>
            <c:set var="invalidUserLastname"><fmt:message key="label.invalidLastname"/></c:set>
            <custom:condMsg condition="${param.error == 'lastname'}" message="${invalidUserLastname}"/>
            <c:set var="invalidUserRole"><fmt:message key="label.invalidRole"/></c:set>
            <custom:condMsg condition="${param.error == 'role'}" message="${invalidUserRole}"/>
        </div>

        <div> <!-- registration container -->
            <form action="Controller" method="post" class="col-lg-6 offset-lg-3">
                <fieldset class="form-group">
                    <legend class="col-form-label col-sm-2 my-label-signUp"><strong><fmt:message key="label.signupRole"/></strong></legend>
                    <div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="role" id="gridRadios1" value="2">
                            <label class="form-check-label" for="gridRadios1">
                                <fmt:message key="radio.student"/>
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="role" id="gridRadios2" value="1">
                            <label class="form-check-label" for="gridRadios2">
                                <fmt:message key="radio.tutor"/>
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="role" id="gridRadios3" value="3">
                            <label class="form-check-label" for="gridRadios3">
                                <fmt:message key="radio.admin"/>
                            </label>
                        </div>
                    </div>
                </fieldset>
                <input type="hidden" name="command" value="signup"/>
                    <div class="row">
                        <label for="name" class="my-label-signUp"><strong><fmt:message key="label.name"/></strong></label>
                        <input type="text" class="form-control" placeholder="<fmt:message key="label.name"/>" id="name" name="name" required/>
                        <small id="passwordHelpBlock3" class="form-text text-muted">
                            <fmt:message key="helpBlock.name"/>
                        </small>
                        <div class="invalid-feedback">
                            Please input name.
                        </div>
                    </div>
                    <div class="row">
                        <label for="lastname" class="my-label-signUp"><strong><fmt:message key="label.lastname"/></strong></label>
                        <input type="text" class="form-control" placeholder="<fmt:message key="label.lastname"/>" id="lastname" name="lastname" required/>
                        <small id="passwordHelpBlock2" class="form-text text-muted">
                            <fmt:message key="helpBlock.lastname"/>
                        </small>
                    </div>
                    <div class="row">
                        <label for="email" class="my-label-signUp"><strong><fmt:message key="label.email"/></strong></label>
                        <input type="text" class="form-control" id="email" placeholder="email@example.com" name="email" required/>
                        <div class="valid-feedback">
                            Looks good!
                        </div>
                    </div>
                    <div class="row">
                        <label for="login" class="my-label-signUp"><strong><fmt:message key="label.login"/></strong></label>
                        <input type="text" class="form-control" placeholder="<fmt:message key="label.login"/>" id="login" name="login" required/>
                        <small id="passwordHelpBlock4" class="form-text text-muted">
                            <fmt:message key="helpBlock.login"/>
                        </small>
                        <div class="valid-feedback">
                            Looks good!
                        </div>
                    </div>
                    <div class="row my-input-password">
                        <label for="password" class="my-label-signUp"><strong><fmt:message key="label.password"/></strong></label>
                        <input type="password" class="form-control" placeholder="<fmt:message key="label.password"/>" id="password" name="password" aria-describedby="passwordHelpBlock" required/>
                        <small id="passwordHelpBlock1" class="form-text text-muted">
                            <fmt:message key="helpBlock.password"/>
                        </small>
                    </div>

<%--                <label for="role"><fmt:message key="label.signupRole"/></label>--%>
<%--                <input type="number" id="role" name="role"/>--%>

                <button type="submit" class="btn btn-dark mb-2"><fmt:message key="button.signup"/></button>
<%--                <input type="submit" value=<fmt:message key="button.signup"/> class="btn-success" class="btn" />--%>
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
