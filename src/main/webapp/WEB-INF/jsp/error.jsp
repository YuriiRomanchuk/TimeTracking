<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Doctor App</title>
</head>
<body>
<h2>
    Error Page<br/>
    <c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>
    <jsp:scriptlet>

                exception.printStackTrace(new java.io.PrintWriter(out));

    </jsp:scriptlet>
</h2>l

<br>

</body>
</html>--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

    <title>Error</title>
</head>
<body>

<c:set var="exception" value="${requestScope['error']}"/>
<%
    Exception exception = (Exception) request.getAttribute("error");
%>
<div class="container">
    <h1>Error! </h1>
    <h3>Oops, something went wrong!</h3>
    <div>
        <%--<%=exception.getMessage()%>--%>
        ${exception.getMessage()}
        <div class="panel-group">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h2 class="panel-title">
                        <a data-toggle="collapse" href="#collapse1">Show stacktrace</a>
                    </h2>
                </div>
                <div id="collapse1" class="panel-collapse collapse">
                    <div class="panel-body" style="white-space: pre-line;">

                        <jsp:scriptlet>

                              exception.printStackTrace(new java.io.PrintWriter(out));

                        </jsp:scriptlet>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

