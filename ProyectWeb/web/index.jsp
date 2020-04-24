<%-- 
    Document   : index
    Created on : Apr 16, 2020, 5:26:33 PM
    Author     : kiss_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/common/validarSesion.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <title>Java Avanzado - Home</title>           
        <jsp:include page="/common/cabecera.jsp"/>
    </head>
    <body>
        <jsp:include page="/common/navbar.jsp"/>
        <div class="container">
            <div class="row">
                <h2>Bienvenido <%=request.getSession().getAttribute("fullName")%> !</h2>
            </div>
            <br>
            <div class="row">
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="glyphicon glyphicon-list-alt huge"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${requestScope.totallib}</div>
                                    <div><h4>Tickets</h4></div>
                                </div>
                            </div>
                        </div>
                        <a href="${pageContext.request.contextPath}/libros.do?op=listar">
                            <div class="panel-footer">
                                <span class="pull-left">Ver tickets</span>
                                <span class="pull-right"><i class="glyphicon glyphicon-arrow-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                            
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="glyphicon glyphicon-user huge"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${requestScope.totalaut}</div>
                                    <div><h4>Empleados</h4></div>
                                </div>
                            </div>
                        </div>
                        <a href="${pageContext.request.contextPath}/autores.do?op=listar">
                            <div class="panel-footer">
                                <span class="pull-left">Ver empleados</span>
                                <span class="pull-right"><i class="glyphicon glyphicon-arrow-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                            
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="glyphicon glyphicon-briefcase huge"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${requestScope.totaledit}</div>
                                    <div><h4>Proyectos</h4></div>
                                </div>
                            </div>
                        </div>
                        <a href="${pageContext.request.contextPath}/editoriales.do?op=listar">
                            <div class="panel-footer">
                                <span class="pull-left">Ver proyectos</span>
                                <span class="pull-right"><i class="glyphicon glyphicon-arrow-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                            
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="glyphicon glyphicon-inbox huge"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${requestScope.totalgen}</div>
                                    <div><h4>Peticiones</h4></div>
                                </div>
                            </div>
                        </div>
                        <a href="${pageContext.request.contextPath}/generos.do?op=listar">
                            <div class="panel-footer">
                                <span class="pull-left">Ver peticiones</span>
                                <span class="pull-right"><i class="glyphicon glyphicon-arrow-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
