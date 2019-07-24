<?xml version="1.0" encoding="UTF-8" ?>
<%@ page session="true" isELIgnored="false" contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<c:set var='role' value="${sessionScope['role']}"/>

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link href="dashboard.css" rel="stylesheet">
</head>

<body>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark sticky-top">
    <a class="navbar-brand" href="#"><fmt:message key="local.hotel"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
            aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <c:if test="${role eq 'UNKNOWN'}">
                    <a class="nav-link" href="${pageContext.request.contextPath}/main/index" role="button"> <fmt:message
                            key="local.home"/> <span class="sr-only">(current)</span></a>
                </c:if>
                <c:if test="${role eq 'ADMIN'}">
                    <a class="nav-link" href="${pageContext.request.contextPath}/main/admin-personal-area" role="button"> <fmt:message
                            key="local.home"/> <span class="sr-only">(current)</span></a>
                </c:if>
                <c:if test="${role eq 'USER'}">
                    <a class="nav-link" href="${pageContext.request.contextPath}/main/user-personal-area" role="button"> <fmt:message
                            key="local.home"/> <span class="sr-only">(current)</span></a>
                </c:if>
            </li>
        </ul>

        <ul class="navbar-nav col-2 form-inline mt-md-0">
            <li class="nav-item">
                <form role="form" method="post" action="change_language" style="margin-bottom: 0px;">
                    <input class="btn btn-outline-success my-2 my-sm-0" type="hidden" name="language" value="en">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">EN</button>
                </form>
            </li>
            <li class="nav-item">
                <form role="form" method="post" action="change_language" style="margin-bottom: 0px;">
                    <input class="btn btn-outline-success my-2 my-sm-0" type="hidden" name="language" value="uk">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">UA</button>
                </form>
            </li>
        </ul>

        <form class="form-inline mt-2 mt-md-0" style="
                margin-bottom: 0px;">
            <%--<c:choose>
                <c:when test="${role eq 'UNKNOWN'}">--%>
                    <a href="registration-form" class="btn btn-outline-success my-2 my-sm-0" role="button"><fmt:message
                            key="local.registration"/></a>
                    <a href="login" class="btn btn-outline-success my-2 my-sm-0" role="button"><fmt:message
                            key="local.log.in"/></a>
         <%--       </c:when>
                <c:otherwise>
                    <a href="logout" class="btn btn-outline-success my-2 my-sm-0" role="button"><fmt:message
                            key="local.logout"/></a>
                </c:otherwise>
            </c:choose>--%>

        </form>
    </div>
</nav>

</body>
</html>
