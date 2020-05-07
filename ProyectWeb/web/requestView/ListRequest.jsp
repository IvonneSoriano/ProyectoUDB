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
        <title>Lista de solicitudes</title>
        <jsp:include page="/common/cabecera.jsp"/>
    </head>
    <body>
        <jsp:include page="/common/navbar.jsp"/>
        <div class="container">
            <div class="row">
                <h1 class="my-5"><fmt:message key="label.tituloLR"/></h1>
            </div>
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
                                    <td>${request.getDepartmentName(request.getDepartmentId())}</td>
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
                            <!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
                            
                            
                            
                            
        <script>
            function eliminar(id) {
                alertify.confirm("¿Realmente desea eliminar esta solicitud?", function (e) {
                    if (e) {
                        location.href = "request.do?op=delete&id=" + id;
                    }
                });
            }
        </script>
    </body>
</html>
