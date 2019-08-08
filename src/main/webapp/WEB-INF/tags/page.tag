<%@ tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ attribute name="title" %>
<%@ attribute name="dataTable" %>
<%@ attribute name="showBar" type="java.lang.Boolean" %>
<%@ attribute name="needValidation" type="java.lang.Boolean" %>

<c:set var="mainPath" value="${pageContext.request.contextPath}" scope="application"/>
<c:set var='role' value="${sessionScope['role']}"/>
<c:set var='error' value="${Error}"/>

<%--<fmt:setLocale value="${sessionScope.lang}"/>--%>

<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <c:if test="${dataTable != null}">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
        <script>$(document).ready(function () {
            $('#${dataTable}').DataTable();
            var menu = $('#${dataTable}-menu');
            if (menu.length) {
                var filter = $('#${dataTable}_filter').children()[0];
                filter.setAttribute('style', 'display: inline-flex;');
                filter.appendChild(menu[0]);
            }
        });
        </script>
    </c:if>

    <c:if test="${needValidation}">
        <script>
            (function () {
                'use strict';
                window.addEventListener('load', function () {
                    var forms = document.getElementsByClassName('needs-validation');
                    var validation = Array.prototype.filter.call(forms, function (form) {
                        form.addEventListener('submit', function (event) {
                            if (form.checkValidity() === false) {
                                event.preventDefault();
                                event.stopPropagation();
                            }
                            form.classList.add('was-validated');
                        }, false);
                    });
                }, false);
            })();
        </script>
    </c:if>
    <script>
        $(function () {
            window.setTimeout(function () {
                $('#my-alert').alert('close');
            }, 20000);
        });
    </script>
</head>
<body>
<tag:header/>
<div class="row h-100">
    <c:if test="${showBar}">
        <tag:bar/>
    </c:if>
    <div class="col fluid bg-faded py-3">
        <tag:error/>
        <div class="w-100 justify-content-center">
            <h1 class="text-center">${title}</h1>
        </div>
        <c:choose>
            <c:when test="${dataTable != null}">
                <jsp:doBody/>
            </c:when>
            <c:otherwise>
                <div class="row my-3 offset-md-3">
                    <div class="col-sm-4">
                        <jsp:doBody/>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>

</body>
</html>