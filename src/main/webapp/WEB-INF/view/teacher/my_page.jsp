<%--
  Created by IntelliJ IDEA.
  User: lexy
  Date: 03.01.17
  Time: 02:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${locale == null ? 'en_US' : locale}"/>
<fmt:setBundle basename="Lang" var="bundle"/>
<fmt:requestEncoding value="UTF-8"/>
<html>
<head>
    <title><fmt:message key="my_page" bundle="${bundle}"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style_main.css"/>
</head>
<body>

<jsp:include page="navbar.jsp"/>

<div class="container">
    <form action="${pageContext.request.contextPath}/Controller/ChangeMyInfo" method="post">
        <label for="fname"><fmt:message key="fname" bundle="${bundle}"/>:</label>
        <input class="form-control" id="fname" type="text" name="fname"
               value="${sessionScope.user.firstName}"/><br/>

        <label for="lname"><fmt:message key="lname" bundle="${bundle}"/>:</label>
        <input class="form-control" id="lname" type="text" name="lname"
               value="${sessionScope.user.lastName}"/><br/>

        <label for="login"><fmt:message key="login" bundle="${bundle}"/>:</label>
        <input class="form-control" id="login" type="text" name="login"
               value="${sessionScope.user.login}"/><br/>

        <label for="password"><fmt:message key="password" bundle="${bundle}"/>:</label>
        <input class="form-control" id="password" type="password" name="password"/><br/>

        <input class="btn btn-success" type="submit"
               value="<fmt:message key="change_my_info" bundle="${bundle}"/>"/>

    </form>
</div>

</body>
</html>
