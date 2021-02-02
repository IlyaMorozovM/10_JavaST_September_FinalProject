<%--
  Created by IntelliJ IDEA.
  User: ilya_
  Date: 02-Feb-21
  Time: 12:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pagination</title>
    <c:url value="/styles/bootstrap.css" var="cssUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssUrl}" /></head>
<body>


<div class="container">
    <div class="pagination p1">
        <ul>
            <c:if test="${currentPage>1}">
                <a href="Controller?command=go_to_tests&currentPage=${currentPage-1}">
                    <li>&#10094</li>
                </a>
            </c:if>

            <c:if test="${currentPage>2}">
                <a href="Controller?command=go_to_tests&currentPage=1">
                    <li>1</li>
                </a>
            </c:if>

            <c:if test="${currentPage>3}">
                <a>
                    <li>...</li>
                </a>
            </c:if>

            <c:if test="${currentPage>1}">
                <a href="Controller?command=go_to_tests&currentPage=${currentPage-1}">
                    <li>${currentPage-1}</li>
                </a>
            </c:if>


            <a class="is-active"
               href="Controller?command=go_to_tests&currentPage=${currentPage}">
                <li>${currentPage}</li>
            </a>

            <c:if test="${currentPage < maxPage}">
                <a href="Controller?command=go_to_tests&currentPage=${currentPage+1}">
                    <li>${currentPage+1}</li>
                </a>
            </c:if>

            <c:if test="${currentPage<maxPage-2}">
                <a>
                    <li>...</li>
                </a>
            </c:if>

            <c:if test="${currentPage<maxPage-1}">
                <a href="Controller?command=go_to_tests&currentPage=${maxPage}">
                    <li>${maxPage}</li>
                </a>
            </c:if>

            <c:if test="${currentPage<maxPage}">
                <a href="Controller?command=go_to_tests&currentPage=${currentPage+1}">
                    <li>&#10095</li>
                </a>
            </c:if>

        </ul>
    </div>
</div>
</body>
</html>
