<%--
  Created by IntelliJ IDEA.
  User: lexy
  Date: 11.01.17
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale == null ? 'en_US' : locale}"/>
<fmt:setBundle basename="Lang" var="bundle"/>
<fmt:requestEncoding value="UTF-8"/>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#collapse-1">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/Controller/AdminHome">
                <fmt:message key="elective" bundle="${bundle}"/>
            </a>
        </div>

        <div class="collapse navbar-collapse" id="collapse-1">

            <ul class="nav navbar-nav navbar-right">

                <li><a href="${pageContext.request.contextPath}/Controller/AllCourses">
                    <fmt:message key="courses" bundle="${bundle}"/>
                </a></li>

                <li><a href="${pageContext.request.contextPath}/Controller/AllStudents">
                    <fmt:message key="students" bundle="${bundle}"/>
                </a></li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <fmt:message key="my_account" bundle="${bundle}"/><span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/Controller/MyInfo">
                            <fmt:message key="my_info" bundle="${bundle}"/>
                        </a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="${pageContext.request.contextPath}/Controller/Logout">
                            <fmt:message key="logout" bundle="${bundle}"/>
                        </a></li>
                    </ul>
                </li>

            </ul>
        </div>

    </div>
</nav>