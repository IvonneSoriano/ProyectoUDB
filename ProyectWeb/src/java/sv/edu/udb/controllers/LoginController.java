/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import org.apache.log4j.Logger;
import sv.edu.udb.models.Employee;
import sv.edu.udb.models.EmployeeDAO;
import sv.edu.udb.util.DAODefaults;

/**
 *
 * @author Rick
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login.do"})
public class LoginController extends HttpServlet {

    private static Logger logger = Logger.getLogger(LoginController.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            // si ninguna operacion viene en la request, significa cerrar sesion
            if (request.getParameter("op") == null) {
                logOut(request, response);
                return;
            }

            String operacion = request.getParameter("op");

            switch (operacion) {
                case "entrar":
                    logIn(request, response);
                    break;
            }

        } catch (Exception ex) {
            logger.error("Error in processRequest method. Message: " + ex.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void logIn(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Leer valores enviados desde el form mediante el objeto request
            String usuario = request.getParameter("usuario");
            String clave = request.getParameter("clave");

            // Utilizar dao para accesar la base de datos
            EmployeeDAO dao = new EmployeeDAO();
            Optional<Employee> foundEmp = dao.getEmployeeByUsername(usuario, clave.toCharArray());
            Employee e = foundEmp.orElseGet(() -> new Employee(DAODefaults.NON_EXISTING_USER.getDefaultValue()));

            // basado en la respuesta mostrar un warning o redireccionar
            if (!e.getUsername().equals(DAODefaults.NON_EXISTING_USER.getDefaultValue())) {
                if (!e.getPassword().equals(DAODefaults.NON_EXISTING_USER.getDefaultValue())) {
                    // crear sesion 
                    HttpSession session_actual = request.getSession(true);
                    session_actual.setAttribute("username", e.getUsername());
                    session_actual.setAttribute("fullName", e.getFullName());

                    // welcome message
                    request.getSession().setAttribute("exito", "login exitoso");
                    response.sendRedirect(request.getContextPath()+"/index.jsp");
                    return;
                }
                request.setAttribute("error", "Password Incorrecto");
                request.getRequestDispatcher("/login.do").forward(request, response);
                return;
            }
            request.setAttribute("error", "Usuario Incorrecto");
            request.getRequestDispatcher("/login.do").forward(request, response);
        } catch (Exception e) {
            logger.error("Error in logIn method. Message: " + e.getMessage());
        }
    }

    public void logOut(HttpServletRequest req, HttpServletResponse res) {
        try {
            req.getRequestDispatcher("/login/login.jsp").forward(req, res);
        } catch (Exception e) {
            logger.error("Error in logOut method. Message: " + e.getMessage());
        }
    }
}
