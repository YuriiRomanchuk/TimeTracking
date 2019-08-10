<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="messages"/>
<fmt:setBundle basename="regexpValidator" var="regexpValidator"/>
<fmt:message key="local.user.history.request" bundle="${messages}" var="myVar"/>
<c:set var="dataTableName" value="user-personal-area" scope="page"/>

<tag:page showBar="true" dataTable="user-history-request" title="${pageScope.myVar}">
    <h1 class="text-center">${sessionScope.lang != 'uk'}</h1>
    <table id="${dataTableName}" class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="local.user.history.request.table.id" bundle="${messages}"/></th>
            <th><fmt:message key="local.user.history.request.table.activity" bundle="${messages}"/></th>
            <th><fmt:message key="local.user.history.request.table.request_action" bundle="${messages}"/></th>
            <th><fmt:message key="local.user.history.request.table.request_status" bundle="${messages}"/></th>
        </thead>
        <c:forEach var="request" items="${requests}" varStatus="loop">
            <tr>
                <td>${request.getId()}</td>
                <td>
                    <c:choose>
                    <c:when test="${sessionScope.lang != 'uk'}">
                        ${request.getActivityDto().getEnglishName()}
                    </c:when>
                    <c:otherwise>
                        ${request.getActivityDto().getName()}
                    </c:otherwise>
                    </c:choose>
                <td>${request.getRequestAction()}</td>
                <td>${request.getRequestStatus()}</td>
            </tr>
        </c:forEach>
    </table>
</tag:page>