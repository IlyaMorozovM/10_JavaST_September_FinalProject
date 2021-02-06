<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Available tests</title>
    <c:url value="/styles/bootstrap.min.css" var="cssUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssUrl}" />
    <c:url value="/styles/tests.css" var="cssTests"/>
    <link href="${cssTests}" rel="stylesheet">
</head>
<body class="text-center">

<!-- content -->
<main>
    <jsp:include page="navLanguageAndSignOut.jsp"/>
    <h2><fmt:message key="label.browsingTests"/></h2>
    <h4><fmt:message key="label.role"/><strong><c:out value="${user.roleName}"/></strong>.</h4>
    <br/>

    <div>
        <c:forEach items="${tests}" var="test">
            <button onclick="location.href='Controller?command=go_to_questions&testId=<c:out value="${test.testId}"/>'" class="btn btn-info"><c:out value="${test.title}"/></button>
<%--            <a href="Controller?command=go_to_questions&testId=<c:out value="${test.testId}"/>"><c:out value="${test.title}"/></a>--%>
            <c:if test="${user.roleName == 'tutor'}">
                <button onclick="location.href='Controller?command=go_to_results&testId=<c:out value="${test.testId}"/>&testTitle=<c:out value="${test.title}"/>'" class="btn btn-success"><fmt:message key="button.results"/></button>
                <button onclick="location.href='Controller?command=go_to_edit&entity=test&' +
                        'id=<c:out value="${test.testId}"/>&text=<c:out value="${test.title}"/>'" class="btn btn-primary"><fmt:message key="button.edit"/></button>
                <br/><br/>
                <c:set var="message"><fmt:message key="notification.delete"/></c:set>
                <form onSubmit='return confirm("${message}");' action="Controller" method="post">
                    <input type="hidden" name="command" value="delete"/>
                    <input type="hidden" name="entity" value="test"/>
                    <input type="hidden" name="id" value=<c:out value="${test.testId}"/> />

                    <input name=delete type=submit value=<fmt:message key="button.delete"/> class="btn-danger">
                </form>
<%--                <button onclick="location.href='Controller?command=delete&entity=test&id=<c:out value="${test.testId}"/>'" class="btn btn-danger"><fmt:message key="button.delete"/></button>--%>
            </c:if>
            <br/><br/>
        </c:forEach>
        <jsp:include page="pagination.jsp"/>
    </div>
    <c:if test="${user.roleName == 'tutor'}">
        <div>
            <br/>
            <button onclick="location.href='Controller?command=go_to_add&entity=test&id=<c:out value="${subjectId}"/>'" class="btn btn-info"><fmt:message key="button.addTest"/></button>
        </div>
    </c:if>
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
