<%-- 
    Document   : project
    Created on : Apr 25, 2020, 7:13:50 PM
    Author     : kiss_
--%>
<%@page import="sv.edu.udb.models.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Project</title>
        <jsp:include page="/common/cabecera.jsp"/>
    </head>
    <body>
        <jsp:include page="/common/navbar.jsp"/>
        <div class="container mt-5">
            <h1 ><fmt:message key="label.titleNP" /></h1>
            <form class="form mt-5" role="form" action="${pageContext.request.contextPath}/proyectos.do?op=insertar" method="POST">
                
                <div class="form-group">
                    <label for="name" >
                        <fmt:message key="label.npName" />
                    </label>
                    <input type="text" name="name" id="name" class="form-control" required/>
                </div>

                <div class="form-group">
                    <label for="depto" >
                        <fmt:message key="label.pvDep" />
                    </label>
                    <select class="form-control" name="depto" id="depto" >
                        <c:forEach items="${requestScope.departamentos}" var="dpto">
                            <option value="${dpto.getDepartmentId()}">${dpto.getDepartmentName()}</option>
                        </c:forEach>
                    </select>
                </div>

   <div class="form-group">
                    <label for="description" >
                        <fmt:message key="label.npDesc" />
                    </label>
       <textarea class="form-control" id="description"  name="description" required></textarea>
                </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary"  id="Guardar" > <fmt:message key="label.save" /></button>
                        </div>
            </form>
        </div>
        $(document).ready(function(){
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
                                function eliminar(id){
                                    alertify.confirm("¿Realmente decea eliminar este Proyecto?", function(e){
                                        if(e){
                                            location.href="proyectos.do?op=eliminar&id="+ id;
                                        }
                                    });
                                }
    </body>

</html>
