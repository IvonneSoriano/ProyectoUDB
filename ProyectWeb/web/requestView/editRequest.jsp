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
                 <h1>
                <fmt:message key="label.tituloER"/>
            </h1>
            <!--action="${pageContext.request.contextPath}/requests.do?op=update"-->
             <form class="form mt-5" role="form"  method="POST" enctype="multipart/form-data">
                <input type="hidden" value="${request.getId()}" name="id" id="id">
                <div class="form-group">
                    <label for="tsoli" >
                        <fmt:message key="label.nrTsoli" />
                    </label>
                    <select class="form-control" name="tsoli" id="tsoli" required>
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
                    <textarea class="form-control" id="description"  name="description">${request.getRequestDescription()} required</textarea>
                </div>
                    <c:if test="${request.getFileIS()}">
                <div class="form-group">
                    <label for="file">
                        <fmt:message key="label.nrArchivo" />
                    </label>
                    <input type="file" class="form-control-file" placeholder="Seleccione el archivo a subir" id="file"  name="file">
                </div>
                    </c:if>
                 <div class="form-row mt-5">
                        <div class="mr-5 form-group">
                            <a type="submit" class="btn btn-primary" href="javascript:aprobar('${request.getId()}','${rType.getId()}')">
                            <fmt:message key="label.aprobar" />
                            </a>
                        </div>
                     <div class=" form-group">
                            <a type="submit" class="btn btn-danger" href="javascript:reject('${request.getId()}')">
                            <fmt:message key="label.rechazar" />
                            </a>
                        </div>
                     </div>
            </form>
        </div>
                            
                            
                            
                             <script>
                             $(document).ready(function () {
                                        if($(this).children("option:selected").val() !== 1){
                                            $(".proj").show();
                                        }
                                        else{
                                            $(".proj").hide();
                                        }
});
                            


            function aprobar(id, so) {
                alertify.confirm("¿Realmente desea aprobar esta solicitud?", function (e) {
                    if (e) {
                        location.href = "requests.do?op=aprobar&id=" + id+"&tsoli="+so;
                    }
                });
            }
            function reject(id) {
                alertify.confirm("¿Realmente desea rechazar esta solicitud?", function (e) {
                    if (e) {
                        location.href = "requests.do?op=reject&id=" + id;
                    }
                });
            }

        </script>
    </body>

</html>