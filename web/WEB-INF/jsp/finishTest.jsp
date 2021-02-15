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
    <c:url value="/styles/finishTest.css" var="cssFinishTestUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssFinishTestUrl}" />
    <c:url value="/img/icon.png" var="iconUrl"/>
    <link rel="shortcut icon" href="${iconUrl}" type="image/png">
</head>
<body class="text-center">
<jsp:include page="navLanguageAndSignOut.jsp"/>
<header class="my-header">
    <h3><fmt:message key="label.testCompleted"/></h3>
</header>

<main>
    <h4 class="my-header-result"><fmt:message key="label.yourReult"/></h4>

    <table class="table table-striped my-table">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="table.testState"/></th>
            <th scope="col"><fmt:message key="table.rightAnswers"/> / <c:out value="${numOfQuestions}"/></th>
            <th scope="col">% <fmt:message key="table.completion"/></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row"><fmt:message key="table.done"/></th>
            <td><c:out value="${rightAnswers}"/></td>
<%--            <td><c:out value="${Double.parseDouble(String.valueOf(rightAnswers)) / Double.parseDouble(String.valueOf(numOfQuestions)) * 100}"/>%</td>--%>
            <td><c:out value="${rightAnswers / numOfQuestions * 100}"/>%</td>
        </tr>
        </tbody>
    </table>

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
