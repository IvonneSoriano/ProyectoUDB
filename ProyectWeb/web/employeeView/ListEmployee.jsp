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
            <h1 class="mt-5 mb-2">
                <fmt:message key="label.tituloLE"/>
            </h1>
            <div class="row">
                <div class="col-12 d-flex justify-content-end mb-5">
                    <a type="button" class="btn btn-primary btn-md"
                       href="${pageContext.request.contextPath}/empleados.do?op=crear"><fmt:message key="label.nEmpleado"/></a>
                </div>
                <div class="col-12">
                    
                        <table class="table table-striped table-bordered table-hover table-sm "
                               id="tabla">
                            <thead class="thead-dark">
                                <tr>
                                    <th><fmt:message key="label.codigoEmpleado"/></th>
                                    <th><fmt:message key="label.nombre"/></th>
                                    <th><fmt:message key="label.apellidos"/></th>
                                    <th><fmt:message key="label.nombreUsuario"/></th>
                                    <th><fmt:message key="label.departamento"/></th>
                                    <th><fmt:message key="label.rol"/></th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach items="${requestScope.listEmployees}" var="employee">
                                    <tr>
                                        <td>${employee.getEmployeeId()}</td>
                                        <td>${employee.getEmployeeName()}</td>
                                        <td>${employee.getEmployeeLastname()}</td>
                                        <td>${employee.getUsername()}</td>
                                        <td>${employee.getDepartmentId()}</td>
                                        <td>${employee.getRolId()}</td>
                                        <td class="text-center">
                                            <a class="btn btn-primary"
                                               href="${pageContext.request.contextPath}/empleados.do?op=get&id=${employee.getEmployeeId()}"> <fmt:message key="label.Edit"/></a>
                                        </td>
                                        <td class="text-center">
                                            <a class="btn btn-danger"
                                               href="javascript:eliminar('${employee.getEmployeeId()}')"> <fmt:message key="label.Delete"/></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                </div>

            </div>
        </div>
        <script>
            function eliminar(id) {
                alertify.confirm("¿Realmente decea eliminar este empleado?", function (e) {
                    if (e) {
                        location.href = "empleados.do?op=delete&id=" + id;
                    }
                });
            }
        </script>
    </body>
</html>