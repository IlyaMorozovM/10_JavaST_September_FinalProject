<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Test results</title>
    <style> <%@include file="../styles/bootstrap.css" %> </style>
    <link rel="shortcut icon" href="#">
</head>
<body class="p-3 mb-2 bg-info text-white">
<jsp:include page="nav-language.jsp"/>
    <h2><fmt:message key="label.browsingResults"/></h2>

    <div> <!-- find user form -->
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="go_to_results"/>
            <input type="hidden" name="isUserResult" value="true">

            <label for="login"><fmt:message key="label.findByLogin"/></label>
            <input type="text" id="login" name="login"/>

            <input type="submit" value=<fmt:message key="button.find"/> class="btn-success" />
        </form>
    </div>

    <div> <!-- find user form -->
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="go_to_results"/>
            <input type="hidden" name="isUserResult" value="false">

            <input type="submit" value=<fmt:message key="button.showAllResults" /> class="btn-success" />
        </form>
    </div>

    <c:choose>
        <c:when test="${userResults != null}">
            <c:set var="results" scope="session" value="${userResults}" />
        </c:when>
        <c:otherwise>
            <c:set var="results" scope="session" value="${allResults}" />
        </c:otherwise>
    </c:choose>

    <div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th><fmt:message key="table.studentLogin"/></th>
                <th><fmt:message key="table.pointsOf"/>${numOfQuestions})</th>
            </tr>
            </thead>

            <c:forEach items="${results}" var="result">
                <tbody>
                <tr>
                    <td> <c:out value="${result.studentLogin}"/></td>
                    <td> <c:out value="${result.points}"/></td>
                </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>

    <div>
        <button onclick="location.href='Controller?command=go_to_tests'" class="btn btn-dark"><fmt:message key="button.back"/></button>
    </div>

    <!-- footer -->
    <footer>
        <jsp:include page="footer.jsp"/>
    </footer>
</body>
</html>
