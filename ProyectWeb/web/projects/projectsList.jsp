<%-- 
    Document   : project
    Created on : Apr 25, 2020, 7:13:50 PM
    Author     : kiss_
--%>
<%@page import="sv.edu.udb.models.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ pagesession="true" %>
<%
 Employee empleado;
 HttpSession sesionOk = request.getSession();
 empleado = (Employee) sesionOk.getAttribute("employee");
 if (empleado == null) {
%>
<jsp:forwardpage="../login/login.jsp">
<jsp:paramname="error" value="Es obligatorio identificarse"/>
</jsp:forward>
<%
 } else {
 empleado = (Employee) sesionOk.getAttribute("empleado");
 }
%>
<c:if test="${param['locale'] != null}">
  <fmt:setLocale value="${param['locale']}" scope="session" />
</c:if>
<fmt:setBundle basename="AplicationResource"/>
<jsp:include page="/common/validarSesion.jsp"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="/common/cabecera.jsp"/>
    </head>
    <body>
        <jsp:include page="/common/navbar.jsp"/>
        <div class="container mt-5">
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

            <div class="row">
                <div class="col-12">
                    <h1 class="mb-5" fmt:message key=”label.tituloLP”>Listado de Proyectos</h1>
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th fmt:message key=”label.pvId” scope="col">ID</th>
                                <th scope="col" fmt:message key=”label.pvName”>Proyecto</th>
                                <th scope="col" fmt:message key=”label.pvDep”>Departamento</th>
                                <th scope="col" fmt:message key=”label.pvDate”>Creacion</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.listaProyectos}" var="project" >
                                <tr>
                                    <td>${project.getProjectsId()}</td>
                                    <td>${project.getProjectName()}</td>
                                    <td>${project.getDepartmentId()}</td>
                                    <td>${project.getCreationDate()}</td>

                                    <td><a href="javascript:eliminar('${project.getProjectsId()}')"><button class="btn btn-danger" fmt:message key=”label.pvDelete”  >Eliminar</button></a></td>

                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script>
//            $(document).ready(function () {
//                $('#tabla').DataTable();
//            });
            
            function eliminar(id) {
                alertify.confirm("¿Realmente desea eliminar este proyecto?", function (e) {
                    if (e) {
                        location.href = "proyectos.do?op=eliminar&id=" + id;
                    }
                });
            }
        </script>
    </body>

</html>
