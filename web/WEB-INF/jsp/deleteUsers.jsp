<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Delete users</title>
</head>
<body class="p-3 mb-2 bg-info text-white">
<jsp:include page="nav-language.jsp"/>

<!-- content -->
<main>
    <div>
        <table class="table table-striped p-3 mb-2 bg-secondary text-white">
            <thead>
            <tr>
                <th><fmt:message key="table.login"/></th>
                <th><fmt:message key="table.name"/></th>
                <th><fmt:message key="table.lastname"/></th>
                <th><fmt:message key="table.role"/></th>
            </tr>
            </thead>
        <c:forEach items="${users}" var="user">
            <tbody>
            <tr>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.lastname}"/></td>
                <td><c:out value="${user.roleName}"/></td>
                <td><button onclick="location.href='Controller?command=delete&entity=user&id=<c:out value="${user.userId}"/>'" class="btn btn-danger"><fmt:message key="button.delete"/></button></td>
            </tr>
            </tbody>
<%--                <button onclick="location.href='Controller?command=delete&entity=user&id=<c:out value="${user.userId}"/>'">Delete</button>--%>
<%--            <br/><br/>--%>
        </c:forEach>
        </table>
    </div>
    <br/>
    <div>    <!-- buttons holder -->
        <button onclick="location.href='Controller?command=go_to_main'" class="btn btn-dark"><fmt:message key="button.back"/></button>
    </div>
</main>

<!-- footer -->
<footer>
    <jsp:include page="footer.jsp"/>
</footer>

</body>
</html>
