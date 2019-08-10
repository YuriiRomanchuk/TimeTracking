<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="messages"/>
<fmt:setBundle basename="regexpValidator" var="regexpValidator"/>
<fmt:message key="local.registration" bundle="${messages}" var="myVar"/>

<tag:page title="${pageScope.myVar}" needValidation="true">
    <form id="form" method="post" action="registration-form" class="needs-validation" novalidate>
        <div class="form-group">
            <label for="firstName"><fmt:message key="local.registration.first.name"
                                                bundle="${messages}"/></label>
            <input required type="text" pattern="<fmt:message key="regexString"/>"
                   class="form-control" id="firstName"
                   name="firstName"
                   placeholder="<fmt:message key="local.activity.first.name.placeholder" bundle="${messages}"/>">
            <label for="lastName"><fmt:message key="local.registration.last.name"
                                               bundle="${messages}"/></label>
            <input required type="text" pattern="<fmt:message key="regexString"/>"
                   class="form-control" id="lastName"
                   name="lastName"
                   placeholder="<fmt:message key="local.activity.last.name.placeholder" bundle="${messages}"/>">
            <label for="middleName"><fmt:message key="local.registration.middle.name"
                                                 bundle="${messages}"/></label>
            <input required type="text" pattern="<fmt:message key="regexString"/>"
                   class="form-control" id="middleName"
                   name="middleName"
                   placeholder="<fmt:message key="local.activity.middle.name.placeholder" bundle="${messages}"/>">
            <label for="login"><fmt:message key="local.registration.login"
                                            bundle="${messages}"/></label>
            <input required type="text" pattern="<fmt:message key="regexStringNumber"/>"
                   class="form-control" id="login"
                   name="login"
                   placeholder="<fmt:message key="local.activity.login.placeholder" bundle="${messages}"/>">
            <label for="password"><fmt:message key="local.registration.password"
                                               bundle="${messages}"/></label>
            <input required type="password" pattern="<fmt:message key="regexStringNumber"/>"
                   class="form-control"
                   id="password" name="password"
                   placeholder="<fmt:message key="local.activity.password.placeholder" bundle="${messages}"/>">
            <label for="email"><fmt:message key="local.registration.email"
                                            bundle="${messages}"/></label>
            <input required type="email" pattern="<fmt:message key="regexEmail"/>"
                   class="form-control" id="email"
                   name="email"
                   placeholder="<fmt:message key="local.activity.email.placeholder" bundle="${messages}"/>">
            <label for="phone"><fmt:message key="local.registration.phone"
                                            bundle="${messages}"/></label>
            <input required type="tel" pattern="<fmt:message key="regexPhoneNumber"/>"
                   class="form-control" id="phone" name="phone"
                   placeholder="<fmt:message key="local.activity.phone.placeholder" bundle="${messages}"/>">
        </div>
        <button type="submit" class="btn btn-primary">Apply</button>
        <a href="${mainPath}/main/index" class="btn btn-primary">Main</a>
    </form>
</tag:page>



