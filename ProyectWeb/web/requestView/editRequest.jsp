<%-- 
    Document   : project
    Created on : Apr 25, 2020, 7:13:50 PM
    Author     : 
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${param['locale'] != null}">
  <fmt:setLocale value="${param['locale']}" scope="session" />
</c:if>
<fmt:setBundle basename="AplicationResource"/>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Project</title>
        <jsp:include page="/common/cabecera.jsp"/>
    </head>
    <body>
        <jsp:include page="/common/navbar.jsp"/>
        <div class="container mt-5">
            <h1 fmt:message key=”label.titleNP”>Nuevo Requerimiento</h1>
            <form class="form mt-5" role="form" action="${pageContext.request.contextPath}/request.do?op=insertar" method="POST">
                
                <div class="form-group">
                    <label for="name" fmt:message key=”label.npName”>
                        Tipo de solicitud
                    </label>
                    <select class="form-control" name="tsoli" id="tsoli" >
                        <c:forEach items="${requestScope.trequest}" var="tsol">
                            <option value="${tsol.getRequestTypeId()}">${dpto.getRequestTypeName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="depto" fmt:message key=”label.pvDep”>
                        Proyecto Existente:
                    </label>
                    <select class="form-control" name="proj" id="proj" >
                        <c:forEach items="${requestScope.proyectos}" var="projc">
                            <option value="${projc.getProjectsId()}">${projc.getProjectName()}</option>
                        </c:forEach>
                    </select>
                </div>

   <div class="form-group">
                    <label for="description" fmt:message key=”label.npDesc”>
                        Descripción:
                    </label>
       <textarea class="form-control" id="description"  name="description"></textarea>
                </div>
                <div class="form-group">
                    <label for="description" fmt:message key=”label.npDesc”>
                        comentario:
                    </label>
       <textarea class="form-control" id="description"  name="comentario"></textarea>
                </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary"  name="Guardar" fmt:message key=”label.titleNP”> Guardar</button>
                        </div>
            </form>
        </div>
    </body>

</html>