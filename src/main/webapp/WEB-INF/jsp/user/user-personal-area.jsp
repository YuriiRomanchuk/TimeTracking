<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="messages"/>
<fmt:setBundle basename="regexpValidator" var="regexpValidator"/>
<fmt:message key="local.login" bundle="${messages}" var="myVar"/>
<c:set var="dataTableName" value="user-personal-area" scope="page"/>

<tag:page showBar="true" dataTable="user-personal-area" title="Today activities">
    <table id="${dataTableName}" class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="local.user.activity.session.table.activity" bundle="${messages}"/></th>
            <th><fmt:message key="local.user.activity.session.table.time_spent" bundle="${messages}"/></th>
        </thead>
        <c:forEach var="activity" items="${activitiesSession}" varStatus="loop">
            <tr>
                <td>${activity.getActivityDto().getName()}</td>
                <td>${activity.getTimeSpent()}</td>
            </tr>
        </c:forEach>
    </table>
</tag:page>
