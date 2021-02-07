<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Add new <c:out value="${param.entity}"/></title>
    <c:url value="/styles/bootstrap.min.css" var="cssUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssUrl}" />
    <c:url value="/styles/addEntity.css" var="cssAddUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssAddUrl}" />
</head>
<body class="text-center">
<jsp:include page="navLanguageAndSignOut.jsp"/>

    <!-- content -->
    <main>
        <form action="Controller" method="post" class="my-form-add-entity">
            <input type="hidden" name="command" value="addEntity"/>
            <input type="hidden" name="entity" value="<c:out value="${param.entity}"/>">
            <input type="hidden" name="id" value="<c:out value="${param.id}"/>"/>
            <c:choose>
                <c:when test="${param.entity == 'subject'}">
                    <input type="text" placeholder=<fmt:message key="placeholer.subjectName"/> name="name"/>
                </c:when>
                <c:when test="${param.entity == 'test'}">
                    <input type="text" placeholder=<fmt:message key="placeholer.testName"/> name="name"/>
                </c:when>
                <c:when test="${param.entity == 'question'}">
                    <textarea name="name" placeholder=<fmt:message key="placeholer.questionText"/>></textarea>
                </c:when>
                <c:when test="${param.entity == 'answer'}">
                    <textarea name="name" placeholder=<fmt:message key="placeholer.answerText"/>></textarea>
                </c:when>
                <c:otherwise>
                    <textarea name="name" placeholder=<fmt:message key="label.addNew"/>"<c:out value="${param.entity}"/>"></textarea>
                </c:otherwise>

            </c:choose>
            <c:if test="${param.entity == 'answer'}">
                <input type="checkbox" name="isRight" value="true"/>
            </c:if>
            </br></br>
            <input type="submit" value=<fmt:message key="button.add"/> class="btn" class="btn-info">
        </form>

        </br></br>

        <div>    <!-- buttons holder -->
            <c:choose>
                <c:when test="${param.entity == 'test'}">
                    <button onclick="location.href='Controller?command=go_to_tests'" class="btn btn-dark"><fmt:message key="button.back"/></button>
                </c:when>
                <c:when test="${param.entity == 'subject'}">
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
