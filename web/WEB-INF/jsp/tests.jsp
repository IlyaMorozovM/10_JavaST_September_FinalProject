<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Available tests</title>
</head>
<body>

<!-- content -->
<main>
    <jsp:include page="nav-language.jsp"/>
    <h2><fmt:message key="label.browsingTests"/></h2>
    <h3><fmt:message key="label.role"/><i><c:out value="${user.roleName}"/></i>.</h3>
    <br/>

    <div>
        <c:forEach items="${tests}" var="test">
            <a href="Controller?command=go_to_questions&testId=<c:out value="${test.testId}"/>"><c:out value="${test.title}"/></a>
            <c:if test="${user.roleName == 'tutor'}">
                <button onclick="location.href='Controller?command=go_to_results&testId=<c:out value="${test.testId}"/>'"><fmt:message key="button.results"/></button>
                <button onclick="location.href='Controller?command=go_to_edit&entity=test&' +
                        'id=<c:out value="${test.testId}"/>&text=<c:out value="${test.title}"/>'"><fmt:message key="button.edit"/></button>
                <button onclick="location.href='Controller?command=delete&entity=test&id=<c:out value="${test.testId}"/>'"><fmt:message key="button.delete"/></button>
            </c:if>
            <br/><br/>
        </c:forEach>
    </div>
    <c:if test="${user.roleName == 'tutor'}">
        <div>
            <br/>
            <button onclick="location.href='Controller?command=go_to_add&entity=test&id=<c:out value="${subjectId}"/>'"><fmt:message key="button.addTest"/></button>
        </div>
    </c:if>
    <br/>
    <div>    <!-- buttons holder -->
        <button onclick="location.href='Controller?command=go_to_main'"><fmt:message key="button.back"/></button>
    </div>
</main>

<!-- footer -->
<footer>
    <jsp:include page="footer.jsp"/>
</footer>

</body>
</html>
