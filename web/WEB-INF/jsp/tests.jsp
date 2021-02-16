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
    <c:url value="/styles/tests.css" var="cssTestsUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssTestsUrl}" >
    <c:url value="/img/icon.png" var="iconUrl"/>
    <link rel="shortcut icon" href="${iconUrl}" type="image/png">
</head>
<body class="text-center">

    <jsp:include page="navLanguageAndSignOut.jsp"/>

    <h2><fmt:message key="label.browsingTests"/></h2>
    <h4><fmt:message key="label.role"/><strong><c:out value="${user.roleName}"/></strong>.</h4>

    <br/>

<main role="main">

    <section class="jumbotron text-center">
        <div class="container">
            <h1 class="jumbotron-heading"><fmt:message key="header.tests"/></h1>
            <p class="lead text-muted"><fmt:message key="label.testDescription"/></p>

            <c:if test="${user.roleName == 'tutor'}">
                <p>
                    <a href="Controller?command=go_to_add&entity=test&id=<c:out value="${subjectId}"/>" class="btn btn-primary my-2"><fmt:message key="button.addTest"/></a>
                </p>
            </c:if>
        </div>
    </section>

    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row">
                <c:forEach items="${tests}" var="test">
                    <div class="col-md-4">
                        <div class="card mb-4 box-shadow">
                            <c:url value="/img/testPng1.jpeg" var="testImg"/>
                            <img class="card-img-top" src="${testImg}" alt="test image">
                            <div class="card-body">
                                <p class="card-text"><mark><strong><c:out value="${test.title.toUpperCase()}"/></strong></mark></p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <c:choose>
                                            <c:when test="${user.roleName == 'student'}">
                                                <button type="button" onclick="location.href='Controller?command=go_to_questions&testId=<c:out value="${test.testId}"/>'"
                                                        class="btn btn-sm btn-outline-secondary"><fmt:message key="button.start"/></button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="button" onclick="location.href='Controller?command=go_to_questions&testId=<c:out value="${test.testId}"/>'"
                                                        class="btn btn-sm btn-outline-secondary"><fmt:message key="button.view"/></button>
                                            </c:otherwise>
                                        </c:choose>
<%--                                        <button type="button" onclick="location.href='Controller?command=go_to_questions&testId=<c:out value="${test.testId}"/>'"--%>
<%--                                                class="btn btn-sm btn-outline-secondary"><fmt:message key="button.view"/></button>--%>
                                        <c:if test="${user.roleName == 'tutor'}">
                                            <button onclick="location.href='Controller?command=go_to_edit&entity=test&' +
                                                    'id=<c:out value="${test.testId}"/>&text=<c:out value="${test.title}"/>'"  class="btn btn-sm btn-outline-secondary"><fmt:message key="button.edit"/></button>
                                            <button onclick="location.href='Controller?command=go_to_results&testId=<c:out value="${test.testId}"/>' +
                                                    '&testTitle=<c:out value="${test.title}"/>'" class="btn btn-sm btn-outline-secondary"><fmt:message key="button.results"/></button>
                                        </c:if>
                                    </div>
                                        <%--                                        <small class="text-muted">9 mins</small>--%>

                                    <c:if test="${user.roleName == 'tutor'}">
                                        <c:set var="message"><fmt:message key="notification.delete"/></c:set>
                                        <form onSubmit='return confirm("${message}");' action="Controller" method="post">
                                            <input type="hidden" name="command" value="delete"/>
                                            <input type="hidden" name="entity" value="test"/>
                                            <input type="hidden" name="id" value=<c:out value="${test.testId}"/> />

                                            <button type="submit" name="delete" class="btn-danger"><fmt:message key="button.delete"/></button>

                                                <%--                                                    <input name=delete type=submit value=<fmt:message key="button.delete"/> class="btn-danger">--%>
                                        </form>
                                        <%--                <button onclick="location.href='Controller?command=delete&entity=subject&id=<c:out value="${subject.subjectId}"/>'" class="btn btn-danger"><fmt:message key="button.delete"/></button>--%>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:forEach>
            </div>
        </div>
    </div>
</main>

    <!-- pagination -->
    <jsp:include page="pagination.jsp"/>

    </br>
    <div>    <!-- buttons holder -->
        <button onclick="location.href='Controller?command=go_to_main'" class="btn btn-dark"><fmt:message key="button.back"/></button>
    </div>

<!-- footer -->
<footer>
    <jsp:include page="footer.jsp"/>
</footer>

</body>
</html>
