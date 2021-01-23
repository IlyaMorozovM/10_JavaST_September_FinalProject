<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Testing System</title>
<%--    <link rel="stylesheet" href="https://unpkg.com/papercss@1.8.1/dist/paper.css">--%>
    <link href="WEB-INF/styles/mainAuthorized.css" type="text/css"/>
    <link rel="shortcut icon" href="#">
  </head>
  <body>

  <!-- change language -->
  <jsp:include page="WEB-INF/jsp/nav-language.jsp"/>

  <!-- header -->
  <header>
    <jsp:include page="WEB-INF/jsp/header.jsp"/>
  </header>

  <!-- content -->
  <main>
    <c:choose>
      <c:when test="${user != null}">
        <jsp:include page="WEB-INF/jsp/main.jsp"/>
      </c:when>
      <c:otherwise>
        <jsp:include page="WEB-INF/jsp/mainUnauthorized.jsp"/>
      </c:otherwise>
    </c:choose>
  </main>

  <!-- footer -->
  <footer>
    <jsp:include page="WEB-INF/jsp/footer.jsp"/>
  </footer>

  </body>
</html>
