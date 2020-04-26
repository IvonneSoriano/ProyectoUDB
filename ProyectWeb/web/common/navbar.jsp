<%-- 
    Document   : navbar
    Created on : Apr 23, 2020, 11:11:26 PM
    Author     : Rick
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${param['locale'] != null}">
    <fmt:setLocale value="${param['locale']}" scope="session" />
</c:if>
<fmt:setBundle basename="AplicationResource"/>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

    <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">SGPI</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div id="navbarSupportedContent" class="collapse navbar-collapse">
        <ul class="navbar-nav mr-auto">

            <!-- Ticket Dropdown -->
            <li class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" id="navbarTicketDropdown" data-toggle="dropdown" 
                   role="button" aria-haspopup="true" 
                   aria-expanded="false">Tickets <span class="caret"></span>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarTicketDropdown">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/tickets.do?op=nuevo">Crear ticket</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/tickets.do?op=listar">Ver lista de tickets</a>                    
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/tickets.do?op=modificar">Modificar ticket</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/tickets.do?op=eliminar">Borrar ticket</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>

            <!-- Empleado Dropdown -->
            <li class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" id="navbarEmpleadoDropdown" data-toggle="dropdown" 
                   role="button" aria-haspopup="true" 
                   aria-expanded="false"><fmt:message key="label.wordEmpleados" /><span class="caret"></span>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarEmpleadoDropdown">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/empleados.do?op=crear">Crear empleado</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/empleados.do?op=ver">Ver lista de empleados</a>                      
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/empleados.do?op=modificar">Modificar empleado</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/empleados.do?op=eliminar">Eliminar empleado</a>
                </div>
            </li>

            <!-- Proyecto Dropdown -->
            <li class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" id="navbarProyectosDropdown" data-toggle="dropdown" 
                   role="button" aria-haspopup="true" 
                   aria-expanded="false"><fmt:message key="label.wordProyectos" /><span class="caret"></span>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarProyectosDropdown">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/proyectos.do?op=crear">Crear proyecto</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/proyectos.do?op=ver">Ver lista de proyectos</a>                      
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/proyectos.do?op=modificar">Modificar proyecto</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/proyectos.do?op=eliminar">Eliminar proyecto</a>
                </div>
            </li>

            <!-- Peticiones Dropdown -->
            <li class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" id="navbarPeticionesDropdown" data-toggle="dropdown" 
                   role="button" aria-haspopup="true" 
                   aria-expanded="false"><span class="caret"><fmt:message key="label.wordSolicitudes" /></span></a>
                <div class="dropdown-menu" aria-labelledby="navbarPeticionesDropdown">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/peticiones.do?op=crear">Crear peticiones</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/peticiones.do?op=ver">Ver lista de peticiones</a>                      
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/peticiones.do?op=modificar">Modificar peticion</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/peticiones.do?op=eliminar">Eliminar peticion</a>
                </div>
            </li>

            <!-- Reportes Dropdown -->
            <li class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" id="navbarReportesDropdown" data-toggle="dropdown" 
                   role="button" aria-haspopup="true" 
                   aria-expanded="false"><span class="caret"><fmt:message key="label.wordReportes" /></span></a>
                <div class="dropdown-menu" aria-labelledby="navbarPeticionesDropdown">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/reportes.do?">Ver reporte A</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/reportes.do?">Ver reporte B</a>
                </div>
            </li>                

            <!-- Opcion cerrar sesion -->
            <a href="${pageContext.request.contextPath}/" class="btn bt-link active text-light pull-right" type="button" aria-pressed="true"><fmt:message key="label.btnCerrarSesion" /></a>
        </ul>

    </div>
</nav>
