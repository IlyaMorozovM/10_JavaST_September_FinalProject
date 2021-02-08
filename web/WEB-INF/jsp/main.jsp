<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>
<!DOCTYPE html>
<html>
<head>
    <title>Testing system</title>
    <c:url value="/styles/bootstrap.min.css" var="cssUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssUrl}" />
    <c:url value="/styles/main.css" var="cssMain"/>
    <link href="${cssMain}" rel="stylesheet">
</head>
<body class="text-center">

    <!-- change language -->
    <jsp:include page="navLanguageAndSignOut.jsp"/>

    <!-- header -->
    <header>
        <jsp:include page="header.jsp"/>
    </header>

    <h2><fmt:message key="label.welcome"/> <c:out value="${user.name}"/></h2>
    <h4><fmt:message key="label.role"/><strong><c:out value="${user.roleName}"/></strong>.</h4>
    <br/>

    <c:if test="${user.roleName == 'admin'}">
        <p>
            <a href="<c:url value="Controller?command=go_to_signup" />" class="btn btn-primary my-2"><fmt:message key="label.signUpUsers"/></a>
            <a href="<c:url value="Controller?command=go_to_delete_users" />" class="btn btn-secondary my-2"><fmt:message key="label.deleteUsers"/></a>
        </p>
    </c:if>

    <main role="main">

        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading"><fmt:message key="header.subjects"/></h1>
                <p class="lead text-muted"><fmt:message key="label.subjectsDescription"/></p>

                <c:if test="${user.roleName == 'tutor'}">
                    <p>
                        <a href="<c:url value="Controller?command=go_to_add&entity=subject" />" class="btn btn-primary my-2"><fmt:message key="label.addSubject"/></a>
                    </p>
                </c:if>
            </div>
        </section>

        <div class="album py-5 bg-light">
            <div class="container">
                <div class="row">
                <c:forEach items="${subjects}" var="subject">
                                <div class="col-md-4">
                                    <div class="card mb-4 box-shadow">
                                        <c:url value="/img/testPng1.jpeg" var="testImg"/>
                                        <img class="card-img-top" src="${testImg}" alt="subject image">
                                        <div class="card-body">
                                            <p class="card-text"><mark><strong><c:out value="${subject.name.toUpperCase()}"/></strong></mark></p>
                                            <div class="d-flex justify-content-between align-items-center">
                                                <div class="btn-group">
                                                    <button type="button" onclick="location.href='Controller?command=go_to_tests&subjectId=<c:out value="${subject.subjectId}"/>'"
                                                            class="btn btn-sm btn-outline-secondary"><fmt:message key="button.view"/></button>
                                                    <c:if test="${user.roleName == 'tutor'}">
                                                        <button onclick="location.href='Controller?command=go_to_edit&entity=subject&' +
                                                                'id=<c:out value="${subject.subjectId}"/>&text=<c:out value="${subject.name}"/>'"  class="btn btn-sm btn-outline-secondary"><fmt:message key="button.edit"/></button>
                                                    </c:if>
                                                </div>
        <%--                                        <small class="text-muted">9 mins</small>--%>
                                                <c:if test="${user.roleName == 'tutor'}">
                                                    <c:set var="message"><fmt:message key="notification.delete"/></c:set>
                                                    <form onSubmit='return confirm("${message}");' action="Controller" method="post">
                                                        <input type="hidden" name="command" value="delete"/>
                                                        <input type="hidden" name="entity" value="subject"/>
                                                        <input type="hidden" name="id" value=<c:out value="${subject.subjectId}"/> />

                                                        <button type="submit" class="btn-danger"><fmt:message key="button.delete"/></button>

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


    <jsp:include page="paginationSubjects.jsp"/>

    <!-- footer -->
    <footer>
        <jsp:include page="footer.jsp"/>
    </footer>
</body>
</html>
