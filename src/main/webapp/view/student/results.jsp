<%--
  Created by IntelliJ IDEA.
  User: lexy
  Date: 03.01.17
  Time: 17:17
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
    <title><fmt:message key="searched_courses" bundle="${bundle}"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style_main.css"/>
</head>
<body>

<jsp:include page="navbar.jsp"/>

<div class="container">
    <div class="row list-group">
        <c:forEach items="${sessionScope.archive}" var="archive">
            <div class="col-xs-4 col-lg-4">
                <div class="thumbnail">
                    <fmt:message key="name" bundle="${bundle}"/>: ${archive.courseDTO.name} <br/>
                    <fmt:message key="start_date" bundle="${bundle}"/>: ${archive.courseDTO.startDate} <br/>
                    <fmt:message key="end_date" bundle="${bundle}"/>: ${archive.courseDTO.endDate} <br/>
                    <fmt:message key="teacher" bundle="${bundle}"/>:
                        ${archive.courseDTO.teacher.firstName} ${archive.courseDTO.teacher.lastName} <br/>
                    <fmt:message key="mark" bundle="${bundle}"/>: ${archive.mark}
                </div>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
