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
</head>
<body class="text-center">
<jsp:include page="navLanguageAndSignOut.jsp"/>

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


<%--        <div> <!-- registration container -->--%>
<%--            <form action="Controller" method="post">--%>
<%--                <input type="hidden" name="command" value="signup"/>--%>

<%--                <br/>--%>
<%--                <label for="login"><fmt:message key="label.login"/></label>--%>
<%--                <input type="text" id="login" name="login"/>--%>

<%--                <br/>--%>
<%--                <label for="email"><fmt:message key="label.email"/></label>--%>
<%--                <input type="text" id="email" name="email"/>--%>

<%--                <br/>--%>
<%--                <label for="name"><fmt:message key="label.name"/></label>--%>
<%--                <input type="text" id="name" name="name"/>--%>

<%--                <br/>--%>
<%--                <label for="lastname"><fmt:message key="label.lastname"/></label>--%>
<%--                <input type="text" id="lastname" name="lastname"/>--%>

<%--                <br/>--%>
<%--                <label for="password"><fmt:message key="label.password"/></label>--%>
<%--                <input type="password" id="password" name="password"/>--%>

<%--                <br/>--%>
<%--                <label for="role"><fmt:message key="label.signupRole"/></label>--%>
<%--                <input type="number" id="role" name="role"/>--%>

<%--                <br/>--%>
<%--                <input type="submit" value=<fmt:message key="button.signup"/> class="btn-success" class="btn" />--%>
<%--            </form>--%>
<%--        </div>--%>

        <div> <!-- registration container -->
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="signup"/>
                <div class="form-row my-form-signUp">
                    <div class="col-md-3 mb-3">
                        <label for="login"><fmt:message key="label.login"/></label>
                        <input type="text" class="form-control" placeholder="<fmt:message key="label.login"/>" id="login" name="login" required/>
                        <div class="valid-feedback">
                            Looks good!
                        </div>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="email"><fmt:message key="label.email"/></label>
                        <input type="text" class="form-control" id="email" placeholder="email@example.com" name="email" required/>
                        <div class="valid-feedback">
                            Looks good!
                        </div>
                    </div>
                    <div class="col-md-3 mb-3">
                            <label for="name"><fmt:message key="label.name"/></label>
                            <input type="text" class="form-control" placeholder="<fmt:message key="label.name"/>" id="name" name="name" required/>
                            <div class="invalid-feedback">
                                Please input name.
                            </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-4 mb-3">
                        <label for="lastname"><fmt:message key="label.lastname"/></label>
                        <input type="text" class="form-control" placeholder="<fmt:message key="label.lastname"/>" id="lastname" name="lastname" required/>
                    </div>

                    <div class="col-md-4 mb-3">
                        <label for="password"><fmt:message key="label.password"/></label>
                        <input type="password" class="form-control" placeholder="<fmt:message key="label.password"/>" id="password" name="password" required/>
                        </div>
                </div>

<%--                <label for="role"><fmt:message key="label.signupRole"/></label>--%>
<%--                <input type="number" id="role" name="role"/>--%>

                <fieldset class="form-group">
                    <div class="row">
                        <legend class="col-form-label col-sm-2"><fmt:message key="label.signupRole"/></legend>
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
                    </div>
                </fieldset>

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
