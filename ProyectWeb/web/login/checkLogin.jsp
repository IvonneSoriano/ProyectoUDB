<%-- 
    Document   : checkLogin
    Created on : Apr 23, 2020, 6:21:00 PM
    Author     : Rick
--%>

<%@page import="sv.edu.udb.util.DAODefaults"%>
<%@page import="sv.edu.udb.models.Employee"%>
<%@page import="sv.edu.udb.models.EmployeeDAO"%>
<%@page session="true" language="java" import="java.util.*" %>
<%
    EmployeeDAO dao = new EmployeeDAO();
    Optional<Employee> foundEmp = dao.getEmployeeByUsername("", new String().toCharArray());
    Employee e = foundEmp.orElseGet(() -> new Employee(DAODefaults.NON_EXISTING_USER.getDefaultValue()));

    if (!e.getUsername().equals(DAODefaults.NON_EXISTING_USER.getDefaultValue())) {
        if (!e.getPassword().equals(DAODefaults.NON_EXISTING_USER.getDefaultValue())) {
            // welcome message
            // start session
%>
<jsp:forward page="../index.jsp" />
<%} else {
%>
<jsp:forward page="./login.jsp">
    <jsp:param name="error" value="Usuario y/o clave incorrecto. Vuelve a intentarlo."/>
</jsp:forward>
<%
        }
    }
%>