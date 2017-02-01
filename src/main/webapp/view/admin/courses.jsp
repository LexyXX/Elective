<%--
  Created by IntelliJ IDEA.
  User: lexy
  Date: 03.01.17
  Time: 20:40
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
    <title><fmt:message key="courses" bundle="${bundle}"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style_main.css"/>
</head>
<body>

<jsp:include page="navbar.jsp"/>

<div class="container">
    <div class="row list-group">

        <c:if test="${sessionScope.courses == null}">
            <c:redirect url="${pageContext.request.contextPath}/Controller/GetAllCourses"/>
        </c:if>

        <c:forEach items="${sessionScope.courses}" var="course">
            <div class="col-xs-6 col-lg-6">
                <div class="thumbnail">
                    <form action="${pageContext.request.contextPath}/Controller/ChangeCourse/${course.id}"
                          method="post">

                        <label for="name"><fmt:message key="name" bundle="${bundle}"/>:</label>
                        <input type="text" class="form-control" id="name" name="name" value="${course.name}"/><br/>

                        <label for="start_date"><fmt:message key="start_date" bundle="${bundle}"/>:</label>
                        <input type="text" class="form-control" id="start_date" name="start_date"
                               value="${course.startDate}"/><br/>

                        <label for="end_date"><fmt:message key="end_date" bundle="${bundle}"/>:</label>
                        <input type="text" class="form-control" id="end_date" name="end_date"
                               value="${course.endDate}"/><br/>

                        <label for="teacher"><fmt:message key="teacher" bundle="${bundle}"/>:</label>
                        <select name="teacher" class="form-control" id="teacher">

                            <c:if test="${sessionScope.teachers == null}">
                                <c:redirect url="${pageContext.request.contextPath}/Controller/GetAllTeachers"/>
                            </c:if>

                            <c:forEach items="${sessionScope.teachers}" var="teacher">
                                <c:choose>
                                    <c:when test="${teacher.id == course.teacher.id}">
                                        <option value="${teacher.id}"
                                                selected>${teacher.firstName} ${teacher.lastName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${teacher.id}">${teacher.firstName} ${teacher.lastName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <br/>

                        <input class="btn btn-warning" type="submit"
                               value="<fmt:message key="change" bundle="${bundle}"/>"/>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
