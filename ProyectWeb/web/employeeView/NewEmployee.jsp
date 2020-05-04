
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${param['locale'] != null}">
    <fmt:setLocale value="${param['locale']}" scope="session"/>
</c:if>
<html>
    <head>
        <title>Nuevo empleado</title>
        <jsp:include page="/common/cabecera.jsp"/>
    </head>
 <body>
 <jsp:include page="/common/navbar.jsp"/>
 <div class="container">
    <div class="row">
        <h1 class="my-5">
            <fmt:message key="label.tituloNe" />
        </h1>
    </div>
    <div class="row">
        <div class=" col-12">
       <form role="form" action="${pageContext.request.contextPath}/empleados.do?op=insert" method="POST">
            <div class="well well-sm">
                <strong><span class="glyphicon glyphicon-asterisk"></span>
                    <fmt:message key="label.campos" />
                </strong>
                </div>
                <div class="form-row">
            <div class="col  mb-4">

           <label><fmt:message key="label.nombre"/></label> 
            <div class="input-group">
                <input type="text" class="form-control" name="name" id="name"  placeholder="Ingresa el nombre" >
                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
            </div>
            </div>
           <div class="col  mb-4">
            <label><fmt:message key="label.apellidos"/></label> 
            <div class="input-group">
                <input type="text" class="form-control" name="lastname" id="lastname"  placeholder="Ingresa el apellido" >
                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
            </div>
            </div>
                </div>
                <div class="form-row">
            <div class="col  mb-4">
                <label><fmt:message key="label.rol"/></label> 
                <div class="input-group">
                    <select name="rol" id="rol" class="form-control">
                        <c:forEach items="${requestScope.listRol}" var="rol">
                            <option value="${rol.getRolId()}">${rol.getRolName()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col  mb-4">
                 <label><fmt:message key="label.departamento"/></label> 
                <div class="input-group">
                    <select name="department" id="department" class="form-control">
                        <c:forEach items="${requestScope.listDepartment}" var="department">
                            <option value="${department.getDepartmentId()}">${department.getDepartmentName()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
                </div>
                <div class="form-row">
           <div class="col  mb-4">
            <label><fmt:message key="label.nombreUsuario"/></label> 
            <div class="input-group">
                <input type="text" class="form-control" id="username"  name="username" placeholder="Ingrese el username">
                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
            </div>
            </div>
            <div class="col  mb-4">
            <label><fmt:message key="label.pass"/></label> 
            <div class="input-group">
                <input type="password" class="form-control" id="password"  name="password" placeholder="Ingrese la contraseña">
                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
            </div>
            </div>
                </div>
                <div class=" mb-4">
                    <button type="submit" class="btn btn-info">
                        <fmt:message key="label.save"/>
                    </button>
            <a class="btn btn-danger"
           href="${pageContext.request.contextPath}/empleado.do?op=ver">
                <fmt:message key="label.cancel"/>
            </a>
                </div>
        </form>
        </div>
    </div>
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
                                    alertify.confirm("¿Realmente decea eliminar este Autor?", function(e){
                                        if(e){
                                            location.href="empleados.do?op=eliminar&id="+ id;
                                        }
                                    });
                                }
 </body>
</html>
