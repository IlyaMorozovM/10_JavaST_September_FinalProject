<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Testing system</title>
<%--    <style> <%@include file="../styles/mainAuthorized.css" %> </style>--%>
    <style> <%@include file="../styles/bootstrap.css" %> </style>
    <style> <%@include file="../styles/bootstrap-theme.css" %> </style>
    <style> <%@include file="../styles/templatemo-style.css" %> </style>
</head>
<body>
    <h2>Welcome, <c:out value="${user.name}"/></h2>
    <h3>Your role is <i><c:out value="${user.roleName}"/></i>.</h3>
    <br/>

    <div>
        <c:if test="${user.roleName == 'admin'}">
            <button onclick="location.href='Controller?command=go_to_signup'" class="btn btn-info">Sign up users</button>
<%--            <a href='<c:url value="Controller?command=go_to_signup" />' class="floating-button">Sign up users!</a>--%>
            <br/><br/>

            <button onclick="location.href='Controller?command=go_to_delete_users'" class="btn btn-info">Delete users</button>
<%--            <a href='<c:url value="Controller?command=go_to_signup" />' class="floating-button">Sign up users</a>--%>
            <br/><br/>
        </c:if>
    </div>

    <div>
        <c:forEach items="${subjects}" var="subject">
            <a href="Controller?command=go_to_tests&subjectId=<c:out value="${subject.subjectId}"/>"><c:out value="${subject.name}"/></a>
            <c:if test="${user.roleName == 'tutor'}">
                <button onclick="location.href='Controller?command=go_to_edit&entity=subject&' +
                                               'id=<c:out value="${subject.subjectId}"/>&text=<c:out value="${subject.name}"/>'" class="btn btn-primary">Edit</button>
                <button onclick="location.href='Controller?command=delete&entity=subject&id=<c:out value="${subject.subjectId}"/>'" class="btn btn-primary">Delete</button>
            </c:if>
            <br/><br/>
        </c:forEach>
    </div>

    <c:if test="${user.roleName == 'tutor'}">
        <div>
            <br/>
            <button onclick="location.href='Controller?command=go_to_add&entity=subject'" class="btn btn-info">Add subject</button>
        </div>
    </c:if>
    <br/>
    <div>    <!-- buttons holder -->
        <button onclick="location.href='Controller?command=signout'"  class="btn btn-danger">Sign Out</button>
    </div>
</body>
</html>
