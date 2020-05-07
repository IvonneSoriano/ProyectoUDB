<%-- 
    Document   : TicketView
    Created on : Apr 28, 2020, 12:47:07 PM
    Author     : Rick
--%>

<%@page import="sv.edu.udb.models.Ticket"%>
<%@page import="sv.edu.udb.util.RequestStatus"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${param['locale'] != null}">
    <fmt:setLocale value="${param['locale']}" scope="session" />
</c:if>
<fmt:setBundle basename="AplicationResource"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Java Avanzado - Ticket</title>             
        <jsp:include page="/common/cabecera.jsp"/>

        <style>
            .bd-placeholder-img {
                font-size: 1.125rem;
                text-anchor: middle;
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
            }

            @media (min-width: 768px) {
                .bd-placeholder-img-lg {
                    font-size: 3.5rem;
                }
            }
            .container {
                max-width: 960px;
            }

            .lh-condensed { 
                line-height: 1.25; 
            }
        </style>

        <script type="text/javascript">
            // Example starter JavaScript for disabling form submissions if there are invalid fields
            (function () {
                'use strict'

                window.addEventListener('load', function () {
                    // Fetch all the forms we want to apply custom Bootstrap validation styles to
                    var forms = document.getElementsByClassName('needs-validation')

                    // Loop over them and prevent submission
                    Array.prototype.filter.call(forms, function (form) {
                        form.addEventListener('submit', function (event) {
                            if (form.checkValidity() === false) {
                                event.preventDefault()
                                event.stopPropagation()
                            }
                            form.classList.add('was-validated')
                        }, false)
                    })
                }, false)
            }())
        </script>
        <script type="text/javascript">

            $(document).ready(function () {
                // Read value on page load
                $("#rangeAvanceVal").html($("#customRange").val());

                // Read value on change
                $("#rangeAvance").change(function () {
                    $("#rangeAvanceVal").html($(this).val() + '%');
                });
            });
        </script>
        <script>
            function enableTesterDropdown() {
                let statusDropdown = document.getElementById("ticketStatus");
                let selectedStatus = statusDropdown.options[statusDropdown.selectedIndex].value;
                let div = document.getElementById("testerSection");
                let testerDropdown = document.getElementById("tester");

                if (selectedStatus === "ESPERANDO_APROBACION_AREA_SOLICITANTE") {
                    div.removeAttribute("hidden");
                    div.style.visibility = "visible";
                    testerDropdown.setAttribute("required", "");
                } else {
                    console.log("hide section, it's not required!")
                    div.style.visibility = "hidden";
                    testerDropdown.removeAttribute("required");
                }
            }
        </script>
        <!-- This is used for datepicker - do not delete -->
        <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
        <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />

    </head>
    <body>
        <jsp:include page="/common/navbar.jsp"/>
        <c:set var="ticket" value="${requestScope.ticketEntity}"/>
        <c:set var="request" value="${ticket.getRequest()}" />
        <c:set var="comments" value="${request.getCommentsList()}" />
        <c:set var="programmersAvailable" value="${requestScope.programmersList}"/>
        <c:set var="testersAvailable" value="${requestScope.testersList}"/>
        <br>
        <div class="container">

            <div class="row">
                <div class="col-md-4 order-md-2 mb-4">
                    <h4 class="d-flex justify-content-between align-items-center mb-3">
                        <span class="text-muted">Comentarios</span>
                        <span class="badge badge-secondary badge-pill"><c:out value="${request.getCommentsList().size()}" default="0"/></span>
                    </h4>

                    <form class="card p-2" id="addCommentForm" name="addCommentForm" 
                          role="form" method="post" enctype="multipart/form-data"
                          action="${pageContext.request.contextPath}/tickets.do?op=agregarComentario&reqId=${ticket.getRequestId()}">
                        <div class="mb-3">
                            <!-- hidden fields that contain values for query -->
                            <input id="reqId" name="reqId" value="<c:out value="${ticket.getRequestId()}"/>" hidden/>                            
                            <input id="ticketId" name="ticketId" value="<c:out value="${ticket.getIdTicket()}"/>" hidden/>

                            <div class="custom-file">
                                <label class="custom-file-label" for="attachment">Seleccionar Archivo</label>
                                <input type="file" class="custom-file-input" id="attachment" name="attachment" lang="es">
                            </div>
                            <br><br>
                            <textarea method="post" class="form-control" id="ticketDescription" name="ticketDescription" rows="3" form="addCommentForm" placeholder="" value="" required></textarea>
                            <div class="invalid-feedback">
                                Valid project description is required.
                            </div>
                        </div>
                        <button type="submit" class="btn btn-secondary btn-block">Agregar</button>
                    </form>

                    <ul class="list-group mb-3">
                        <c:set var="commentCount" value="0" scope="page" />
                        <c:forEach items="${comments}" var="comment">
                            <c:set var="commentCount" value="${commentCount + 1}" scope="page"/>
                            <li class="list-group-item d-flex justify-content-between lh-condensed">
                                <div>
                                    <h6 class="my-0">Comentario #<c:out value="${commentCount}"/></h6>
                                    <small class="text-muted"><c:out value="${comment.getCommentText()}"/></small>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>


                <div class="col-md-8 order-md-1">

                    <form id="updateTicketForm" name="updateTicketForm" 
                          role="form" method="post"
                          action="${pageContext.request.contextPath}/tickets.do?op=modificar">

                        <input id="ticketId" name="ticketId" value="<c:out value="${ticket.getIdTicket()}"/>" hidden/>
                        <h4 class="mb-3">Ticket - <c:out value="${ticket.getInternalCode()}" default="########"></c:out></h4>
                            <hr class="mb-4">
                            <form class="needs-validation" novalidate>

                                <div class="mb-3">
                                    <label for="ticketDescription">Descripcion:</label>
                                    <textarea class="form-control" id="ticketDescription" rows="3" name="ticketDescription"
                                              placeholder="<c:out value="${request.getRequestDescription()}" default="########"/>" 
                                    value="<c:out value="${request.getRequestDescription()}" default="########"/>" required disabled></textarea>
                                <div class="invalid-feedback">
                                    Valid project description is required.
                                </div>
                            </div>


                            <div class="mb-3">
                                <div class="input-group">   
                                    <span id="rangeAvanceValLbl">Avance: <span id="rangeAvanceVal">${ticket.getAvance()}%</span></span>
                                    <input type="range" class="custom-range" min="0" max="100" step="5" id="rangeAvance" name="rangeAvance" value="${ticket.getAvance()}">

                                    <div class="invalid-feedback" style="width: 100%;">
                                        Your username is required.
                                    </div>
                                </div>                           
                            </div>

                            <!--label for="email">Email <span class="text-muted">(Optional)</span></label-->

                            <div class="row">
                                <div class="col-md-5 mb-3">
                                    <label for="ticketStatus">Estado:</label>
                                    <select class="custom-select d-block w-100" id="ticketStatus" name="ticketStatus" onchange="enableTesterDropdown()" required >

                                        <option value=""><fmt:message key="label.seleccionarItem"/>...</option>
                                        <%
                                            RequestStatus[] list = RequestStatus.values();
                                            for (RequestStatus status : list) {
                                        %> 
                                        <option value="<%= status.name()%>" 
                                                <%
                                                    Ticket receivedEntity = (Ticket) request.getAttribute("ticketEntity");
                                                    if (status.name().equalsIgnoreCase(receivedEntity.getTicketStatus())) {

                                                %>
                                                selected="selected"
                                                <%                                                } // end-if
                                                %> 
                                                ><%= status.name()%></option>
                                        <%
                                            } // end-for
                                        %>
                                    </select>
                                    <div class="invalid-feedback">
                                        <fmt:message key="label.validacionStatus"/>
                                    </div>
                                </div>

                                <!-- Dropdown: programadores -->
                                <div class="col-md-4 mb-3">
                                    <label for="programador">Programador:</label>
                                    <select class="custom-select d-block w-100" id="programador" name="programador" required>
                                        <option value=""><fmt:message key="label.seleccionarItem"/>...</option>

                                        <c:forEach items="${programmersAvailable}" var="programmer">
                                            <option value="${programmer.getEmployeeId()}"
                                                    <c:if test="${ticket.getIdProgrammer() == programmer.getEmployeeId()}">
                                                        selected="selected"
                                                    </c:if>
                                                    >${programmer.getFullName()}</option>
                                        </c:forEach>
                                    </select>
                                    <div class="invalid-feedback">
                                        <fmt:message key="label.validacionProgramador"/>
                                    </div>
                                </div>



                                <!-- Dropdown: Testers -->
                                <div class="col-md-3 mb-3" id="testerSection" <c:if test="${ticket.getIdTester() <= 0}">hidden</c:if> >
                                        <label for="tester">QA:</label>
                                        <select class="custom-select d-block w-100" id="tester" name="tester">
                                            <option value=""><fmt:message key="label.seleccionarItem"/>...</option>

                                        <c:forEach items="${testersAvailable}" var="tester">
                                            <option value="${tester.getEmployeeId()}"
                                                    <c:if test="${ticket.getIdTester() == tester.getEmployeeId()}">
                                                        selected="selected"
                                                    </c:if>
                                                    >${tester.getFullName()}</option>
                                        </c:forEach>
                                    </select>
                                    <div class="invalid-feedback">
                                        <fmt:message key="label.validacionQA"/>
                                    </div>
                                </div>

                            </div>
                            <hr class="mb-4">

                            <div class="row">
                                <div class="col-md-5 mb-3">
                                    <label for="fechaInicio">Fecha Inicio:</label>
                                    <input id="fechaInicio" name="fechaInicio" width="250" value="${ticket.getFormattedDate("start")}" required/>
                                    <div class="invalid-feedback">
                                        Please provide a valid date.
                                    </div>
                                </div>
                                <div class="col-md-3 mb-3">
                                    <label for="fechaFin">Fecha Fin:</label>  
                                    <input id="fechaFin" name="fechaFin" width="250" value="${ticket.getFormattedDate("end")}" required/>
                                    <div class="invalid-feedback">
                                        Please provide a valid date.
                                    </div>
                                </div>
                            </div>

                            <!--div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="same-address">
                                <label class="custom-control-label" for="same-address">Shipping address is the same as my billing address</label>
                            </div-->

                            <hr class="mb-4">

                            <!--h4 class="mb-3">Payment</h4-->
                            <div class="form-group">
                                
                            <button class="btn btn-primary btn-lg btn-block" type="submit">Guardar</button>
                            <br>
                            <a href="${pageContext.request.contextPath}/tickets.do?op=listar">Regresar</a>

                            </div>
                        </form>

                </div>
            </div>
        </div>
    </body>
    <script>
        $('#fechaInicio').datepicker({
            uiLibrary: 'bootstrap4'
        });
        $('#fechaFin').datepicker({
            uiLibrary: 'bootstrap4'
        });
    </script>
</html>
