<%-- 
    Document   : TicketList
    Created on : Apr 28, 2020, 11:13:41 PM
    Author     : Rick
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${param['locale'] != null}">
    <fmt:setLocale value="${param['locale']}" scope="session" />
</c:if>
<fmt:setBundle basename="AplicationResource"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Java Avanzado - Listar Tickets</title>        
        <jsp:include page="/common/cabecera.jsp"/>
    </head>
    <body>
        <jsp:include page="/common/navbar.jsp"/>

        <div class="container">

            <h3>Lista de tickets</h3>

            <br>
            <div class="row">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover" id="tabla">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col" class="col-md-2">Codigo</th>
                                <th scope="col">Request Id</th>
                                <th scope="col">Project Id</th>
                                <th scope="col">Id Programador</th>
                                <th scope="col">Id Tester</th>
                                <th scope="col" class="col-md-2">Ticket Status</th>
                                <th scope="col">Internal Code</th>
                                <th scope="col">Start Date</th>
                                <th scope="col">End Date</th>
                                <th scope="col">Avance</th>
                                <th scope="col"><fmt:message key="label.operaciones"/></th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach items="${requestScope.ticketsList}" var="ticket">
                                <tr>
                                    <td>${ticket.getIdTicket()}</td>
                                    <td>${ticket.getRequestId()}</td>
                                    <td>${ticket.getProjectID()}</td>
                                    <td>${ticket.getIdProgrammer()}</td>
                                    <td>${ticket.getIdTester()}</td>
                                    <td>${ticket.getTicketStatus()}</td>
                                    <td>${ticket.getInternalCode()}</td>
                                    <td>${ticket.getStartDate()}</td>
                                    <td>${ticket.getEndDate()}</td>
                                    <td>${ticket.getAvance()}</td>
                                    <td>
                                        <a class="btn btn-primary"
                                           href="${pageContext.request.contextPath}/tickets.do?op=verTicket&id=${ticket.getIdTicket()}"><span
                                                class="glyphicon glyphicon-edit"></span> Editar</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </body>
</html>
