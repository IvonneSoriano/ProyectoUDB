/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import sv.edu.udb.models.Deparment;
import sv.edu.udb.models.DeparmentDAO;

/**
 *
 * @author kiss_
 */
@WebServlet(name = "DepartmentController", urlPatterns = {"/DepartmentController"})
public class DepartmentController extends HttpServlet {

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
            if (request.getParameter("op") == null) {
                return;
            }
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DepartmentController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DepartmentController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    public void showDeparment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            DeparmentDAO dao = new DeparmentDAO();
 
                int id = Integer.parseInt(request.getParameter("id"));

                request.setAttribute("department", dao.getOne(id));
                request.getRequestDispatcher(request.getContextPath() + "/department.do?op=listar");
            
        } catch (Error e) {
// Logger.getLogger(DepartmentController.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Error in logIn method. Message: " + e.getMessage());
        }

    }

    public void showDeparments(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            DeparmentDAO dao = new DeparmentDAO();
            request.setAttribute("departamentos", dao.getAll());
            request.getRequestDispatcher(request.getContextPath() + "/department.do?op=listar");

        } catch (Error e) {
// Logger.getLogger(DepartmentController.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Error in logIn method. Message: " + e.getMessage());
        }

    }

    public void showDeparmentByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            DeparmentDAO dao = new DeparmentDAO();

            String name = request.getParameter("name");

            request.setAttribute("department", dao.getOneByName(name));
            request.getRequestDispatcher(request.getContextPath() + "/department.do?op=listar");

        } catch (Error e) {
// Logger.getLogger(DepartmentController.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Error in logIn method. Message: " + e.getMessage());
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

}
