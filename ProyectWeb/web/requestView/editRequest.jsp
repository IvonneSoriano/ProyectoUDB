<%-- 
    Document   : project
    Created on : Apr 25, 2020, 7:13:50 PM
    Author     : 
--%>
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
        <title>New Project</title>
        <jsp:include page="/common/cabecera.jsp"/>
    </head>
    <body>
        <jsp:include page="/common/navbar.jsp"/>
        <div class="container mt-5">
            <h1 fmt:message key=”label.titleNP”>Nuevo Requerimiento</h1>
             <form class="form mt-5" role="form" action="${pageContext.request.contextPath}/requests.do?op=update" method="POST" enctype="multipart/form-data">
                <input type="hidden" value="${request.getId()}" name="id" id="id">
                <div class="form-group">
                    <label for="tsoli" >
                        <fmt:message key="label.nrTsoli" />
                    </label>
                    <select class="form-control" name="tsoli" id="tsoli" >
                            <option value="${rType.getId()}"> ${rType.getRequestTypeName()}</option>
                    </select>
                </div>
                   
                <div class="form-group proj">
                    <label for="proj">
                        <fmt:message key="label.nrPro" />
                    </label>
                    <select class="form-control" name="proj" id="proj" >
                            <option value="${proj.getProjectsId()}">${proj.getProjectName()}</option>
                    </select>
                </div>

   <div class="form-group">
                    <label for="description">
                        <fmt:message key="label.npDesc" />
                    </label>
                    <textarea class="form-control" id="description"  name="description">${request.getRequestDescription()}</textarea>
                </div>
                    
                <div class="form-group">
                    <label for="file">
                        <fmt:message key="label.nrArchivo" />
                    </label>
                    <input type="file" class="form-control-file" placeholder="Seleccione el archivo a subir" id="file"  name="file">
                </div>
                 <div class="form-row mt-5">
                        <div class="mr-5 form-group">
                            <button type="submit" class="btn btn-primary"  name="Guardar"> 
                            <fmt:message key="label.aprobar" />
                            </button>
                        </div>
                     <div class=" form-group">
                            <button type="submit" class="btn btn-danger"  name="Guardar"> 
                            <fmt:message key="label.rechazar" />
                            </button>
                        </div>
                     </div>
            </form>
        </div>
    </body>

</html>