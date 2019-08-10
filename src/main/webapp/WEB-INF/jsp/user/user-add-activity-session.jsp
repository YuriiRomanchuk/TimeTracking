<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="messages"/>
<fmt:setBundle basename="regexpValidator" var="regexpValidator"/>
<fmt:message key="local.user.add.activity.session" bundle="${messages}" var="myVar"/>

<tag:page showBar="true" needValidation="true" title="${pageScope.myVar}">
    <form id="form" method="post" action="user-add-activity-session" class="needs-validation" novalidate>
        <label for="userId"><fmt:message key="local.user.activity.session.user.id" bundle="${messages}"/></label>
        <input required type="text" class="form-control" id="userId" name="userId" size="1"
               readonly
               value=${sessionScope.user_id}>
        <div class="form-group">
            <label for="activity_id"><fmt:message key="local.user.add.activity.session.activities"
                                                  bundle="${messages}"/></label>
            <select name="activity_id" id="activity_id" class="form-control" title="activity_id" required="required">
                <option selected><fmt:message key="local.user.add.activity.session.activities.placeholder"
                                              bundle="${messages}"/></option>
                <c:forEach var="activity" items="${activities}">
                    <option value=${activity.getId()}>
                        <c:choose>
                            <c:when test="${sessionScope.lang != 'uk'}">
                                ${activity.getEnglishName()}
                            </c:when>
                            <c:otherwise>
                                ${activity.getName()}
                            </c:otherwise>
                        </c:choose></option>
                    <c:choose>
                        <c:when test="${sessionScope.lang != 'uk'}">
                            ${activity.getEnglishName()}
                        </c:when>
                        <c:otherwise>
                            ${activity.getName()}
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <label for="time_spent"><fmt:message key="local.user.add.activity.session.time.spent"
                                                 bundle="${messages}"/></label>
            <input required type="number"
                   pattern="<fmt:message key="regexNumber" bundle="${regexpValidator}"/>"
                   class="form-control" id="time_spent" name="time_spent">
        </div>
        <button type="submit" class="btn btn-primary"><fmt:message key="local.admin.add.activity.session.button.add"
                                                                   bundle="${messages}"/></button>
    </form>
</tag:page>
