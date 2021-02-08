<%--
  Created by IntelliJ IDEA.
  User: ilya_
  Date: 08-Feb-21
  Time: 12:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pagination</title>
    <c:url value="/styles/bootstrap.min.css" var="cssUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssUrl}" /></head>
<body>


<div class="container">
    <div class="pagination p1 justify-content-center">
        <ul class="pagination">
            <c:if test="${currentPage>1}">
                <li class="page-item">
                    <a href="Controller?command=go_to_main&currentPage=${currentPage-1}" class="page-link">
                        &#10094
                    </a>
                </li>
            </c:if>

            <c:if test="${currentPage>2}">
                <li class="page-item">
                    <a href="Controller?command=go_to_main&currentPage=1" class="page-link">1</a>
                </li>
            </c:if>

            <c:if test="${currentPage>3}">
                <li class="page-item">
                    <a>...</a>
                </li>
            </c:if>

            <c:if test="${currentPage>1}">
                <li class="page-item">
                    <a href="Controller?command=go_to_main&currentPage=${currentPage-1}" class="page-link">${currentPage-1}</a>
                </li>
            </c:if>


            <li class="page-item active">
                <a href="Controller?command=go_to_main&currentPage=${currentPage}" class="page-link">${currentPage}</a>
            </li>

            <c:if test="${currentPage < maxPage}">
            <li class="page-item">
                <a href="Controller?command=go_to_main&currentPage=${currentPage+1}" class="page-link">${currentPage+1}</a>
            <li class="page-item">
                </c:if>

                <c:if test="${currentPage<maxPage-2}">
            <li class="page-item">
                <a class="page-link">...</a>
            </li>
            </c:if>

            <c:if test="${currentPage<maxPage-1}">
                <li class="page-item">
                    <a href="Controller?command=go_to_main&currentPage=${maxPage}" class="page-link">${maxPage}</a>
                </li>
            </c:if>

            <c:if test="${currentPage<maxPage}">
                <li class="page-item">
                    <a href="Controller?command=go_to_main&currentPage=${currentPage+1}" class="page-link">
                        &#10095
                    </a>
                </li>
            </c:if>

        </ul>
    </div>
</div>
</body>
</html>
