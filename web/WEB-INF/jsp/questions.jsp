<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Testing system: Questions</title>
    <c:url value="/styles/bootstrap.min.css" var="cssUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssUrl}" />
    <c:url value="/styles/questions.css" var="cssQuestionsUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssQuestionsUrl}" />
</head>
<body class="text-center">
<jsp:include page="navLanguageAndSignOut.jsp"/>

<!-- content -->
<main>
    <h2><fmt:message key="label.browsingQuestions"/></h2>
    <h4><fmt:message key="label.role"/><strong><c:out value="${user.roleName}"/></strong>.</h4>
    <br/>

    <div>
        <c:forEach items="${questions}" var="question">
            <div class="my-form-question">
            <b><c:out value="${question.question}"/></b>
            </div>
            <c:if test="${user.roleName == 'tutor'}">
                <button onclick="location.href='Controller?command=go_to_edit&entity=question&' +
                    'id=<c:out value="${question.questionId}"/>&text=<c:out value="${question.question}"/>'" class="btn btn-dark mb-2"><fmt:message key="button.edit"/></button>
                <br/><br/>
                <c:set var="message"><fmt:message key="notification.delete"/></c:set>
                <form onSubmit='return confirm("${message}");' action="Controller" method="post">
                    <input type="hidden" name="command" value="delete"/>
                    <input type="hidden" name="entity" value="question"/>
                    <input type="hidden" name="id" value=<c:out value="${question.questionId}"/> />

                    <input name=delete type=submit value=<fmt:message key="button.delete"/> class="btn-danger">
                </form>
<%--                <button onclick="location.href='Controller?command=delete&entity=question&id=<c:out value="${question.questionId}"/>'" class="btn btn-danger"><fmt:message key="button.delete"/></button>--%>
            </c:if>
            <br/>


            <!-- Answers -->
            <div class="list-group">
            <c:forEach items="${question.answers}" var="answer">
                <c:choose>
                    <c:when test="${answer.right}">
                         <p class="green_text" class="list-group-item list-group-item-action list-group-item-primary">
                            <c:out value="${answer.answer}"/>
                        </p>
                    </c:when>
                    <c:otherwise>
                        <c:out value="${answer.answer}"/>
                    </c:otherwise>
                </c:choose>
                <c:if test="${user.roleName == 'tutor'}">
                    <button onclick="location.href='Controller?command=go_to_edit&entity=answer&' +
                        'id=<c:out value="${answer.answerId}"/>&text=<c:out value="${answer.answer}"/>&isRight=<c:out value="${answer.right}"/>'" class="btn btn-primary"><fmt:message key="button.edit"/></button>
                    <br/><br/>
                    <c:set var="message"><fmt:message key="notification.delete"/></c:set>
                    <form onSubmit='return confirm("${message}");' action="Controller" method="post">
                        <input type="hidden" name="command" value="delete"/>
                        <input type="hidden" name="entity" value="answer"/>
                        <input type="hidden" name="id" value=<c:out value="${answer.answerId}"/> />

                        <input name=delete type=submit value=<fmt:message key="button.delete"/> class="btn-danger">
                    </form>
<%--                    <button onclick="location.href='Controller?command=delete&entity=answer&id=<c:out value="${answer.answerId}"/>'" class="btn btn-danger"><fmt:message key="button.delete"/></button>--%>
                </c:if>
                <br/>
            </c:forEach>
            </div>

            <c:if test="${user.roleName == 'tutor'}">
                <div>
                    <br/>
                    <button onclick="location.href='Controller?command=go_to_add&entity=answer&id=<c:out value="${question.questionId}"/>'" class="btn btn-info"><fmt:message key="button.addAnswer"/></button>
                </div>
            </c:if>
            <br/><br/>

        </c:forEach>
    </div>

    <c:if test="${user.roleName == 'tutor'}">
        <div>
            <br/>
            <button onclick="location.href='Controller?command=go_to_add&entity=question&id=<c:out value="${testId}"/>'" class="btn btn-info">
                <fmt:message key="button.addQuestion"/>
            </button>
        </div>
    </c:if>
    <br/>

    <div>    <!-- buttons holder -->
        <button onclick="location.href='Controller?command=go_to_tests'" class="btn btn-dark"><fmt:message key="button.back"/></button>
    </div>
</main>

<!-- footer -->
<footer>
    <jsp:include page="footer.jsp"/>
</footer>

</body>
</html>