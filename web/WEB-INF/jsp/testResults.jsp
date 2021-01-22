<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Test results</title>
    <style> <%@include file="../styles/bootstrap.css" %> </style>
    <link rel="shortcut icon" href="#">
</head>
<body>
    <h2>Now browsing results</h2>

<%--    <div>--%>
<%--        <c:forEach items="${results}" var="result">--%>
<%--            <c:out value="${result.userId}"/>--%>
<%--            <c:out value="${result.points}"/>--%>
<%--            <br/><br/>--%>
<%--        </c:forEach>--%>
<%--    </div>--%>

    <div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Student login</th>
                <th>Points</th>
            </tr>
            </thead>
            <c:forEach items="${results}" var="result">
                <tbody>
                <tr>
                    <td> <c:out value="${result.userId}"/></td>
                    <td> <c:out value="${result.points}"/></td>
                </tr>
                </tbody>
                <%--                <button onclick="location.href='Controller?command=delete&entity=user&id=<c:out value="${user.userId}"/>'">Delete</button>--%>
                <%--            <br/><br/>--%>
            </c:forEach>
        </table>
    </div>

    <div>
        <button onclick="location.href='Controller?command=go_to_tests'">Back</button>
    </div>

    <!-- footer -->
    <footer>
        <jsp:include page="footer.jsp"/>
    </footer>
</body>
</html>
