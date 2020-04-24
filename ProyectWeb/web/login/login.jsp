<%-- 
    Document   : login
    Created on : Apr 23, 2020, 1:44:19 PM
    Author     : Rick
--%>

<%@page session="true" language="java" import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Java Avanzado - 2020</title>        
        <jsp:include page="../common/cabecera.jsp"/>
    </head>

    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-4 col-sm-offset-4">
                    <h2>Inicio de sesión</h2>
                    <br>
                    <%
                        if (request.getSession().getAttribute("error") != null) {
                    %>
                    <div class="alert alert-danger">
                        <strong>Error!</strong><%=request.getSession().getAttribute("error")%>
                        <br>
                    </div>
                    <%
                        }//END-If
                    %>

                    <form role="form" action="${pageContext.request.contextPath}/login.do?op=entrar" method="post">
                        <div class="form-group">
                            <label for="usuario">Usuario</label>
                            <input type="text" class="form-control" id="usuario" placeholder="Usuario" name="usuario" required>
                        </div>
                        <div class="form-group">
                            <label for="clave">Password:</label>
                            <input type="password" class="form-control" id="clave" placeholder="Password" name="clave" required>
                        </div>
                        <div class="form-group">
                            <button class="btn btn-lg btn-primary btn-block" type="submit">Iniciar sesión</button>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </body

</html>