<%--
  Created by IntelliJ IDEA.
  User: lexy
  Date: 11.01.17
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tags.tld" prefix="mytag" %>

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
            <a class="navbar-brand" href="${pageContext.request.contextPath}/Controller/StudentHome">
                <fmt:message key="elective" bundle="${bundle}"/>
            </a>
        </div>

        <div class="collapse navbar-collapse" id="collapse-1">

            <form action="${pageContext.request.contextPath}/Controller/SearchCourse" method="post"
                  class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" name="name"
                           placeholder="<fmt:message key="course_name" bundle="${bundle}"/>">
                </div>
                <button type="submit" class="btn btn-default">
                    <fmt:message key="search" bundle="${bundle}"/>
                </button>
            </form>

            <ul class="nav navbar-nav navbar-right">

                <li><a href="${pageContext.request.contextPath}/Controller/ChosenCourses">
                    <fmt:message key="chosen_courses" bundle="${bundle}"/>
                </a></li>

                <li><a href="${pageContext.request.contextPath}/Controller/Results">
                    <fmt:message key="results" bundle="${bundle}"/>
                </a></li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <fmt:message key="my_account" bundle="${bundle}"/>
                        <span class="caret"></span></a>
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

                <p class="navbar-text hello-text">
                    <mytag:message text="${sessionScope.user.firstName}"/>
                </p>


            </ul>
        </div>

    </div>
</nav>