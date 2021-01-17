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
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Login</th>
                <th>Name</th>
                <th>Lastname</th>
                <th>Role</th>
            </tr>
            </thead>
        <c:forEach items="${users}" var="user">
            <tbody>
            <tr>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.lastname}"/></td>
                <td><c:out value="${user.roleName}"/></td>
                <td><button onclick="location.href='Controller?command=delete&entity=user&id=<c:out value="${user.userId}"/>'">Delete</button></td>
            </tr>
            </tbody>
<%--                <button onclick="location.href='Controller?command=delete&entity=user&id=<c:out value="${user.userId}"/>'">Delete</button>--%>
<%--            <br/><br/>--%>
        </c:forEach>
        </table>
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
