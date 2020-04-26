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
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

            <div class="row">
                <div class="col-12">
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Proyecto</th>
                                <th scope="col">Departamento</th>
                                <th scope="col">Creacion</th>
                                <th scope="col">Handle</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.listaProyectos}" var="project" >
                                <tr>
                                    <td>${project.getProjectsId()}</td>
                                    <td>${project.getProjectName()}</td>
                                    <td>${project.getDepartmentId()}</td>
                                    <td>${project.getCreationDate()}</td>
                                   
                                    <td><a href="javascript:eliminar('${project.getProjectsId}')"><button class="btn btn-danger" >Eliminar</button></a></td>

                                </tr>
                            </c:forEach>
                                
                                
                                <script>
 $(document).ready(function(){

 function eliminar(id){
 confirm("¿Realmente desea eliminar este Proyecto?", function(e){
 if(e){
 location.href="autores.do?op=eliminar&id="+ id;
 }
 });
 }
 });
 </script>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>

</html>
