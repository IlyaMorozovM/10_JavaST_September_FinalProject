<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Testing system : Questions</title>
</head>
<body>
<jsp:include page="nav-language.jsp"/>

<!-- content -->
<main>
    <h2><fmt:message key="label.browsingQuestions"/></h2>
    <h3><fmt:message key="label.role"/><i>`<c:out value="${user.roleName}"/>`</i>.</h3>
    <br/>

    <div>
        <c:forEach items="${questions}" var="question">

            <b><c:out value="${question.question}"/></b>
            <c:if test="${user.roleName == 'tutor'}">
                <button onclick="location.href='Controller?command=go_to_edit&entity=question&' +
                    'id=<c:out value="${question.questionId}"/>&text=<c:out value="${question.question}"/>'"><fmt:message key="button.edit"/></button>
                <button onclick="location.href='Controller?command=delete&entity=question&id=<c:out value="${question.questionId}"/>'"><fmt:message key="button.delete"/></button>
            </c:if>
            <br/>


            <!-- Answers -->
            <c:forEach items="${question.answers}" var="answer">
                <c:choose>
                    <c:when test="${answer.right}">
                        <p><font color="green"><c:out value="${answer.answer}"/></font></p>
                    </c:when>
                    <c:otherwise>
                        <p><c:out value="${answer.answer}"/></p>
                    </c:otherwise>
                </c:choose>
                <c:if test="${user.roleName == 'tutor'}">
                    <button onclick="location.href='Controller?command=go_to_edit&entity=answer&' +
                        'id=<c:out value="${answer.answerId}"/>&text=<c:out value="${answer.answer}"/>&isRight=<c:out value="${answer.right}"/>'"><fmt:message key="button.edit"/></button>
                    <button onclick="location.href='Controller?command=delete&entity=answer&id=<c:out value="${answer.answerId}"/>'"><fmt:message key="button.delete"/></button>
                </c:if>
                <br/>
            </c:forEach>

            <c:if test="${user.roleName == 'tutor'}">
                <div>
                    <br/>
                    <button onclick="location.href='Controller?command=go_to_add&entity=answer&id=<c:out value="${question.questionId}"/>'"><fmt:message key="button.addAnswer"/></button>
                </div>
            </c:if>
            <br/><br/>

        </c:forEach>
    </div>

    <c:if test="${user.roleName == 'tutor'}">
        <div>
            <br/>
            <button onclick="location.href='Controller?command=go_to_add&entity=question&id=<c:out value="${testId}"/>'"><fmt:message key="button.addQuestion"/></button>
        </div>
    </c:if>
    <br/>

    <div>    <!-- buttons holder -->
        <button onclick="location.href='Controller?command=go_to_tests'"><fmt:message key="button.back"/></button>
    </div>
</main>

<!-- footer -->
<footer>
    <jsp:include page="footer.jsp"/>
</footer>

</body>
</html>