<%-- 
    Document   : TicketList
    Created on : Apr 28, 2020, 11:13:41 PM
    Author     : Rick
--%>
<%@page import="sv.edu.udb.util.Validations"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${param['locale'] != null}">
    <fmt:setLocale value="${param['locale']}" scope="session" />
</c:if>
<fmt:setBundle basename="AplicationResource"/>
<style>

</style>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Java Avanzado - Listar Tickets</title>        
        <jsp:include page="/common/cabecera.jsp"/>
    </head>
    <body>
        <jsp:include page="/common/navbar.jsp"/>

        <div class="container mt-5">

            <h1 class="mb-5">
                <fmt:message key="label.titleLT"/></h1>

            <br>
            <div class="row">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover table-sm" id="tabla">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col" class="col-md-2">
                                    <fmt:message key="label.pvId"/></th>
                                <th scope="col"><fmt:message key="label.rqId"/></th>
                                <th scope="col"><fmt:message key="label.pvName"/></th>
                                <th scope="col"><fmt:message key="label.programmer"/></th>
                                <th scope="col"><fmt:message key="label.tester"/></th>
                                <th scope="col" class="col-md-2"> <fmt:message key="label.rvState"/></th>
                                <th scope="col"><fmt:message key="label.intCode"/></th>
                                <th scope="col"><fmt:message key="label.dinicio"/></th>
                                <th scope="col"><fmt:message key="label.dfin"/></th>
                                <th scope="col"><fmt:message key="label.avance"/></th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach items="${requestScope.ticketsList}" var="ticket">
                                <tr>
                                    <td>${ticket.getIdTicket()}</td>
                                    <td>${ticket.getRequestId()}</td>
                                    <td>${ticket.getProjectName(ticket.getProjectID())}</td>
                                    <td
                                        <c:if test="${ticket.getEmployeeName(ticket.getIdProgrammer()).equals('Sin Asignar')}">
                                            style="color: red;"
                                        </c:if>
                                        >
                                        ${ticket.getEmployeeName(ticket.getIdProgrammer())}
                                    </td>
                                    <td
                                        <c:if test="${ticket.getEmployeeName(ticket.getIdTester()).equals('Sin Asignar')}">
                                            style="color: red;"
                                        </c:if>
                                        >${ticket.getEmployeeName(ticket.getIdTester())}</td>
                                    <td>${ticket.getTicketStatus()}</td>
                                    <td>${ticket.getInternalCode()}</td>
                                    <td>${ticket.getStartDate()}</td>
                                    <td>${ticket.getEndDate()}</td>
                                    <td><b>${ticket.getAvance()}%<b></td>
                                                <td>
                                                    <a class="btn btn-primary"
                                                       href="${pageContext.request.contextPath}/tickets.do?op=verTicket&id=${ticket.getIdTicket()}"><span
                                                            class="glyphicon glyphicon-edit"></span><fmt:message key="label.Edit"/></a>
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
