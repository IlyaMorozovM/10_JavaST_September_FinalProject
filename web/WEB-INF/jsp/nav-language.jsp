<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent"/>

<html>
<head>

</head>
<body>

<li>

    <a>
        <fmt:message key="title.language"/>
        <c:out value="${page}"/> = page)))
        <c:out value="${pageContext.request.requestURL}"/> = requestURL

    </a>

    <ul>
        <li>
            <a href="Controller?command=change_language&lang=en_EN&page=${pageContext.request.requestURL}">
                <fmt:message key="label.languageEn"/>
            </a>
        </li>
        <li>
            <a href="Controller?command=change_language&lang=ru_RU&page=${pageContext.request.requestURL}">
                <fmt:message key="label.languageRu"/>
            </a>
        </li>
    </ul>

</li>


</body>
</html>
