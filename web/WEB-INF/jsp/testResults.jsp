<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Test results</title>
    <c:url value="/styles/bootstrap.min.css" var="cssUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssUrl}" />
    <c:url value="/styles/testResults.css" var="cssResultsUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssResultsUrl}" />
    <link rel="shortcut icon" href="#">
</head>
<body class="text-center">
<jsp:include page="navLanguageAndSignOut.jsp"/>
    <h2 class="my-header"><fmt:message key="label.browsingResults"/><c:out value="${testTitle}"/> </h2>

    <div> <!-- find user form -->
        <form action="Controller" method="post" class="form-inline justify-content-center ">
            <input type="hidden" name="command" value="go_to_results"/>
            <input type="hidden" name="isUserResult" value="true">

<%--            <c:set var="isUserResult" scope="session" value="true" />--%>
            <div class="form-group mx-sm-3 mb-2">
                <label for="login"><fmt:message key="label.findByLogin"/></label>
                <input type="text" class="form-control" placeholder="User login" id="login" name="login"/>
            </div>

            <button type="submit" class="btn btn-dark mb-2"><fmt:message key="button.find"/></button>
<%--            <input type="submit" value=<fmt:message key="button.find"/> class="btn-success" />--%>
        </form>
    </div>

    <div> <!-- find all users form -->
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="go_to_results"/>
            <input type="hidden" name="isUserResult" value="false">
<%--            <c:set var="isUserResult" scope="session" value="false" />--%>

            <div class="form-group mx-sm-3 mb-2">
                <button type="submit" class="btn btn-dark mb-2"><fmt:message key="button.showAllResults" /></button>
            </div>
<%--            <input type="submit" value=<fmt:message key="button.showAllResults" /> class="btn-success" />--%>
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
        <table class="table table-bordered">
            <thead>
            <tr class="table-dark">
                <th scope="col"><fmt:message key="table.studentLogin"/></th>
                <th scope="col"><fmt:message key="table.pointsOf"/>${numOfQuestions})</th>
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
    </br>

    <jsp:include page="paginationResults.jsp"/>
    </br>

    <div>
        <button onclick="location.href='Controller?command=go_to_tests'" class="btn btn-dark"><fmt:message key="button.back"/></button>
    </div>



    <!-- footer -->
    <footer>
        <jsp:include page="footer.jsp"/>
    </footer>
</body>
</html>
