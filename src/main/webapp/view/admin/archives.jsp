<%--
  Created by IntelliJ IDEA.
  User: lexy
  Date: 04.12.16
  Time: 18:59
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
    <title><fmt:message key="admin_home_page" bundle="${bundle}"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style_main.css"/>
</head>
<body>

<jsp:include page="navbar.jsp"/>

<div class="container">
    <h1 class="text-center"> <fmt:message key="archive" bundle="${bundle}"/></h1>
    <br/>

    <div class="row list-group">

        <c:forEach items="${sessionScope.archives}" var="archive">
            <div class="col-xs-4 col-lg-4">
                <div class="thumbnail">
                    <form action="${pageContext.request.contextPath}/Controller/RemoveRecord/${archive.id}"
                          method="post">
                        <fmt:message key="name" bundle="${bundle}"/>: ${archive.courseDTO.name} <br/>
                        <fmt:message key="start_date" bundle="${bundle}"/>: ${archive.courseDTO.startDate} <br/>
                        <fmt:message key="end_date" bundle="${bundle}"/>: ${archive.courseDTO.startDate} <br/>
                        <fmt:message key="student" bundle="${bundle}"/>:
                            ${archive.student.firstName} ${archive.student.lastName} <br/>

                        <fmt:message key="mark" bundle="${bundle}"/>:
                            ${archive.mark} <br/><br/>

                        <input class="btn btn-danger btn-sm" type="submit"
                               value="<fmt:message key="remove" bundle="${bundle}"/>"/>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
