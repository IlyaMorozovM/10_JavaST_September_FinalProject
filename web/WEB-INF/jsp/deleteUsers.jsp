<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Delete users</title>
</head>
<body>

<!-- content -->
<main>
    <div>
        <c:forEach items="${users}" var="user">
                <button onclick="location.href='Controller?command=delete&entity=user&id=<c:out value="${user.userId}"/>'">Delete</button>
            <br/><br/>
        </c:forEach>
    </div>
    <br/>
    <div>    <!-- buttons holder -->
        <button onclick="location.href='Controller?command=go_to_main'">Back</button>
    </div>
</main>

<!-- footer -->
<footer>
    <jsp:include page="footer.jsp"/>
</footer>

</body>
</html>
