<%-- 
    Document   : navbar
    Created on : Apr 23, 2020, 11:11:26 PM
    Author     : Rick
--%>
<%@page import="sv.edu.udb.models.Employee"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ pagesession="true" %>
<%
    HttpSession sesionOk = request.getSession();
    Employee empleado = (Employee) sesionOk.getAttribute("employee");
    Integer de = (Integer) sesionOk.getAttribute("rol");
    if (empleado == null) {
%>
<jsp:forwardpage="login/login.jsp">
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
                   aria-expanded="false"><fmt:message key="label.wordTickets" /> <span class="caret"></span>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarTicketDropdown">

                    <%                 if (de % 2 != 0) {
                    %>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/peticiones.do?op=crear">
                        <fmt:message key="label.createTicket" /></a>
                    <%                 }
                    %>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/tickets.do?op=listar"><fmt:message key="label.titleLT"/></a> 
                    <!--div class="dropdown-divider"></div-->
                </div>
            </li>

            <!-- Empleado Dropdown -->
            <%
                if (de % 2 != 0) {
            %>
            <li class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" id="navbarEmpleadoDropdown" data-toggle="dropdown" 
                   role="button" aria-haspopup="true" 
                   aria-expanded="false"><fmt:message key="label.wordEmpleados" /><span class="caret"></span>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarEmpleadoDropdown">	
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/empleados.do?op=crear">
                       <fmt:message key="label.nEmpleado"/></a>	
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/empleados.do?op=ver"> <fmt:message key="label.tituloLE"/></a>
                </div>
            </li>
            <%
                }
            %>


            <!-- Proyecto Dropdown -->
            <%
                if (de % 2 != 0) {
            %>
            <li class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" id="navbarProyectosDropdown" data-toggle="dropdown" 
                   role="button" aria-haspopup="true" 
                   aria-expanded="false"><fmt:message key="label.wordProyectos" /><span class="caret"></span>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarProyectosDropdown">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/proyectos.do?op=crear"><fmt:message key="label.titleNP"/></a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/proyectos.do?op=ver"><fmt:message key="label.tituloLP"/></a>                      
                </div>
            </li>
            <%
                }
            %>

            <!-- Peticiones Dropdown -->
            <%                 if (de % 2 != 0) {
            %>
            <li class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" id="navbarPeticionesDropdown" data-toggle="dropdown" 
                   role="button" aria-haspopup="true" 
                   aria-expanded="false"><span class="caret"><fmt:message key="label.wordSolicitudes" /></span></a>
                <div class="dropdown-menu" aria-labelledby="navbarPeticionesDropdown">
                    <%
                        if (de == 1) {
                    %>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/requests.do?op=crear"> <fmt:message key="label.tituloNR" /></a>
                    <%
                        }
                    %>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/requests.do?op=ver"> <fmt:message key="label.tituloLR" /></a>                      
                </div>
            </li>
            <%
                }
            %>

            <!-- Reportes Dropdown -->
            <li class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" id="navbarReportesDropdown" data-toggle="dropdown" 
                   role="button" aria-haspopup="true" 
                   aria-expanded="false"><span class="caret"><fmt:message key="label.wordReportes" /></span></a>
                <div class="dropdown-menu" aria-labelledby="navbarPeticionesDropdown">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}//ReportAllRequest"><fmt:message key="label.ReportesSolis" /> </a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/reportes.do?">Ver reporte B</a>
                </div>
            </li>                

            <!-- Opcion cerrar sesion -->
            <a href="${pageContext.request.contextPath}/" class="btn bt-link active text-light pull-right" type="button" aria-pressed="true"><fmt:message key="label.btnCerrarSesion" /></a>
        </ul>

    </div>
</nav>
