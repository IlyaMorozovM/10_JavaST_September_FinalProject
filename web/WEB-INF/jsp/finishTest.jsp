<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Test (results)</title>
    <c:url value="/styles/bootstrap.min.css" var="cssUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssUrl}" />
</head>
<body lass="badge bg-success">
<jsp:include page="navLanguageAndSignOut.jsp"/>
<header>
    <h3><fmt:message key="label.testCompleted"/></h3>
</header>

<main>
    <h4><fmt:message key="label.yourReult"/></h4>
    <p><b><c:out value="${rightAnswers}"/><fmt:message key="label.rightAnswers"/><c:out value="${numOfQuestions}"/></b></p>
    <br/>
    <br/>

    <button onclick="location.href='Controller?command=go_to_tests'" class="btn btn-dark"><fmt:message key="button.backToTests"/></button>
</main>

<!-- footer -->
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
