<%--
  Created by IntelliJ IDEA.
  User: lexy
  Date: 29.01.17
  Time: 00:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="Lang" var="bundle"/>

<html>
<head>
    <title><fmt:message key="error" bundle="${bundle}"/></title>
</head>
<body>
    <fmt:message key="error" bundle="${bundle}"/><br/>
    <fmt:message key="${sessionScope.error}" bundle="${bundle}"/>
</body>
</html>
