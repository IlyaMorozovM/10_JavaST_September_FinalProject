<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="pagecontent" />
<c:url value="/styles/header.css" var="cssUrl"/>
<link rel="stylesheet" type="text/css" href="${cssUrl}" />

<figure class="text-center my-header">
    <blockquote class="blockquote">
        <p class="h3"><fmt:message key="header.welcome"/> </p>
    </blockquote>
</figure>

