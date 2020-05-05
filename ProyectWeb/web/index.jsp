<%-- 
    Document   : index
    Created on : Apr 16, 2020, 5:26:33 PM
    Author     : kiss_
--%>

<%@page import="sv.edu.udb.models.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ pagesession="true" %>
<%
 Employee empleado;
 HttpSession sesionOk = request.getSession();
 empleado = (Employee) sesionOk.getAttribute("employee");
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

<jsp:include page="/common/validarSesion.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <title>Java Avanzado - Home</title>           
        <jsp:include page="/common/cabecera.jsp"/>
    </head>
    <body>
        <jsp:include page="/common/navbar.jsp"/>
        <br><br>
        <div class="container">
            <div class="row">
                <h2><fmt:message key="label.saludo" /> <%=request.getSession().getAttribute("fullName")%> !</h2>
            </div>
            <br>
            <div class="card-deck mb-3 text-center">
                <div class="card mb-4 shadow-sm">
                    <div class="card-header">
                        <svg class="bi bi-file-earmark-ruled" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" d="M13 9H3V8h10v1zm0 3H3v-1h10v1z" clip-rule="evenodd"/>
                        <path fill-rule="evenodd" d="M5 14V9h1v5H5z" clip-rule="evenodd"/>
                        <path d="M4 1h5v1H4a1 1 0 00-1 1v10a1 1 0 001 1h8a1 1 0 001-1V6h1v7a2 2 0 01-2 2H4a2 2 0 01-2-2V3a2 2 0 012-2z"/>
                        <path d="M9 4.5V1l5 5h-3.5A1.5 1.5 0 019 4.5z"/>
                        </svg>
                        <h4 class="my-0 font-weight-normal">Tickets</h4>
                    </div>
                    <div class="card-body">
                        <!--h1 class="card-title pricing-card-title">$0 <small class="text-muted">/ mo</small></h1>
                        <i class="glyphicon glyphicon-list-alt huge"></i>
                        <ul class="list-unstyled mt-3 mb-4">
                            <li>10 users included</li>
                            <li>2 GB of storage</li>
                            <li>Email support</li>
                            <li>Help center access</li>
                        </ul-->
                        <button type="button" class="btn btn-lg btn-block btn-primary"><fmt:message key="label.verTickets" /> ...</button>
                    </div>
                </div>
                <div class="card mb-4 shadow-sm">
                    <div class="card-header">
                        <svg class="bi bi-people-circle" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path d="M13.468 12.37C12.758 11.226 11.195 10 8 10s-4.757 1.225-5.468 2.37A6.987 6.987 0 008 15a6.987 6.987 0 005.468-2.63z"/>
                        <path fill-rule="evenodd" d="M8 9a3 3 0 100-6 3 3 0 000 6z" clip-rule="evenodd"/>
                        <path fill-rule="evenodd" d="M8 1a7 7 0 100 14A7 7 0 008 1zM0 8a8 8 0 1116 0A8 8 0 010 8z" clip-rule="evenodd"/>
                        </svg>
                        <h4 class="my-0 font-weight-normal"><fmt:message key="label.wordEmpleados" /></h4>
                        </svg>
                    </div>
                    <div class="card-body">
                        <button type="button" class="btn btn-lg btn-block btn-primary"><fmt:message key="label.verEmpleados" /> ...</button>
                    </div>
                </div>
                <div class="card mb-4 shadow-sm">
                    <div class="card-header">
                        <svg class="bi bi-briefcase-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" d="M0 12.5A1.5 1.5 0 001.5 14h13a1.5 1.5 0 001.5-1.5V6.85L8.129 8.947a.5.5 0 01-.258 0L0 6.85v5.65z" clip-rule="evenodd"/>
                        <path fill-rule="evenodd" d="M0 4.5A1.5 1.5 0 011.5 3h13A1.5 1.5 0 0116 4.5v1.384l-7.614 2.03a1.5 1.5 0 01-.772 0L0 5.884V4.5zm5-2A1.5 1.5 0 016.5 1h3A1.5 1.5 0 0111 2.5V3h-1v-.5a.5.5 0 00-.5-.5h-3a.5.5 0 00-.5.5V3H5v-.5z" clip-rule="evenodd"/>
                        </svg>
                        <h4 class="my-0 font-weight-normal"><fmt:message key="label.wordProyectos" /></h4>
                    </div>
                    <div class="card-body">
                        <button type="button" class="btn btn-lg btn-block btn-primary"><fmt:message key="label.verProyectos" /> ...</button>
                    </div>
                </div>
            </div>
                    
                    
                    
                    
    </body>
</html>
