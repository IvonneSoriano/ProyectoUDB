<%-- 
    Document   : project
    Created on : Apr 25, 2020, 7:13:50 PM
    Author     : kiss_
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <h1>Nuevo proyecto</h1>
            <form class="form mt-5" role="form" action="${contextPath}/proyectos.do" method="POST">
                <input type="hidden" name="op" value="insertar">
                
                <div class="form-group">
                    <label for="name">
                        Nombre de Proyecto
                    </label>
                    <input type="text" name="name" id="name" class="form-control"/>
                </div>

                <div class="form-group">
                    <label for="depto">
                        Departamento:
                    </label>
                    <select class="form-control" name="depto" id="depto" >
                        <c:forEach items="${requestScope.showDeparment()}" var="dpto">
                            <option value="${dpto.getDepartmentId()}">${dpto.getDepartmentName()}</option>
                        </c:forEach>
                    </select>
                </div>

   <div class="form-group">
                    <label for="description">
                        Descripción:
                    </label>
       <textarea class="form-control" id="description"  name="description"></textarea>
                </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary" value="Guardar" name="Guardar">
                        </div>
            </form>
        </div>
    </body>

</html>
