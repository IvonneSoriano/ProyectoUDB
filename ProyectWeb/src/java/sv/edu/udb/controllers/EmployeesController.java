/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sv.edu.udb.models.Deparment;
import sv.edu.udb.models.DeparmentDAO;
import sv.edu.udb.models.Employee;
import sv.edu.udb.models.EmployeeDAO;
import sv.edu.udb.models.Rol;
import sv.edu.udb.models.RolDAO;

/**
 *
 * @author kiss_
 */
@WebServlet(name = "EmployeesController", urlPatterns = {"/empleados.do"})
public class EmployeesController extends HttpServlet {
    private static Logger logger = Logger.getLogger(ProjectController.class);

    ArrayList<String> listaErrores = new ArrayList<>();
    Employee modelEmployee = new Employee();
    EmployeeDAO empDAO = new EmployeeDAO();
    Deparment modelDeparment = new Deparment();
    DeparmentDAO depDao = new DeparmentDAO();
    Rol modelRol = new Rol();
    RolDAO rolDAO = new RolDAO();
    
    
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
           if (request.getParameter("op")== null) {
               list(request, response);
               return;
            }
            String operacion = request.getParameter("op");
            switch(operacion){
                case "ver":
                    list(request,response);
                    break;
                case "crear":
                    newEmployee(request, response);
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
        }
            catch (Exception e) {
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

    
    
    
     private void list(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setAttribute("listEmployees", empDAO.getAll());
            request.getRequestDispatcher("/employeeView/ListEmployee.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error("Error in List method. Message: " + e.getMessage());
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
            if(listaErrores.size() > 0){
                request.setAttribute("employee", modelEmployee);
                request.setAttribute("listaErrores", listaErrores);
                request.getRequestDispatcher("empleado.do?op=list").forward(request, response);
            }else{
                if(empDAO.save(modelEmployee)){
                    request.getSession().setAttribute("exito", "Empleado registrado exitosamente");
                    response.sendRedirect(request.getContextPath() + "/empleados.do?op=ver");
                }
                else{
                    request.getSession().setAttribute("fracaso", "Empleado no ha sido ingresado");
                    response.sendRedirect(request.getContextPath() + "/empleados.do?op=ver");
                }
            }
            
        }catch(IOException | ServletException e){
             logger.error("Error in insert Employees method. Message: " + e.getMessage());
        }
    }
    private void getEmployee(HttpServletRequest request, HttpServletResponse response){
        try{
            String idemp = request.getParameter("id");
            int id = Integer.parseInt(idemp);
            if (empDAO.getEmployeeById(id) != null) {
                request.setAttribute("employee", empDAO.getEmployeeById(id));
                request.setAttribute("listRol", rolDAO.getAll());
                request.setAttribute("listDeparment", depDao.getAll());               
                request.getRequestDispatcher("/employeeView/EditEmployee.jsp").forward(request, response);
            }else{
                response.sendRedirect("");
            }
        }catch(IOException | ServletException e){
            logger.error("Error in getEmployees method. Message: " + e.getMessage());
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
            if(listaErrores.size() > 0){
                request.setAttribute("employee", modelEmployee);
                request.setAttribute("listaErrores", listaErrores);
                request.getRequestDispatcher("empleado.do?op=get").forward(request, response);
            }else{
                if(empDAO.updateUser(modelEmployee, request.getParameter("newPasword"))){
                    request.getSession().setAttribute("exito", "Empleado modificado");
                    response.sendRedirect(request.getContextPath() + "/empleados.do?op=ver");
                }
                else{
                    request.getSession().setAttribute("fracaso", "Empleado no ha sido modificado");
                    response.sendRedirect(request.getContextPath() + "/empleados.do?op=ver");
                }
            }
       
        } catch (IOException | ServletException e) {
            logger.error("Error in updateEmployees method. Message: " + e.getMessage());
        }

    }
    private void newEmployee(HttpServletRequest request, HttpServletResponse response){
        try{
            
                request.setAttribute("listRol", rolDAO.getAll());
                request.setAttribute("listDeparment", depDao.getAll());               
                request.getRequestDispatcher("/employeeView/NewEmployee.jsp").forward(request, response);
            
        }catch(IOException | ServletException e){
            logger.error("Error in newEmployees method. Message: " + e.getMessage());
        }
    }
    private void delete(HttpServletRequest request, HttpServletResponse response){
        try{
            String idemp = request.getParameter("id");
            int id = Integer.parseInt(idemp);
            modelEmployee.setEmployeeId(id);
            boolean r =empDAO.delete(modelEmployee);
            if (r) {
                request.setAttribute("exito","Empleado eliminado");
                
            }else{
                request.setAttribute("fracaso","No se ha podido eliminado");
            }
            request.getRequestDispatcher("/empleados.do?op=ver").forward(request, response);
        }catch(IOException | ServletException e){
            logger.error("Error in deleteEmployees method. Message: " + e.getMessage());
        }
    }

    
    
}
