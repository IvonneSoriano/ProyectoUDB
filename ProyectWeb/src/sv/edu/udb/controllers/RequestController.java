/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sv.edu.udb.models.Comment;
import sv.edu.udb.models.Request;
import sv.edu.udb.models.RequestDAO;

/**
 *
 * @author Imer Palma
 */
@WebServlet(name = "RequestController", urlPatterns = {"/request.do"})
public class RequestController extends HttpServlet {

    ArrayList<String> listaErrores = new ArrayList<>();
    Request modelRequest = new Request();
    RequestDAO rqDAO = new RequestDAO();
    int id, typeId, projectId, departmentId;
    String description, status;
    Timestamp requestDate;
    
    List<Comment> commentsList = new ArrayList<>();
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
                     getRequest(request, response);
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
            request.setAttribute("listRequest", rqDAO.getAll());
            request.getRequestDispatcher("").forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(RequestController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    private void insert(HttpServletRequest request, HttpServletResponse response){
        try {
            listaErrores.clear();
            typeId = Integer.parseInt(request.getParameter("typeid"));
            departmentId = Integer.parseInt(request.getParameter("departementid"));
            projectId = Integer.parseInt(request.getParameter("projectid"));
            requestDate = Timestamp.valueOf(request.getParameter("requestDate"));
            modelRequest.setIdTypeRequest(typeId);
            modelRequest.setDepartmentId(departmentId);
            modelRequest.setProjectId(projectId);
            modelRequest.setRequestDate(requestDate);
            modelRequest.setRequestStatus(request.getParameter("status"));
            modelRequest.setRequestDescription(request.getParameter("description"));
            request.setAttribute("request", rqDAO.save(modelRequest) );
            request.getRequestDispatcher("").forward(request, response);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(RequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void getRequest(HttpServletRequest request, HttpServletResponse response){
        try{
            String idrq = request.getParameter("id");
            int id = Integer.parseInt(idrq);
            if (rqDAO.getOneById(id) != null) {
                request.setAttribute("request", rqDAO.getOneById(id));
                request.getRequestDispatcher("").forward(request, response);
            }else{
                response.sendRedirect("");
            }
        }catch(IOException | ServletException ex){
            Logger.getLogger(RequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void update(HttpServletRequest request, HttpServletResponse response){
        try {
            listaErrores.clear();
            typeId = Integer.parseInt(request.getParameter("typeid"));
            departmentId = Integer.parseInt(request.getParameter("departementid"));
            projectId = Integer.parseInt(request.getParameter("projectid"));
            requestDate = Timestamp.valueOf(request.getParameter("requestDate"));
            modelRequest.setIdTypeRequest(typeId);
            modelRequest.setDepartmentId(departmentId);
            modelRequest.setProjectId(projectId);
            modelRequest.setRequestDate(requestDate);
            modelRequest.setRequestStatus(request.getParameter("status"));
            modelRequest.setRequestDescription(request.getParameter("description"));
            request.setAttribute("request", rqDAO.updateStatus(modelRequest) );
            request.getRequestDispatcher("").forward(request, response);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(RequestController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void delete(HttpServletRequest request, HttpServletResponse response){
        
    }
}
