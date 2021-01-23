<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Test (results)</title>
<%--    <link rel="stylesheet" href="https://unpkg.com/papercss@1.8.1/dist/paper.css">--%>
</head>
<body>
<jsp:include page="nav-language.jsp"/>
<header>
    <h3><fmt:message key="label.testCompleted"/></h3>
</header>

<main>
    <h4><fmt:message key="label.yourReult"/></h4>
    <p><b><c:out value="${rightAnswers}"/><fmt:message key="label.rightAnswers"/><c:out value="${numOfQuestions}"/></b></p>
    <br/>
    <br/>

    <button onclick="location.href='Controller?command=go_to_tests'"><fmt:message key="button.backToTests"/></button>
</main>

<!-- footer -->
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
