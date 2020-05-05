
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${param['locale'] != null}">
    <fmt:setLocale value="${param['locale']}" scope="session" />
</c:if>
<fmt:setBundle basename="AplicationResource"/>

<!DOCTYPE html>
<html>
    <head>
        <title>Modificar empleado</title>
        <jsp:include page="/common/cabecera.jsp"/>
    </head>
    <body>
        <jsp:include page="/common/navbar.jsp"/>
        <div class="container">
            <h1 class="my-5"><fmt:message key="label.tituloEe"/></h1>
            <div class="row">
                <div class=" col-12">

                    <form role="form" action="${pageContext.request.contextPath}/empleados.do?op=update" method="POST">
                        <input type="hidden" value="${employee.getEmployeeId()}" name="id" id="id">
                        <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span><fmt:message key="label.campos"/></strong></div>
                        <div class="form-row">
                        <div class="col form-group mt-4">

                            <label><fmt:message key="label.nombre"/></label>
                            <div class="input-group">
                                <input type="text" class="form-control" name="name" id="name" value="${employee.getEmployeeName()}" placeholder="Ingresa el nombre" required="true">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="col form-group mt-4">
                            <label><fmt:message key="label.apellidos"/></label> 
                            <div class="input-group">
                                <input type="text" class="form-control" name="lastname" id="lastname" value="${employee.getEmployeeLastname()}" placeholder="Ingresa el apellido" required="true">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                                </div>
                        <div class="form-row">
                        <div class="col form-group mt-4">
                            <label><fmt:message key="label.rol"/></label> 
                            <div class="input-group">
                                <select name="rol" id="rol" class="form-control" required="true">
                                    <c:forEach items="${requestScope.listRol}" var="rol" >
                                        <option value="${rol.getRolId()}">${rol.getRolName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col form-group mt-4">
                            <label><fmt:message key="label.departamento"/></label> 
                            <div class="input-group">
                                <select name="department" id="department" class="form-control" required="true">
                                    <c:forEach items="${requestScope.listDeparment}" var="department">
                                        <option value="${department.getDepartmentId()}">${department.getDepartmentName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        </div>
                        <div class="form-group mt-4">
                            <label><fmt:message key="label.nombreUsuario"/></label> 
                            <div class="input-group">
                                <input type="text" class="form-control" id="username" value="${employee.getUsername()}" name="username" placeholder="Ingrese el username" required="true">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-row">
                        <div class="col form-group mt-4">
                            <label><fmt:message key="label.pass"/></label> 
                            <div class="input-group">
                                <input type="password" class="form-control" id="Opassword" name="Opassword" placeholder="Ingrese la contraseña" required="true">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                             <div class="col form-group mt-4">
                            <label>
                                <fmt:message key="label.Npass"/>
                            </label> 
                            <div class="input-group">
                                <input type="password" class="form-control" id="newPassword" name="npassword" placeholder="Ingrese la contraseña nueva">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                                </div>
                        <div class="form-group my-4">
                            <button type="submit" class="btn btn-info" >
                                <fmt:message key="label.save"/>
                            </button>
                        <a class="btn btn-danger"
                           href="${pageContext.request.contextPath}/empleados.do?op=ver">
                            <fmt:message key="label.cancel"/>
                        </a>
                           </div>
                    </form>
                </div>
            </div>
        </div>
        <script>
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
            
                            $("#rol option[value="+ '${employee.getRolId()}' +"]").attr("selected",true);
                            $("#department option[value="+ '${employee.getDepartmentId()}' +"]").attr("selected",true);
                            

                                
                            
                        </script>
    </body>
</html>
