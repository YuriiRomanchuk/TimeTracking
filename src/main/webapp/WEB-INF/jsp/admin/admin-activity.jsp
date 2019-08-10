<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="messages"/>
<fmt:setBundle basename="regexpValidator" var="regexpValidator"/>
<fmt:message key="local.activity.add" bundle="${messages}" var="myVar"/>

<tag:page title="${pageScope.myVar}" needValidation="true" showBar="true">

    <form id="form" method="post" action="admin-activity" class="needs-validation" novalidate>
        <div class="form-group">
            <label for="name"><fmt:message key="local.activity.name"
                                            bundle="${messages}"/></label>
            <input required="name"
                   pattern="<fmt:message key="regexStringNumber" bundle="${regexpValidator}"/>"
                   class="form-control" id="name" name="name"
                   placeholder="<fmt:message key="local.activity.name.placeholder" bundle="${messages}"/>">
            <label for="english_name"><fmt:message key="local.activity.name.english"
                                           bundle="${messages}"/></label>
            <input required="english_name"
                   pattern="<fmt:message key="regexStringNumber" bundle="${regexpValidator}"/>"
                   class="form-control" id="english_name" name="english_name"
                   placeholder="<fmt:message key="local.activity.name.english.placeholder" bundle="${messages}"/>">
        </div>
        <button type="submit" class="btn btn-primary"><fmt:message
                key="local.activity.add.button" bundle="${messages}"/></button>
    </form>
</tag:page>