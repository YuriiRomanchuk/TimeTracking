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
            <label for="firstName">First name:</label>
            <input required type="text" pattern="<fmt:message key="regexString"/>"
                   class="form-control" id="firstName"
                   name="firstName"
                   placeholder="Enter your first name">
            <label for="lastName">Last name:</label>
            <input required type="text" pattern="<fmt:message key="regexString"/>"
                   class="form-control" id="lastName"
                   name="lastName"
                   placeholder="Enter your last name">
            <label for="middleName">Middle_name:</label>
            <input required type="text" pattern="<fmt:message key="regexString"/>"
                   class="form-control" id="middleName"
                   name="middleName"
                   placeholder="Enter your middle name">
            <label for="login">Nickname:</label>
            <input required type="text" pattern="<fmt:message key="regexStringNumber"/>"
                   class="form-control" id="login"
                   name="login"
                   placeholder="Enter your login">
            <label for="password">Password:</label>
            <input required type="password" pattern="<fmt:message key="regexStringNumber"/>"
                   class="form-control"
                   id="password" name="password"
                   placeholder="Enter your password">
            <label for="email">Email:</label>
            <input required type="email" pattern="<fmt:message key="regexEmail"/>"
                   class="form-control" id="email"
                   name="email"
                   placeholder="example@mail.com">
            <label for="phone">Phone:</label>
            <input required type="tel" pattern="<fmt:message key="regexPhoneNumber"/>"
                   class="form-control" id="phone" name="phone"
                   placeholder="Enter your phone">
        </div>
        <button type="submit" class="btn btn-primary">Apply</button>
        <a href="${mainPath}/main/index" class="btn btn-primary">Main</a>
    </form>
</tag:page>



