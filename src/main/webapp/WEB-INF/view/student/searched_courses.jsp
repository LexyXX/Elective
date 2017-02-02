<%--
  Created by IntelliJ IDEA.
  User: lexy
  Date: 03.01.17
  Time: 16:20
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

        <c:if test="${sessionScope.searched_courses.size() == 0}">
            <h1> <fmt:message key="no_data_yet" bundle="${bundle}"/></h1>
        </c:if>

        <c:forEach items="${sessionScope.searched_courses}" var="course">
            <div class="col-xs-4 col-lg-4">
                <div class="thumbnail">
                    <form action="${pageContext.request.contextPath}/Controller/Enroll/${course.id}">
                        <fmt:message key="name" bundle="${bundle}"/>: ${course.name} <br/>
                        <fmt:message key="start_date" bundle="${bundle}"/>: ${course.startDate} <br/>
                        <fmt:message key="end_date" bundle="${bundle}"/>: ${course.endDate} <br/>
                        <fmt:message key="teacher" bundle="${bundle}"/>:
                            ${course.teacher.firstName} ${course.teacher.lastName} <br/><br/>

                        <c:set var="hasEnrolled" value="course${course.id}" />

                        <c:if test="${sessionScope[hasEnrolled] == null}">
                            <c:redirect
                                    url="${pageContext.request.contextPath}/Controller/CheckEnrolled/${course.id}/${sessionScope.user.id}"/>
                        </c:if>


                        <c:choose>
                            <c:when test="${sessionScope[hasEnrolled]}">
                                <input class="btn btn-success" type="submit" disabled
                                       value="<fmt:message key="enroll" bundle="${bundle}"/>"/>
                            </c:when>
                            <c:otherwise>
                                <input class="btn btn-success" type="submit"
                                       value="<fmt:message key="enroll" bundle="${bundle}"/>"/>
                            </c:otherwise>
                        </c:choose>

                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
