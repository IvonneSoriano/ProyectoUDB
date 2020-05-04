<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${param['locale'] != null}">
    <fmt:setLocale value="${param['locale']}" scope="session" />
</c:if>
<fmt:setBundle basename="AplicationResource"/>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Lista de solicitudes</title>
        <jsp:include page="/common/cabecera.jsp"/>
    </head>
    <body>
        <jsp:include page="/common/navbar.jsp"/>
        <div class="container">
            <div class="row">
                <h1 class="my-5"><fmt:message key="label.tituloLR"/></h1>
            </div>
            <div class="row">
                <div class="col-12">
                   
                    <table class="table table-striped table-bordered table-hover table-sm"
                           id="tabla">
                        <thead>
                            <tr>
                                <th> <fmt:message key="label.pvId"/>Codigo</th>
                                <th> <fmt:message key="label.tituloLR"/>Tipo</th>
                                <th> <fmt:message key="label.tituloLR"/>Fecha de solicitud</th>
                                <th> <fmt:message key="label.tituloLR"/>Descripcion</th>
                                <th> <fmt:message key="label.tituloLR"/>Estado</th>
                                <th> <fmt:message key="label.tituloLR"/>Projecto</th>
                                <th> <fmt:message key="label.tituloLR"/>Departamento</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach items="${requestScope.listRequest}" var="request">
                                <tr>
                                    <td>${request.getId()}</td>
                                    <td>${request.getIdTypeRequest()}</td>
                                    <td>${request.getRequestDate()}</td>
                                    <td>${request.getRequestDescription()}</td>
                                    <td>${request.getRequestStatus()}</td>
                                    <td>${request.getProjectId()}</td>
                                    <td>${request.getDepartmentId()}</td>
                                    <td>
                                        <a class="btn btn-danger"
                                           href="javascript:delete('${request.getId()}')"><span class="glyphicon glyphicontrash"></span> Eliminar</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
        <script>
            $(document).ready(function () {
                $('#tabla').DataTable();
            });
            <c:if test="${not empty exito}">
            alertify.success('${exito}');
                <c:set var="exito" value="" scope="session" />
            </c:if>
            <c:if test="${not empty fracaso}">
            alertify.error('${fracaso}');
                <c:set var="fracaso" value="" scope="session" />
            </c:if>
            function eliminar(id) {
                alertify.confirm("¿Realmente decea eliminar esta solicitud?", function (e) {
                    if (e) {
                        location.href = "request.do?op=delete&id=" + id;
                    }
                });
            }
        </script>
    </body>
</html>
