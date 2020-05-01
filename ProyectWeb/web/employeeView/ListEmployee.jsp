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
        <title>Lista de empleados</title>
        <jsp:include page="/common/cabecera.jsp"/>
    </head>
    <body>
        <jsp:include page="/common/navbar.jsp"/>
        <div class="container">
            <div class="row">
                <h3>Lista de empleados</h3>
            </div>
            <div class="row">
                <div class="col-md-10">
                    <a type="button" class="btn btn-primary btn-md"
                       href="${pageContext.request.contextPath}/empleado.do?op=new"><fmt:message key="label.nEmpleado"/></a>
                    <br/><br/>
                    <table class="table table-striped table-bordered table-hover"
                           id="tabla">
                        <thead>
                            <tr>
                                <th><fmt:message key="label.codigoEmpleado"/></th>
                                <th><fmt:message key="label.nombre"/></th>
                                <th><fmt:message key="label.apellido"/></th>
                                <th><fmt:message key="label.nombreUsuario"/></th>
                                <th><fmt:message key="label.pass"/></th>
                                <th><fmt:message key="label.departaento"/></th>
                                <th><fmt:message key="label.rol"/></th>
                                <th><fmt:message key="label.operaciones"/></th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach items="${requestScope.listEmployees}" var="employee">
                                <tr>
                                    <td>${employee.getEmployeeId()}</td>
                                    <td>${employee.getEmployeeName()}</td>
                                    <td>${employee.getEmployeeLastname()}</td>
                                    <td>${employee.getUsername()}</td>
                                    <td>${employee.getPassword()}</td>
                                    <td>${employee.getDepartmentId()}</td>
                                    <td>${employee.getRolId()}</td>
                                    <td>
                                        <a class="btn btn-primary"
                                           href="${pageContext.request.contextPath}/empleado.do?op=get&id=${employee.getEmployeeId()}"><span
                                                class="glyphicon glyphicon-edit"></span> Editar</a>
                                        <a class="btn btn-danger"
                                           href="javascript:delete('${employee.getEmployeeId()}')"><span class="glyphicon glyphicontrash"></span> Eliminar</a>
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
                alertify.confirm("¿Realmente decea eliminar este empleado?", function (e) {
                    if (e) {
                        location.href = "empleado.do?op=delete&id=" + id;
                    }
                });
            }
        </script>
    </body>
</html>