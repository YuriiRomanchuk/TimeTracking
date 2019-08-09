<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var='error' value="${Error}"/>

<div class="col w-100">
    <c:if test="${error !=null && error != ''}">
        <div id="my-alert" class="alert alert-danger alert-dismissible fade show w-100" role="alert">
                ${error}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <div class="w-100 d-none d-md-block"></div>
    </c:if>
</div>