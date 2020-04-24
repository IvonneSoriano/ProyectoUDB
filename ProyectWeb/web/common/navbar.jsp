<%-- 
    Document   : navbar
    Created on : Apr 23, 2020, 11:11:26 PM
    Author     : Rick
--%>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" 
                    data-toggle="collapse" data-target="#navbar" 
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Desplegar navegación</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">SGPI</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${pageContext.request.contextPath}/index.jsp">Inicio</a></li> 
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" 
                       role="button" aria-haspopup="true" 
                       aria-expanded="false">Tickets <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/autores.do?op=nuevo">Registrar autor</a></li>
                        <li><a href="${pageContext.request.contextPath}/autores.do?op=listar">Ver lista de autores</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" 
                       role="button" aria-haspopup="true" 
                       aria-expanded="false">Empleados <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/empleados.do?op=crear">Crear empleado</a></li>
                        <li><a href="${pageContext.request.contextPath}/empleados.do?op=ver">Ver lista de empleados</a></li>                        
                        <li><a href="${pageContext.request.contextPath}/empleados.do?op=modificar">Modificar empleado</a></li>
                        <li><a href="${pageContext.request.contextPath}/empleados.do?op=eliminar">Eliminar empleado</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" 
                       role="button" aria-haspopup="true" 
                       aria-expanded="false">Proyectos <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/proyectos.do?op=crear">Crear proyecto</a></li>
                        <li><a href="${pageContext.request.contextPath}/proyectos.do?op=ver">Ver proyectos</a></li>
                        <li><a href="${pageContext.request.contextPath}/proyectos.do?op=modificar">Modificar proyecto</a></li>
                        <li><a href="${pageContext.request.contextPath}/proyectos.do?op=eliminar">Eliminar proyecto</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" 
                       role="button" aria-haspopup="true" 
                       aria-expanded="false">Peticiones<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/editoriales.do?op=nuevo">Registrar editorial</a></li>
                        <li><a href="${pageContext.request.contextPath}/editoriales.do?op=listar">Ver lista de editoriales</a></li>
                    </ul>
                </li>
                 <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" 
                       role="button" aria-haspopup="true" 
                       aria-expanded="false">Reportes<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/editoriales.do?op=nuevo">Ver reporte A</a></li>
                        <li><a href="${pageContext.request.contextPath}/editoriales.do?op=listar">Ver reporte B</a></li>                        
                        <li><a href="${pageContext.request.contextPath}/editoriales.do?op=listar">Ver lista de reportes</a></li>
                    </ul>
                </li>                
                <li class="inactive"><a href="${pageContext.request.contextPath}/">Cerrar Sesion</a></li> 
            </ul>

        </div>
    </div>
</nav>

