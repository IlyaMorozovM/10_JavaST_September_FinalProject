<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Add new <c:out value="${param.entity}"/></title>
</head>
<body>
<jsp:include page="nav-language.jsp"/>

    <!-- content -->
    <main>
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="addEntity"/>
            <input type="hidden" name="entity" value="<c:out value="${param.entity}"/>">
            <input type="hidden" name="id" value="<c:out value="${param.id}"/>"/>
            <c:choose>
                <c:when test="${param.entity == 'subject' || param.entity == 'test'}">
                    <input type="text" value=<fmt:message key="label.addNew"/>"<c:out value="${param.entity}"/>" name="name"/>
                </c:when>
                <c:otherwise>
                    <textarea name="name"><fmt:message key="label.addNew"/><c:out value="${param.entity}"/></textarea>
                </c:otherwise>
            </c:choose>
            <c:if test="${param.entity == 'answer'}">
                <input type="checkbox" name="isRight" value="true"/>
            </c:if>
            <input type="submit" value=<fmt:message key="button.add"/>>
        </form>

        <div>    <!-- buttons holder -->
            <c:choose>
                <c:when test="${param.entity == 'test'}">
                    <button onclick="location.href='Controller?command=go_to_tests'"><fmt:message key="button.back"/></button>
                </c:when>
                <c:when test="${param.entity == 'subject'}">
                    <button onclick="location.href='Controller?command=go_to_main'"><fmt:message key="button.back"/></button>
                </c:when>
                <c:otherwise>
                    <button onclick="location.href='Controller?command=go_to_questions'"><fmt:message key="button.back"/></button>
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
