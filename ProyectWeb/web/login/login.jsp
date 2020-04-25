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
        <title>Java Avanzado - Log In</title>        
        <jsp:include page="../common/cabecera.jsp"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/signin.css" />
    </head>

    <body class="text-center">
        <div class="container-fluid">
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

            <form class="form-signin" role="form" action="${pageContext.request.contextPath}/login.do?op=entrar" method="post">
                <h1 class="h3 mb-3 font-weight-normal">Inicio de sesión</h1>
                <label for="usuario" class="sr-only">Usuario</label>
                <input type="text" class="form-control" id="usuario" placeholder="Usuario" name="usuario" required autofocus>


                <label for="clave"class="sr-only">Password:</label>
                <input type="password" class="form-control" id="clave" placeholder="Password" name="clave" required>


                <button class="btn btn-lg btn-primary btn-block" type="submit">Iniciar sesión</button>
                <p class="mt-5 mb-3 text-muted">&copy; UDB 2020</p>

            </form>

        </div>
    </body

</html>