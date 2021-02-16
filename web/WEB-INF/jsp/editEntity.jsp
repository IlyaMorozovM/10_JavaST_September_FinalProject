<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Edit <c:out value="${entity}"/></title>
    <c:url value="/styles/bootstrap.min.css" var="cssUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssUrl}" />
    <c:url value="/styles/editEntity.css" var="cssEditUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssEditUrl}" />
    <c:url value="/img/icon.png" var="iconUrl"/>
    <link rel="shortcut icon" href="${iconUrl}" type="image/png">
</head>
<body class="text-center">
<jsp:include page="navLanguageAndSignOut.jsp"/>

<!-- content -->
<main>
    <form action="Controller" method="post" class="my-form-edit-entity">
        <input type="hidden" name="command" value="editEntity"/>
        <input type="hidden" name="entity" value="<c:out value="${entity}"/>">
        <input type="hidden" name="id" value="<c:out value="${id}"/>">
        <c:choose>
            <c:when test="${entity == 'subject'}">
                <input type="text" name="text" value="${text}" placeholder=<fmt:message key="placeholer.subjectName"/> />
            </c:when>
            <c:when test="${entity == 'test'}">
                <input type="text" name="text" value="${text}" placeholder=<fmt:message key="placeholer.testName"/> />
            </c:when>
            <c:when test="${entity == 'question'}">
                <textarea name="text" placeholder=<fmt:message key="placeholer.questionText"/>><c:out value="${text}"/></textarea>
            </c:when>
            <c:when test="${entity == 'answer'}">
                <textarea name="text" placeholder=<fmt:message key="placeholer.answerText"/>><c:out value="${text}"/></textarea>
            </c:when>
            <c:otherwise>
                <textarea name="text" placeholder=<fmt:message key="label.edit"/>"<c:out value="${entity}"/>"><c:out value="${text}"/></textarea>
            </c:otherwise>
        </c:choose>
        <c:if test="${entity == 'answer'}">
            <input type="checkbox" name="isRight" value="true" <c:if test="${isRight == 'true'}"><c:out value="checked"/></c:if>/>
        </c:if>
        <input type="submit" value=<fmt:message key="button.save"/> class="btn" class="btn-success">
    </form>

    <div>    <!-- buttons holder -->
        <c:choose>
            <c:when test="${entity == 'test'}">
                <button onclick="location.href='Controller?command=go_to_tests'" class="btn btn-dark"><fmt:message key="button.back"/></button>
            </c:when>
            <c:when test="${entity == 'subject'}">
                <button onclick="location.href='Controller?command=go_to_main'" class="btn btn-dark"><fmt:message key="button.back"/></button>
            </c:when>
            <c:otherwise>
                <button onclick="location.href='Controller?command=go_to_questions'" class="btn btn-dark"><fmt:message key="button.back"/></button>
            </c:otherwise>
        </c:choose>
        <%--            <button onclick="location.href='Controller?command=go_to_tests'">Back</button>--%>
    </div>

</main>

<!-- footer -->
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>