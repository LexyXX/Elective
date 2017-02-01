<%--
  Created by IntelliJ IDEA.
  User: lexy
  Date: 04.12.16
  Time: 15:16
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
    <title><fmt:message key="registration" bundle="${bundle}"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style_index.css"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-5 col-sm-offset-7 form">
            <form action="/Controller/Register" method="post">
                <div class="form-group">
                    <fmt:message key="fname" bundle="${bundle}"/>:
                    <input class="form-control " type="text" name="fname"/>
                </div>

                <div class="form-group">
                    <fmt:message key="lname" bundle="${bundle}"/>:
                    <input class="form-control " type="text" name="lname"/>
                </div>
                <div class="form-group">
                    <fmt:message key="login" bundle="${bundle}"/>:
                    <input class="form-control " type="text" name="login"/>
                </div>

                <div class="form-group">
                    <fmt:message key="password" bundle="${bundle}"/>:
                    <input class="form-control " type="password" name="password"/>
                </div>

                <input class="btn btn-info" type="submit" value="<fmt:message key="sign_up" bundle="${bundle}"/>"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>
