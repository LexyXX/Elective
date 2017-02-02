<%--
  Created by IntelliJ IDEA.
  User: Alexandra
  Date: 20.12.2015
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>


<fmt:setLocale value="${locale == null ? 'en_US' : locale}"/>
<fmt:setBundle basename="Lang" var="bundle"/>
<fmt:requestEncoding value="UTF-8"/>

<html>
<head>
    <title><fmt:message key="login" bundle="${bundle}"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style_index.css"/>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="pull-right lang">
            <fmt:message key="language" bundle="${bundle}"/>:<br/>
            <a href="${pageContext.request.contextPath}/Controller/Language/en">English</a><br/>
            <a href="${pageContext.request.contextPath}/Controller/Language/ru">Русский</a>
        </div>
        <div class="col-sm-4 col-sm-offset-8 form">

            <c:if test="${not empty sessionScope.infoMessage}">
                <fmt:message key="${sessionScope.infoMessage}" bundle="${bundle}"/>
                <br/>
            </c:if>

            <form action="${pageContext.request.contextPath}/Controller/Login" method="post">
                <div class="form-group">
                    <fmt:message key="login" bundle="${bundle}"/>:
                    <input type="text" class="form-control " name="login"/>
                </div>
                <div class="form-group">
                    <fmt:message key="password" bundle="${bundle}"/>:
                    <input type="password" class="form-control" name="password"/>
                </div>

                <input type="submit" class="btn btn-success" value="<fmt:message key="sign_in" bundle="${bundle}"/>"/>
                <a href="${pageContext.request.contextPath}/Controller/Registration"><fmt:message key="registration" bundle="${bundle}"/></a>
            </form>


        </div>
    </div>
</div>
</body>
</html>
