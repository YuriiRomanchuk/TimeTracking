<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="regexpValidator" var="regexpValidator"/>
<fmt:setBundle basename="messages" var="messages"/>

<t:genericpage>
    <jsp:attribute name="title">
      Template Page
    </jsp:attribute>
    <jsp:attribute name="header">
        <jsp:include page="/WEB-INF/jsp/template/header.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <div class="row h-100">
            <div class="col fluid bg-faded py-3">
                <div class="row w-100 justify-content-center">
                    <div class="col-sm-4">
                        <c:set var='error' value="${Error}"/>
                        <div class="col w-100">

                            <c:if test="${error !=null}">
                                <div id="my-alert" class="alert alert-danger alert-dismissible fade show w-100" role="alert">
                                        ${error}
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="w-100 d-none d-md-block"></div>
                            </c:if>

                            <div class="w-100 justify-content-center">
                                <h1><fmt:message key="local.login" bundle="${messages}"/></h1>
                            </div>
                        </div>

                        <form id="form" method="post" action="login" class="needs-validation" novalidate>
                            <div class="form-group">
                                <label for="email"><fmt:message key="local.login.name" bundle="${messages}"/></label>
                                <input required="email" pattern="<fmt:message key="regexEmail" bundle="${regexpValidator}"/>" class="form-control" id="email" name="email"
                                       placeholder="<fmt:message key="local.login.name.placeholder" bundle="${messages}"/>">
                                <label for="password"><fmt:message key="local.login.password" bundle="${messages}"/></label>
                                <input required type="password" pattern="<fmt:message key="regexStringNumber" bundle="${regexpValidator}"/>" class="form-control"
                                       id="password" name="password"
                                       placeholder="<fmt:message key="local.login.password.placeholder" bundle="${messages}"/>">
                            </div>
                            <button type="submit" class="btn btn-primary"><fmt:message key="local.login.log.in.button" bundle="${messages}"/></button>
                        </form>
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

                            $(function () {
                                window.setTimeout(function () {
                                    $('#my-alert').alert('close');
                                }, 20000);
                            });
                        </script>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:genericpage>

