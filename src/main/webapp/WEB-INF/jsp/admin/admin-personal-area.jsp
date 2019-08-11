<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="messages"/>
<fmt:setBundle basename="regexpValidator" var="regexpValidator"/>
<fmt:message key="local.admin.personal.area" bundle="${messages}" var="myVar"/>
<c:set var="dataTableName" value="admin-personal-area" scope="page"/>

<tag:page showBar="true" dataTable="admin-personal-area" title="${pageScope.myVar}">
    <table id="${dataTableName}" class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="local.admin.user.table.id" bundle="${messages}"/></th>
            <th><fmt:message key="local.admin.user.table.login" bundle="${messages}"/></th>
            <th><fmt:message key="local.admin.user.table.firstName" bundle="${messages}"/></th>
            <th><fmt:message key="local.admin.user.table.lastName" bundle="${messages}"/></th>
            <th><fmt:message key="local.admin.user.table.email" bundle="${messages}"/></th>
            <th><fmt:message key="local.admin.user.table.phone" bundle="${messages}"/></th>
        </thead>
        <c:forEach var="user" items="${users}" varStatus="loop">
            <tr>
                <td>${user.getId()}</td>
                <td>${user.getLogin()}</td>
                <td>${user.getFirstName()}</td>
                <td>${user.getLastName()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.getPhone()}</td>
            </tr>
        </c:forEach>
    </table>
</tag:page>
