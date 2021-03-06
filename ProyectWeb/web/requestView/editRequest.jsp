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
        
        <c:set var="request" value="${requestScope.request}"/>
        <c:set var="rtypes" value="${requestScope.rtypes}"/>
        <c:set var="proj" value="${requestScope.proj}"/>
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
                    <div class="form-group">
                              <a type="submit" class="btn btn-primary" href="javascript:run('${request.getId()}')">
                            Ver Archivo
                        </a>
                    </div>
                <div class="form-row mt-5">
                    <div class="mr-5 form-group">
                        <a type="submit" class="btn btn-primary" href="javascript:aprobar('${request.getId()}','${rType.getId()}', '${proj.getProjectsId()}' )">
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
                    console.log($("#tsoli").children("option:selected").val());
                    if ($("#tsoli").children("option:selected").val() == 1) {
                        $(".proj").hide();
                    } else {
                        $(".proj").show();
                    }
            });



            function aprobar(id, so, t) {
                alertify.confirm("¿Realmente desea aprobar esta solicitud?", function (e) {
                    if (e) {
                        location.href = "requests.do?op=aprobar&id=" + id + "&tsoli=" + so+"&proj="+t;
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
            
             function run(id) {
                
                        location.href = "requests.do?op=run&id=" + id;
              
            }

        </script>
    </body>

</html>




