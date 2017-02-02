<%--
  Created by IntelliJ IDEA.
  User: lexy
  Date: 03.01.17
  Time: 21:57
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
    <title><fmt:message key="students" bundle="${bundle}"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style_main.css"/>
</head>
<body>

<jsp:include page="navbar.jsp"/>

<div class="container">
    <div class="row list-group">
        <c:if test="${sessionScope.users == null}">
            <c:redirect url="${pageContext.request.contextPath}/Controller/GetAllUsers"/>
        </c:if>

        <c:if test="${sessionScope.users.size() == 0}">
           <h1> <fmt:message key="no_data_yet" bundle="${bundle}"/></h1>
        </c:if>

        <c:forEach items="${sessionScope.users}" var="user">
            <div class="col-xs-4 col-lg-4">
                <div class="thumbnail">
                    <form action="${pageContext.request.contextPath}/Controller/ChangeUser/${user.id}">

                        <fmt:message key="fname" bundle="${bundle}"/>:
                            ${user.firstName}<br/>

                        <fmt:message key="lname" bundle="${bundle}"/>:
                            ${user.lastName}<br/>

                        <fmt:message key="login" bundle="${bundle}"/>:
                            ${user.login}<br/>

                        <label for="is_teacher"><fmt:message key="is_teacher" bundle="${bundle}"/></label>
                        <c:choose>
                            <c:when test="${user.teacher == true}">
                                <input type="checkbox" id="is_teacher" name="is_teacher" checked/>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" id="is_teacher" name="is_teacher"/>
                            </c:otherwise>
                        </c:choose>
                        <br/>

                        <label for="is_admin"><fmt:message key="is_admin" bundle="${bundle}"/></label>
                        <c:choose>
                            <c:when test="${user.admin == true}">
                                <input type="checkbox" id="is_admin" name="is_admin" value="is_admin" checked/>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" id="is_admin" name="is_admin" value="is_admin"/>
                            </c:otherwise>
                        </c:choose>
                        <br/>

                        <input class="btn btn-success btn-sm" type="submit"
                               value="<fmt:message key="change" bundle="${bundle}"/>"/>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
</div>


</body>
</html>
