<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "custom" uri = "/WEB-INF/tld/conditionalMsg.tld"%>
<html>
<head>
    <title>Sign Up to Garbage Testing System</title>
<%--    <link rel="stylesheet" href="https://unpkg.com/papercss@1.8.1/dist/paper.css">--%>
</head>
<body>

    <!-- header -->
    <header>
        <jsp:include page="header.jsp"/>
    </header>

    <!-- main -->
    <main>

        <c:if test="${param.error == 'error'}">
            <h2>Oops. Something went wrong while signing up. Try again.</h2>
        </c:if>
        <c:if test="${param.error == 'unique'}">
            <h2>Oops. User with this login or email already exists. Try again.</h2>
        </c:if>
        <custom:condMsg condition="${param.register == 'success'}" message="Successful user registration!"/>


        <div> <!-- registration container -->
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="signup"/>

                <br/>
                <label for="login">Login:</label>
                <input type="text" id="login" name="login"/>

                <br/>
                <label for="email">Email:</label>
                <input type="text" id="email" name="email"/>

                <br/>
                <label for="name">Name:</label>
                <input type="text" id="name" name="name"/>

                <br/>
                <label for="lastname">Lastname:</label>
                <input type="text" id="lastname" name="lastname"/>

                <br/>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password"/>

                <br/>
                <label for="role">Role (1=tutor, 2=student):</label>
                <input type="number" id="role" name="role"/>

                <br/>
                <input type="submit" value="Sign Up"/>
            </form>
        </div>
    </main>

    <!-- footer -->
    <footer>
        <jsp:include page="footer.jsp"/>
    </footer>
</body>
</html>
