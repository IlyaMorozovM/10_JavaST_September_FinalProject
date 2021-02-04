<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
  <head>
    <title>Testing System</title>
    <c:url value="/styles/bootstrap.min.css" var="cssUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssUrl}" />
    <link rel="shortcut icon" href="#">
  </head>
  <body>

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

  </body>
</html>
