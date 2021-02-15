<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Test (in progress)</title>
    <c:url value="/styles/bootstrap.min.css" var="cssUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssUrl}" />
    <c:url value="/styles/test.css" var="myCssTestUrl"/>
    <link rel="stylesheet" type="text/css" href="${myCssTestUrl}" />
    <c:url value="/img/icon.png" var="iconUrl"/>
    <link rel="shortcut icon" href="${iconUrl}" type="image/png">
</head>
<body class="text-center">
<jsp:include page="navLanguageAndSignOut.jsp"/>
<!-- header -->
<header class="my-header">
    <h3><fmt:message key="label.question"/><c:out value="${currQuestion + 1}"/><fmt:message key="label.outOf"/><c:out value="${numOfQuestions}"/></h3>
</header>

<main>
    <div class="form-inline justify-content-center my-head-question">
<%--        <h5><b><c:out value="${questions[currQuestion].question}"/></b></h5>--%>
        <b class="mx-sm-3 mb-3 my-question-text"><c:out value="${questions[currQuestion].question}"/></b>
    </div>
    <form action="Controller" method="post">
        <input type="hidden" name="command" value="next_question">
        <div class="my-form-answers">
            <c:choose>
                <c:when test="${questions[currQuestion].rightAnswers == 1}">
                    <c:forEach items="${questions[currQuestion].answers}" var="answer">
<%--                        <p><input type="radio" name="answer" value="<c:out value="${answer.answerId}"/>"/><c:out value="${answer.answer}"/></p>--%>
                <div class="form-inline my-form-question mx-sm-3 mb-3">
                    <input type="radio" name="answer" value="<c:out value="${answer.answerId}"/>"/><c:out value="${answer.answer}"/>
                </div>
                    </c:forEach>
                </c:when>

                <c:otherwise>
                    <c:forEach items="${questions[currQuestion].answers}" var="answer">
                        <div class="form-inline my-form-question mx-sm-3 mb-3">
    <%--                        <p><input type="checkbox" name="<c:out value="${answer.answerId}"/>" value="true"/><c:out value="${answer.answer}"/></p>--%>
                            <input type="checkbox" name="<c:out value="${answer.answerId}"/>" value="true"/><c:out value="${answer.answer}"/>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
        <br/>

        <c:choose>
            <c:when test="${currQuestion + 1 == numOfQuestions}">
                <input type="hidden" name="finishTest" value="true">
                <button type="submit" class="btn btn-dark mb-2"><fmt:message key="button.finishTest"/></button>
<%--                <input type="submit" value=<fmt:message key="button.finishTest"/> class="btn-success" class="btn" >--%>
            </c:when>
            <c:otherwise>
                <button type="submit" class="btn btn-dark mb-2"><fmt:message key="button.nextQuesstion"/></button>
<%--                <input type="submit" value=<fmt:message key="button.nextQuesstion"/> >--%>
            </c:otherwise>
        </c:choose>
    </form>
</main>



<!-- footer -->
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
