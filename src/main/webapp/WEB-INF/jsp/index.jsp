<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<t:genericpage>
    <jsp:attribute name="title">
      Index
    </jsp:attribute>
    <jsp:attribute name="header">
        <jsp:include page="/WEB-INF/jsp/template/header.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <%--<div class="row h-100">
            <div class="col fluid bg-faded py-3">
                <div class="row my-3 offset-md-3 " style="
                        margin-left: 0px;">
                    <div class="col">
                        <c:set var='filmSessionDto' value="${filmSessionDto}"/>
                        <c:set var='currentFilm_id' value="${filterFilmId}"/>
                        <c:set var='error' value="${Error}"/>
                        <div class="col w-100">
                            <c:if test="${error !=null}">
                                <div id="my-alert" class="alert alert-danger alert-dismissible fade show w-100"
                                     role="alert">
                                        ${error}
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="w-100 d-none d-md-block"></div>
                            </c:if>

                            <div class="w-100 justify-content-center">
                                <h1>Film session</h1>
                            </div>
                        </div>

                        <div class="w-100 d-none d-md-block"></div>

                        <form method="post" action="index">
                            <div class="form-group field-middle_name row">
                                <div class="col-sm-6 col-md-6 col-xs-6 no-padding">
                                    <label for="film_filter">Film:</label>
                                    <select name="film_filter" id="film_filter" class="form-control" title="Film"
                                            required="required">
                                        <option selected>Choose...</option>
                                        <c:forEach var="film" items="${filmsDto}">
                                            <c:choose>
                                                <c:when test="${ (currentFilm_id !=null && film.getId() == currentFilm_id)}">
                                                    <option selected="selected"
                                                            value=${film.getId()}>${film.getName()}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value=${film.getId()}>${film.getName() }
                                                    </option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-sm-6 col-md-6 col-xs-6 no-padding">
                                    <label for="date_filter">Session date:</label>
                                    <input required type="date" class="form-control" id="date_filter" name="date_filter"
                                           placeholder="Enter session date"
                                           value=${filterDate}>
                                </div>
                            </div>
                            <div class="help-block row"></div>

                            <button type="submit" class="btn btn-primary my-sm-2">Find</button>

                            <table class="table table-sm table-striped">
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Date</th>
                                    <th>Room</th>
                                    <th>Film</th>
                                    <th>Action</th>
                                </thead>
                                <c:forEach var="filmSessionDto" items="${filmsSessionDto}" varStatus="loop">
                                    <tr>
                                        <td>
                                            <input type="text" class="form-control" id="session_id_${loop.index}"
                                                   name="session_id_${loop.index}" size="1"
                                                   readonly
                                                   value=" ${filmSessionDto.getId()}">
                                        </td>
                                        <td>
                                            <input type="text" class="form-control" id="session_date_${loop.index}"
                                                   name="session_date_${loop.index}"
                                                   placeholder="Enter film's name" readonly
                                                   value="${filmSessionDto.getDate()}">
                                        </td>
                                        <td>
                                            <select class="custom-select mr-sm-2" id="session_room_${loop.index}"
                                                    name="session_room_${loop.index}">
                                                <c:if test="${filmSessionDto.getRoom() !=null}">
                                                    <option selected="selected"
                                                            value=${filmSessionDto.getRoom().getId()}>${filmSessionDto.getRoom().getName()}
                                                    </option>
                                                </c:if>
                                            </select
                                        </td>
                                        <td>
                                            <select class="custom-select mr-sm-2" id="session_film_${loop.index}"
                                                    name="session_film_${loop.index}">
                                                <c:if test="${filmSessionDto.getFilmDto() !=null}">
                                                    <option selected="selected"
                                                            value=${filmSessionDto.getFilmDto().getId()}>${filmSessionDto.getFilmDto().getName()}
                                                    </option>
                                                </c:if>
                                            </select>
                                        </td>

                                        <td>
                                            <div class="form-group field-middle_name row mr-2">
                                                <c:if test="${filmSessionDto.getId() > 0 and !isLastDay}">
                                                    <button onclick="form.action='show-unknown-session-room';" type="submit"
                                                            name="show-session"
                                                            value="${loop.index}"
                                                            class="btn btn-success ml-3">
                                                        show
                                                    </button>
                                                </c:if>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </form>

                        <script>
                            $(function () {
                                window.setTimeout(function () {
                                    $('#my-alert').alert('close');
                                }, 20000);
                            });
                        </script>

                    </div>

                </div>
            </div>
        </div>--%>
    </jsp:body>
</t:genericpage>
