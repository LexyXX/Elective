<%--
  Created by IntelliJ IDEA.
  User: lexy
  Date: 03.01.17
  Time: 18:02
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
    <title><fmt:message key="teacher_home_page" bundle="${bundle}"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style_main.css"/>
</head>
<body>

<jsp:include page="navbar.jsp"/>

<div class="container">
    <div class="row list-group">
        <div class="col-xs-7 col-lg-7">
            <c:if test="${sessionScope.archives == null}">
                <c:redirect
                        url="${pageContext.request.contextPath}/Controller/GetArchivesForTeacher/${sessionScope.user.id}"/>
            </c:if>

            <c:forEach items="${sessionScope.archives}" var="archive">
                <div class="col-xs-4 col-lg-4">
                    <div class="thumbnail">
                        <form action="${pageContext.request.contextPath}/Controller/PutMark/${archive.id}"
                              method="post">
                            <fmt:message key="name" bundle="${bundle}"/>: ${archive.courseDTO.name} <br/>
                            <fmt:message key="start_date" bundle="${bundle}"/>: ${archive.courseDTO.startDate} <br/>
                            <fmt:message key="end_date" bundle="${bundle}"/>: ${archive.courseDTO.startDate} <br/>
                            <fmt:message key="student" bundle="${bundle}"/>:
                                ${archive.student.firstName} ${archive.student.lastName} <br/>

                            <label for="mark"><fmt:message key="mark" bundle="${bundle}"/>:</label>
                            <input type="text" id="mark" name="mark" value="${archive.mark}"/><br/><br/>

                            <input class="btn btn-success btn-sm" type="submit"
                                   value="<fmt:message key="estimate" bundle="${bundle}"/>"/>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="col-xs-5 col-lg-5">
            <div class="thumbnail">
                <form action="${pageContext.request.contextPath}/Controller/AddCourse" method="post">

                    <label for="new_name"><fmt:message key="name" bundle="${bundle}"/>:</label>
                    <input type="text" class="form-control" id="new_name" name="name"/><br/>

                    <label for="new_start_date"><fmt:message key="start_date" bundle="${bundle}"/>:</label>
                    <input type="text" class="form-control" id="new_start_date" name="start_date"
                           placeholder="YYYY-MM-DD"/><br/>

                    <label for="new_end_date"><fmt:message key="end_date" bundle="${bundle}"/>:</label>
                    <input type="text" class="form-control" id="new_end_date" name="end_date"
                           placeholder="YYYY-MM-DD"/><br/>

                    <input class="btn btn-success" type="submit"
                           value="<fmt:message key="add" bundle="${bundle}"/>"/>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
