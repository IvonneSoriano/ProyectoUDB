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
        <title>Reporte de estados</title>
      <jsp:include page="/common/cabecera.jsp"/>
    </head>
    <body>
          <jsp:include page="/common/navbar.jsp"/>
          <div class="container">
            <div class="row">
                <div class="col-12">
                <h1 class="my-5">
                     <fmt:message key="label.rxE"/></h1>
                </div>
                <div class="col-8 text">
                    <p>
                        <fmt:message key="label.pR"/></p>
                <form class="form" action="${pageContext.request.contextPath}/ReportsStatus" method="POST" >
                    <div class="form-group">
                <select name="estado" class="form-control" >
                <option value="VENCIDO">Vencido</option>
                <option value="EN_ESPERA">En espera</option>
                <option value="FINALIZADO">Finalizado</option>
                <option value="ASIGNAR_PROGRAMADOR">Asignar programador</option>
                <option value="EN_DESARROLLO">En desarrollo</option>
                <option value="SOLICITUD_RECHAZADA">Solicitud rechazada</option>
                <option value="DEVUELTO_CON_OBSERVACIONES">Devuelto con observaciones</option>
                <option value="ESPERANDO_APROBACION_AREA_SOLICITANTE">Esperando aprobacion area solicitante</option>
                <option value="SOLICITUD_ACEPTADA">Solicitud aceptada</option>

                </select>
                        </div>
                      <div class="form-group">
                          <button type="submit"  class="btn btn-primary" ><fmt:message key="label.send"/> </button>
                  </div>
                </form>
                    </div>
            </div>
          </div>
    </body>
</html>
