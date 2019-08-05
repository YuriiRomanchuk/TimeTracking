<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="messages"/>
<fmt:setBundle basename="regexpValidator" var="regexpValidator"/>
<fmt:message key="local.login" bundle="${messages}" var="myVar"/>

<tag:page title="${pageScope.myVar}" needValidation="true">

    <form id="form" method="post" action="login" class="needs-validation" novalidate>
        <div class="form-group">
            <label for="email"><fmt:message key="local.login.name"
                                            bundle="${messages}"/></label>
            <input required="email"
                   pattern="<fmt:message key="regexEmail" bundle="${regexpValidator}"/>"
                   class="form-control" id="email" name="email"
                   placeholder="<fmt:message key="local.login.name.placeholder" bundle="${messages}"/>">
            <label for="password"><fmt:message key="local.login.password"
                                               bundle="${messages}"/></label>
            <input required type="password"
                   pattern="<fmt:message key="regexStringNumber" bundle="${regexpValidator}"/>"
                   class="form-control"
                   id="password" name="password"
                   placeholder="<fmt:message key="local.login.password.placeholder" bundle="${messages}"/>">
        </div>
        <button type="submit" class="btn btn-primary"><fmt:message
                key="local.login.log.in.button" bundle="${messages}"/></button>
    </form>
</tag:page>
