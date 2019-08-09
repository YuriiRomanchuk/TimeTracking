<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="messages"/>
<fmt:setBundle basename="regexpValidator" var="regexpValidator"/>
<fmt:message key="local.admin.request.activity" bundle="${messages}" var="myVar"/>
<c:set var="dataTableName" value="approval-request-activity" scope="page"/>

<tag:page showBar="true" dataTable="approval-request-activity" title="${pageScope.myVar}">
    <form method="post" action="admin-approval-request-activity">
        <div class="w-100 d-none d-md-block"></div>
        <table id="${dataTableName}" class="table table-striped">
            <thead>
            <tr>
                <th><fmt:message key="local.admin.request.activity.table.id" bundle="${messages}"/></th>
                <th><fmt:message key="local.admin.request.activity.table.userNickname" bundle="${messages}"/></th>
                <th><fmt:message key="local.admin.request.activity.table.activity" bundle="${messages}"/></th>
                <th><fmt:message key="local.admin.request.activity.table.requestAction" bundle="${messages}"/></th>
                <th><fmt:message key="local.admin.request.activity.table.action" bundle="${messages}"/></th>
            </thead>
            <c:forEach var="request" items="${requestActivities}" varStatus="loop">
                <tr>
                    <td>
                        <input type="text" class="form-control" id="request_id_${loop.index}"
                               name="request_id_${loop.index}" size="1"
                               readonly
                               value=" ${request.getId()}">
                    </td>
                    <td>
                        <input type="text" class="form-control" id="user_nickname_${loop.index}"
                               name="user_nickname_${loop.index}" size="1"
                               readonly
                               value=" ${request.getUserDto().getLogin()}">
                    </td>
                    <td>
                        <input type="text" class="form-control" id="activity_${loop.index}"
                               name="activity_${loop.index}"
                               readonly
                               value="${request.getActivityDto().getName()}">
                    </td>
                    <td>
                        <input type="text" class="form-control" id="request_action_${loop.index}"
                               name="request_action_${loop.index}"
                               readonly
                               value="${request.getRequestAction()}">
                    </td>
                    <td>
                        <div class="form-group field-middle_name row mr-2">
                            <c:if test="${invoice.getId() !=0}">
                                <button onclick="form.action='approve-request';" type="submit" name="approve-request"
                                        value="${loop.index}"
                                        class="btn btn-primary ml-2 mr-1">
                                    <fmt:message key="local.user.request.activity.button.approve" bundle="${messages}"/>
                                </button>

                                <button onclick="form.action='reject-request';" type="submit" name="reject-request"
                                        value="${loop.index}"
                                        class="btn btn-primary ml-2 mr-1">
                                    <fmt:message key="local.user.request.activity.button.reject" bundle="${messages}"/>
                                </button>
                            </c:if>

                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
</tag:page>
