<%-- 
    Document   : index
    Created on : Apr 16, 2020, 5:26:33 PM
    Author     : kiss_
--%>

<%@page import="sv.edu.udb.models.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<%
    HttpSession sesionOk = request.getSession();
    Employee empleado = (Employee) sesionOk.getAttribute("employee");
    Integer de = (Integer) sesionOk.getAttribute("rol");
    if (empleado == null) {
%>
<jsp:forward page="login/login.jsp">
    <jsp:param name="error" value="Es obligatorio identificarse"/>
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
        <style>
            .modal-dialog {
                max-width: 1000px!important;
            }
            .bell svg{
                width: 25px;
                height: 25px;
            }
        </style>
             <%
                if (de == 3) {
            %>                               
            <script>


                $(document).ready(function () {
                    $('#exampleModal').modal('toggle');
                });
            </script>   
            <%
                }
            %>
    </head>
    <body>
        <jsp:include page="/common/navbar.jsp"/>
        <br><br>
        <div class="container">
            <div class="row">
                <h2 class="col-10"><fmt:message key="label.saludo" /> <%=request.getSession().getAttribute("fullName")%> !</h2>
                  <%
                if (de == 3) {
            %>  
                <div class="col-2 "><button type="button" class="btn btn-primary bell" data-toggle="modal" data-target="#exampleModal">
                        <svg  viewBox="-36 1 511 511.99999" xmlns="http://www.w3.org/2000/svg">
                        <path d="m257.128906 452.128906c0-33.066406-26.847656-59.871094-59.96875-59.871094s-59.96875 26.804688-59.96875 59.871094 26.847656 59.871094 59.96875 59.871094 59.96875-26.804688 59.96875-59.871094zm0 0" fill="#f7c92b"/><path d="m197.160156 392.257812c-6.0625 0-11.914062.90625-17.429687 2.578126 24.617187 7.453124 42.539062 30.277343 42.539062 57.292968 0 27.011719-17.921875 49.839844-42.539062 57.292969 5.515625 1.671875 11.367187 2.578125 17.429687 2.578125 33.121094 0 59.96875-26.804688 59.96875-59.871094s-26.847656-59.871094-59.96875-59.871094zm0 0" fill="#f2b51d"/><path d="m204.167969 102.699219c-19.261719 3.863281-38.007813-8.589844-41.878907-27.820313-3.871093-19.226562 8.605469-37.945312 27.863282-41.808594 19.257812-3.863281 38.007812 8.589844 41.878906 27.820313 3.867188 19.226563-8.605469 37.945313-27.863281 41.808594zm0 0" fill="#f7c92b"/><path d="m232.03125 60.890625c-3.871094-19.230469-22.621094-31.683594-41.878906-27.820313-1.792969.359376-3.523438.851563-5.191406 1.453126 11.242187 4.109374 20.144531 13.789062 22.675781 26.367187 3.511719 17.4375-6.425781 34.457031-22.671875 40.355469 5.945312 2.171875 12.542968 2.789062 19.203125 1.453125 19.257812-3.863281 31.730469-22.582031 27.863281-41.808594zm0 0" fill="#f2b51d"/><path d="m345.691406 442.109375h-297.0625c-26.582031 0-48.128906-21.511719-48.128906-48.046875 0-14.691406 6.660156-28.59375 18.113281-37.816406l9.542969-7.6875c22.074219-17.773438 34.910156-44.566406 34.910156-72.878906v-50.496094c0-74.503906 60.492188-134.898438 135.117188-134.898438 74.621094 0 135.117187 60.394532 135.117187 134.898438v51.429687c0 27.777344 12.355469 54.125 33.726563 71.914063l9.292968 7.734375c11.089844 9.230469 17.5 22.902343 17.5 37.316406v.484375c0 26.535156-21.546874 48.046875-48.128906 48.046875zm0 0" fill="#f7e249"/><path d="m376.320312 356.261719-9.292968-7.734375c-21.371094-17.792969-33.726563-44.136719-33.726563-71.917969v-51.429687c0-74.5-60.496093-134.898438-135.117187-134.898438-13.222656 0-25.996094 1.90625-38.070313 5.441406 56.085938 16.410156 97.046875 68.15625 97.046875 129.457032v51.429687c0 27.78125 12.355469 54.125 33.726563 71.917969l9.296875 7.734375c11.085937 9.230469 17.5 22.902343 17.5 37.316406v.484375c0 26.535156-21.546875 48.046875-48.128906 48.046875h76.140624c26.578126 0 48.125-21.511719 48.125-48.046875v-.484375c0-14.414063-6.410156-28.085937-17.5-37.316406zm0 0" fill="#f7c92b"/><path d="m440.078125 115.777344c0-63.941406-51.917969-115.777344-115.964844-115.777344s-115.96875 51.835938-115.96875 115.777344 51.921875 115.777344 115.96875 115.777344 115.964844-51.835938 115.964844-115.777344zm0 0" fill="#fc476e"/><path d="m355.230469 4.222656c29.574219 20.980469 48.867187 55.460938 48.867187 94.441406 0 63.941407-51.917968 115.777344-115.964844 115.777344-10.78125 0-21.214843-1.472656-31.117187-4.222656 18.9375 13.4375 42.09375 21.335938 67.097656 21.335938 64.042969 0 115.964844-51.835938 115.964844-115.777344 0-53.179688-35.914063-97.976563-84.847656-111.554688zm0 0" fill="#cc2b5a"/><path d="m324.113281 169.851562c-4.472656 0-8.097656-3.617187-8.097656-8.082031v-77.960937l-3.019531 2.753906c-3.304688 3.007812-8.425782 2.773438-11.4375-.527344-3.011719-3.296875-2.777344-8.410156.527344-11.417968l16.570312-15.085938c2.371094-2.15625 5.789062-2.714844 8.726562-1.421875 2.933594 1.292969 4.824219 4.191406 4.824219 7.394531v96.265625c0 4.464844-3.625 8.082031-8.09375 8.082031zm0 0" fill="#e3faff"/></svg>
                    </button>
                      <%
                }
            %>  
                </div>
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
                        <h4 class="my-0 font-weight-normal"><fmt:message key="label.wordTickets" /></h4>
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
                        <a class="btn btn-lg btn-block btn-primary" href="${pageContext.request.contextPath}/tickets.do?op=listar"><fmt:message key="label.verTickets" /> ...</a>
                    </div>
                </div>
                <%
                    if (de % 2 != 0) {
                %>
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
                <%
                    }
                %>
                <%
                    if (de % 2 != 0) {
                %>
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
                <%
                    }
                %>
            </div>


            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title" id="exampleModalLabel">
                                <fmt:message key="label.rPend" />
                            </h3>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-12">

                                            <table class="table table-striped table-bordered table-hover table-sm"
                                                   id="tabla">
                                                <thead class="thead-dark">
                                                    <tr>
                                                        <th> <fmt:message key="label.pvId"/></th>
                                                        <th> <fmt:message key="label.rvTipo"/></th>
                                                        <th> <fmt:message key="label.rvDate"/></th>
                                                        <th> <fmt:message key="label.npDesc"/></th>
                                                        <th> <fmt:message key="label.rvState"/></th>
                                                        <th> <fmt:message key="label.pvName"/></th>
                                                        <th> <fmt:message key="label.pvDep"/></th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody>

                                                    <c:forEach items="${requestScope.listRequest}" var="request">
                                                        <tr>
                                                            <td>${request.getId()}</td>
                                                            <td>${request.getIdTypeRequest()}</td>
                                                            <td>${request.getRequestDate()}</td>
                                                            <td>${request.getRequestDescription()}</td>
                                                            <td>${request.getRequestStatus()}</td>
                                                            <td>${request.getProjectId()}</td>
                                                            <td>${request.getDepartmentId()}</td>
                                                            <td>
                                                                <a type="button" class="btn btn-secondary"
                                                                   href="${pageContext.request.contextPath}/requests.do?op=modificar&id=${request.getId()}"><fmt:message key="label.Operar"/></a>
                                                            </td> 
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>

                                </div>

                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                <fmt:message key="label.close"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>  
       
    </body>
</html>
