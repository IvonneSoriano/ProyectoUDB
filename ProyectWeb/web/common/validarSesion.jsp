<%-- 
    Document   : validarSesion
    Created on : Apr 24, 2020, 12:35:15 AM
    Author     : Rick
--%>
<%
    HttpSession session_actual = request.getSession(false);
    String usuario = (String) session_actual.getAttribute("username");
    
    if (null == usuario || usuario.trim().equalsIgnoreCase("null")) {
         response.sendRedirect("login.jsp");
    }
%>
