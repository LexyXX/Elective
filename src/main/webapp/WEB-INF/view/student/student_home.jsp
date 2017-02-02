<%--
  Created by IntelliJ IDEA.
  User: lexy
  Date: 03.01.17
  Time: 01:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="pagination" uri="http://pagination.com"%>

<fmt:setLocale value="${locale == null ? 'en_US' : locale}"/>
<fmt:setBundle basename="Lang" var="bundle"/>
<fmt:requestEncoding value="UTF-8"/>

<html>
<head>
    <title><fmt:message key="student_home_page" bundle="${bundle}"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style_main.css"/>
</head>
<body>

<jsp:include page="navbar.jsp"/>


<div class="container">
    <div class="row list-group">

        <c:if test="${sessionScope.categories == null}">
            <c:redirect url="${pageContext.request.contextPath}/Controller/GetCategoriesLimited"/>
        </c:if>

        <c:if test="${sessionScope.categories.size() == 0}">
        <h1> <fmt:message key="no_data_yet" bundle="${bundle}"/></h1>
        </c:if>

        <c:forEach items="${sessionScope.categories}" var="category">
            <div class="col-xs-3 col-lg-3">
                <div class="thumbnail">
                    <form action="${pageContext.request.contextPath}/Controller/CoursesForCategory/${category.id}">
                        <b><fmt:message key="name" bundle="${bundle}"/>:</b> ${category.name} <br/><br/>
                        <b><fmt:message key="start_date" bundle="${bundle}"/>:</b> ${category.descr} <br/><br/>

                        <input class="btn btn-success" type="submit"
                               value="<fmt:message key="show_courses" bundle="${bundle}"/>"/>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
    <h2><pagination:nav pageCount="${sessionScope.categories.size()/8}"
                  action="${pageContext.request.contextPath}/Controller/GetCategoriesLimited"/></h2>
</div>



</body>
</html>
