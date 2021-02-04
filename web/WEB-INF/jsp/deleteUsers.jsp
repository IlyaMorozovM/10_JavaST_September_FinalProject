<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix = "custom" uri = "/WEB-INF/tld/conditionalMsg.tld"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Delete users</title>
    <c:url value="/styles/bootstrap.min.css" var="cssUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssUrl}" />
</head>
<body class="p-3 mb-2 bg-info text-white">
<jsp:include page="navLanguageAndSignOut.jsp"/>

<!-- content -->
<main>
    <c:set var="failDelete"><fmt:message key="error.unexpected"/></c:set>
    <c:set var="success"><fmt:message key="label.success"/></c:set>
    <custom:condMsg condition="${param.error == 'user'}" message="${failDelete}"/>
    <custom:condMsg condition="${param.delete == 'success'}" message="${success}"/>

    <div> <!-- find user form -->
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="go_to_delete_users"/>
            <input type="hidden" name="showOneUser" value="true">

            <label for="login"><fmt:message key="label.findByLogin"/></label>
            <input type="text" id="login" name="login"/>

            <input type="submit" value=<fmt:message key="button.find"/> class="btn-success" />
        </form>
    </div>

    <div> <!-- find user form -->
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="go_to_delete_users"/>
            <input type="hidden" name="showOneUser" value="false">

            <input type="submit" value=<fmt:message key="button.showAllUsers" /> class="btn-success" />
        </form>
    </div>

    <c:choose>
        <c:when test="${oneUser != null}">
            <c:set var="users" scope="session" value="${oneUser}" />
        </c:when>
        <c:otherwise>
            <c:set var="users" scope="session" value="${allUsers}" />
        </c:otherwise>
    </c:choose>


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
<%--                <td><button onclick="location.href='Controller?command=delete&entity=user&id=<c:out value="${user.userId}"/>'" class="btn btn-danger"><fmt:message key="button.delete"/></button></td>--%>
                <td>
                    <c:set var="message"><fmt:message key="notification.deleteUser"/></c:set>
                    <form onSubmit='return confirm("${message}");' action="Controller" method="post">
                        <input type="hidden" name="command" value="delete"/>
                        <input type="hidden" name="entity" value="user"/>
                        <input type="hidden" name="id" value=<c:out value="${user.userId}"/> />

                        <input name=delete type=submit value=<fmt:message key="button.delete"/> class="btn-danger">
                    </form>
                </td>
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
