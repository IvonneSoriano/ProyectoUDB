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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/proj.css" />
    </head>
    <body>
        <jsp:include page="/common/navbar.jsp"/>
        <div class="container mt-5">
            <h1>
                <fmt:message key="label.tituloNR"/>
            </h1>
            <form class="form mt-5" role="form" action="${pageContext.request.contextPath}/requests.do?op=insert" method="POST" enctype="multipart/form-data">
                
                <div class="form-group">
                    <label for="tsoli" >
                        <fmt:message key="label.nrTsoli" />
                    </label>
                    <select class="form-control" name="tsoli" id="tsoli" required>
                        
                        <c:forEach items="${requestScope.rtypes}" var="tsol">
                            <option value="${tsol.getId()}"> ${tsol.getRequestTypeName()}</option>
                        </c:forEach>
                    </select>
                </div>
                   
                <div class="form-group proj">
                    <label for="proj">
                        <fmt:message key="label.nrPro" />
                    </label>
                    <select class="form-control" name="proj" id="proj" >
                        <c:forEach items="${requestScope.proyectos}" var="projc">
                            <option value="${projc.getProjectsId()}">${projc.getProjectName()}</option>
                        </c:forEach>
                    </select>
                </div>

   <div class="form-group">
                    <label for="description">
                        <fmt:message key="label.npDesc" />
                    </label>
       <textarea class="form-control" id="description"  name="description" required></textarea>
                </div>
                    
                <div class="form-group">
                    <label for="file">
                        <fmt:message key="label.nrArchivo" />
                    </label>
                    <input type="file" class="form-control-file" placeholder="Seleccione el archivo a subir" id="file"  name="file">
                </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary"  name="Guardar"> 
                            <fmt:message key="label.save" />
                            </button>
                        </div>
            </form>
        </div>
                            <script>
                                    $( "#tsoli" ).change(function() {
                                        if($(this).children("option:selected").val() !== 1){
                                            $(".proj").show();
                                        }
                                        else{
                                            $(".proj").hide();
                                        }
});
                                </script>
    </body>

</html>