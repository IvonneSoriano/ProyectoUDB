/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sv.edu.udb.models.Employee;
import sv.edu.udb.models.EmployeeDAO;

/**
 *
 * @author Imer Palma
 */
@WebServlet(name = "EmployeeController", urlPatterns = {"/employee.do"})
public class EmployeeController extends HttpServlet {

    ArrayList<String> listaErrores = new ArrayList<>();
    Employee modelEmployee = new Employee();
    EmployeeDAO empDAO = new EmployeeDAO();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
    
        response.setContentType("text/html;charset=UTF -8");
        try (PrintWriter out = response.getWriter()){
            if (request.getParameter("op")== null) {
               list(request, response);
               return;
            }
            String operacion = request.getParameter("op");
            switch(operacion){
                case "list":
                    list(request,response);
                    break;
                case "new":
                    request.getRequestDispatcher("").forward(request, response);
                    break;
                case "insert":
                    insert(request,response);
                    break;
                case "get":
                     getEmployee(request, response);
                     break;
                case "update":
                    update(request,response);
                    break;
                case "delete":
                    delete(request, response);
                    break;
            }
        } catch (Exception e) {
        }
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
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
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
    private void list(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setAttribute("listEmployees", empDAO.getAll());
            request.getRequestDispatcher("").forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    private void insert(HttpServletRequest request, HttpServletResponse response){
        try{
            listaErrores.clear();
            modelEmployee.setEmployeeName(request.getParameter("name"));
            modelEmployee.setEmployeeLastname(request.getParameter("lastname"));
            modelEmployee.setDepartmentId(Integer.parseInt(request.getParameter("deparment")));
            modelEmployee.setRolId(Integer.parseInt(request.getParameter("rol")));
            modelEmployee.setPassword(request.getParameter("password"));
            modelEmployee.setUsername(request.getParameter("username"));
            request.setAttribute("employee", empDAO.save(modelEmployee));
            request.getRequestDispatcher("").forward(request, response);
        }catch(IOException | ServletException ex){
        }
    }
    private void getEmployee(HttpServletRequest request, HttpServletResponse response){
        try{
            String idemp = request.getParameter("id");
            int id = Integer.parseInt(idemp);
            if (empDAO.getEmployeeById(id) != null) {
                request.setAttribute("employee", empDAO.getEmployeeById(id));
                request.getRequestDispatcher("").forward(request, response);
            }else{
                response.sendRedirect("");
            }
        }catch(IOException | ServletException ex){
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void update(HttpServletRequest request, HttpServletResponse response){
        try {
            listaErrores.clear();
            modelEmployee.setEmployeeName(request.getParameter("name"));
            modelEmployee.setEmployeeLastname(request.getParameter("lastname"));
            modelEmployee.setDepartmentId(Integer.parseInt(request.getParameter("deparment")));
            modelEmployee.setRolId(Integer.parseInt(request.getParameter("rol")));
            modelEmployee.setPassword(request.getParameter("password"));
            modelEmployee.setUsername(request.getParameter("username"));
            request.setAttribute("employee", empDAO.updateUser(modelEmployee, modelEmployee.getPassword()));
            request.getRequestDispatcher("").forward(request, response);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void delete(HttpServletRequest request, HttpServletResponse response){
        try{
            String idemp = request.getParameter("id");
            int id = Integer.parseInt(idemp);
            modelEmployee.setEmployeeName(request.getParameter("name"));
            modelEmployee.setEmployeeLastname(request.getParameter("lastname"));
            modelEmployee.setDepartmentId(Integer.parseInt(request.getParameter("deparment")));
            modelEmployee.setRolId(Integer.parseInt(request.getParameter("rol")));
            modelEmployee.setPassword(request.getParameter("password"));
            modelEmployee.setUsername(request.getParameter("username"));
            if (empDAO.getEmployeeById(id) != null) {
                request.setAttribute("employee", empDAO.delete(modelEmployee));
                request.getRequestDispatcher("").forward(request, response);
            }else{
                response.sendRedirect("");
            }
        }catch(IOException | ServletException ex){
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
