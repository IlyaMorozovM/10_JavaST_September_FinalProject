<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>
<!DOCTYPE html>
<html>
<head>
    <title>Testing system</title>
    <style> <%@include file="../styles/bootstrap.css" %> </style>
</head>
<body class="p-3 mb-2 bg-info text-white">

    <h2><fmt:message key="label.welcome"/> <c:out value="${user.name}"/></h2>
    <h3><fmt:message key="label.role"/> <i><c:out value="${user.roleName}"/></i>.</h3>
    <br/>

    <div>
        <c:if test="${user.roleName == 'admin'}">
            <button onclick="location.href='Controller?command=go_to_signup'" class="btn btn-success"><fmt:message key="label.signUpUsers"/></button>
<%--            <a href='<c:url value="Controller?command=go_to_signup" />' class="floating-button">Sign up users!</a>--%>
            <br/><br/>

            <button onclick="location.href='Controller?command=go_to_delete_users'" class="btn btn-danger"><fmt:message key="label.deleteUsers"/></button>
<%--            <a href='<c:url value="Controller?command=go_to_signup" />' class="floating-button">Sign up users</a>--%>
            <br/><br/>
        </c:if>
    </div>

    <div>
        <c:forEach items="${subjects}" var="subject">
            <button onclick="location.href='Controller?command=go_to_tests&subjectId=<c:out value="${subject.subjectId}"/>'" class="btn btn-info"><c:out value="${subject.name}"/></button>
<%--            <a href="Controller?command=go_to_tests&subjectId=<c:out value="${subject.subjectId}"/>"><c:out value="${subject.name}"/></a>--%>
            <c:if test="${user.roleName == 'tutor'}">
                <button onclick="location.href='Controller?command=go_to_edit&entity=subject&' +
                                               'id=<c:out value="${subject.subjectId}"/>&text=<c:out value="${subject.name}"/>'" class="btn btn-primary"><fmt:message key="button.edit"/></button>
                <br/><br/>
                <c:set var="message"><fmt:message key="notification.delete"/></c:set>
                <form onSubmit='return confirm("${message}");' action="Controller" method="post">
                    <input type="hidden" name="command" value="delete"/>
                    <input type="hidden" name="entity" value="subject"/>
                    <input type="hidden" name="id" value=<c:out value="${subject.subjectId}"/> />

                    <input name=delete type=submit value=<fmt:message key="button.delete"/> class="btn-danger">
                </form>
<%--                <button onclick="location.href='Controller?command=delete&entity=subject&id=<c:out value="${subject.subjectId}"/>'" class="btn btn-danger"><fmt:message key="button.delete"/></button>--%>
            </c:if>
            <br/><br/>
        </c:forEach>
    </div>

    <c:if test="${user.roleName == 'tutor'}">
        <div>
            <br/>
            <button onclick="location.href='Controller?command=go_to_add&entity=subject'" class="btn btn-info"><fmt:message key="label.addSubject"/></button>
        </div>
    </c:if>
    <br/>
    <div>    <!-- buttons holder -->
        <button onclick="location.href='Controller?command=signout'"  class="btn btn-danger"><fmt:message key="button.signOut"/></button>
    </div>
</body>
</html>
