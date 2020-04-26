/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sv.edu.udb.models.Project;
import sv.edu.udb.models.ProjectDAO;

/**
 *
 * @author kiss_
 */
@WebServlet(name = "ProjectController", urlPatterns = {"/proyectos.do"})
public class ProjectController extends HttpServlet {
private static Logger logger = Logger.getLogger(ProjectController.class);
ProjectDAO dao = new ProjectDAO();
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
            /* TODO output your page here. You may use following sample code. */
           if(request.getParameter("op") == null){
               listarProjects(request, response);
               return;
           }

           String ope = request.getParameter("op");
           switch(ope){
               case "ver":
                   listarProjects(request, response);
               break;
               case "crear":
                   insertProject(request, response);
                   break;
           }
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

    
    public void listarProjects(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {            
            request.setAttribute("listaProyectos", dao.getAll());
            request.getRequestDispatcher("/projects/projectsList.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            logger.error("Error in logIn method. Message: " + e.getMessage());
        }
        
    }
    
    
     public void insertProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
         try{
         Project miProject = new Project();
         miProject.setProjectName(request.getParameter("name"));
         miProject.setProjectDescription(request.getParameter("description"));
         miProject.setDepartmentId(Integer.parseInt(request.getParameter("depto")));
         
         request.setAttribute("exito", "Proyecto ingresado exitosamente");
         response.sendRedirect(request.getContextPath() + "/proyectos.do?op=listar");
        dao.save(miProject);
         }
         catch(IOException e){
          logger.error("Error in logIn method. Message: " + e.getMessage());   
         }
    }
     
      public void deleteProject(HttpServletRequest request, HttpServletResponse response) {
          try {
              int id = Integer.parseInt(request.getParameter("id"));
              Project p = new Project();
              p.setProjectsId(id);
              if(dao.delete(p)){
              request.setAttribute("exito", "Proyecto eliminado");    
              }
              else{
                  request.setAttribute("fracaso", "Proyecto no eliminado");    
              }
              

               request.getRequestDispatcher("/proyectos.do?op=listar").forward(request, response);

          } 
          catch (Exception e) {
              logger.error("Error in logIn method. Message: " + e.getMessage());   
          }
        
    }
}