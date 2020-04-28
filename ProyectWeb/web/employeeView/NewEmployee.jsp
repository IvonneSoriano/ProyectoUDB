<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Nuevo autor</title>
        <%@ include file='/cabecera.jsp' %>
    </head>
 <body>
 <jsp:include page="/navbar.jsp"/>
 <div class="container">
    <div class="row">
        <h3>Nuevo autor</h3>
    </div>
    <div class="row">
        <div class=" col-md-7">

        <c:if test="${not empty listaErrores}">
        <div class="alert alert-danger">
                <ul>
                <c:forEach var="errores"
               items="${requestScope.listaErrores}">
                <li>${errores}</li>
                </c:forEach>
                </ul>
        </div>
       </c:if>
       <form role="form" action="${pageContext.request.contextPath}/empleados.do" method="POST">
            <input type="hidden" name="op" value="insert">
            <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>Campos requeridos</strong></div>
            <div class="form-group">

           <label for="nombre">Nombre</label>
            <div class="input-group">
                <input type="text" class="form-control" name="name" id="name" value="${employee.employeename}" placeholder="Ingresa el nombre" >
                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
            </div>
            </div>
           <div class="form-group">
            <label for="apellido">Apellido</label>
            <div class="input-group">
                <input type="text" class="form-control" name="lastname" id="lastname" value="${employee.employeelastname}" placeholder="Ingresa el apellido" >
                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
            </div>
            </div>
           <div class="form-group">
            <label for="contacto">Username</label>
            <div class="input-group">
                <input type="text" class="form-control" id="username" value="${employee.username}" name="username" placeholder="Ingrese el username">
                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
            </div>
            </div>
            <div class="form-group">
            <label for="contacto">Contraseña</label>
            <div class="input-group">
                <input type="password" class="form-control" id="password" value="${employee.password}" name="password" placeholder="Ingrese la contraseña">
                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
            </div>
            </div>
            <input type="submit" class="btn btn-info" value="Guardar" name="Guardar">
            <a class="btn btn-danger"
           href="${contextPath}/empleado.do?op=list">Cancelar</a>
        </form>
        </div>
    </div>
 </div>
 </body>
</html>