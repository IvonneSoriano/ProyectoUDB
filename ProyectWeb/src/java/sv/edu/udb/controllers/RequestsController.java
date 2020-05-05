/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import sv.edu.udb.models.DeparmentDAO;
import sv.edu.udb.models.Employee;
import sv.edu.udb.models.Project;
import sv.edu.udb.models.Request;
import sv.edu.udb.models.RequestDAO;
import sv.edu.udb.models.ProjectDAO;
import sv.edu.udb.models.RequestType;
import sv.edu.udb.models.RequestTypeDAO;
import sv.edu.udb.models.FileRequestDAO;
import sv.edu.udb.util.DAODefaults;
import sv.edu.udb.util.RequestStatus;
/**
 *
 * @author kiss_
 */
@WebServlet(name = "RequestsController", urlPatterns = {"/requests.do"})
@MultipartConfig(maxFileSize = 16177215)
public class RequestsController extends HttpServlet {
 ArrayList<String> listaErrores = new ArrayList<>();
 RequestTypeDAO rtd = new RequestTypeDAO();
    Request modelRequest = new Request();
    RequestDAO rqDAO = new RequestDAO();
    int id, typeId, projectId, departmentId;
    ProjectDAO pd = new ProjectDAO();
    String description, status;
    Timestamp requestDate;
    private static Logger logger = Logger.getLogger(RequestsController.class);
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
       try (PrintWriter out = response.getWriter()){
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
                    nuevo(request, response);
                    break;
                case "insert":
                    insert(request,response);
                    break;
                    case "modificar":
                    obtener(request,response);
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

    
    
     private void list(HttpServletRequest request, HttpServletResponse response ){
         HttpSession miSesion = (HttpSession) request.getSession();
        try {
            
            Employee actual = (Employee) miSesion.getAttribute("employee");
            departmentId = actual.getDepartmentId();
            List<Request> re =  rqDAO.getRequestsByDepartmentId(departmentId);
//            List<Request> re =  rqDAO.getAll();
            if(re.size() != 0){
                request.setAttribute("listRequest", re );
            }
            else{
                request.setAttribute("listRequest", re );
            }
            
            request.getRequestDispatcher("/requestView/ListRequest.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            logger.error("Error in list Requests method. Message: " + ex.getMessage());
        }
    }
    private void insert(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, ServletException{
        try {
            typeId = Integer.parseInt(request.getParameter("tsoli"));
//            Aqui debe ir el depto del usuario actual
            Employee actual = (Employee) request.getSession().getAttribute("employee");
            departmentId = actual.getDepartmentId();
//            terminar cometn]
            requestDate = new Timestamp(System.currentTimeMillis());
            
            modelRequest.setIdTypeRequest(Integer.parseInt(request.getParameter("tsoli")));
            if(typeId == 1){
                modelRequest.setProjectId(0);
            }
            else{
                modelRequest.setProjectId(Integer.parseInt(request.getParameter("proj")));
            }
            
            modelRequest.setDepartmentId(departmentId);
            modelRequest.setRequestDate(requestDate);         
            modelRequest.setRequestStatus(RequestStatus.EN_ESPERA.toString());
            modelRequest.setRequestDescription(request.getParameter("description"));
            Part file = request.getPart("file");
            
           modelRequest.setFileIS(file.getInputStream());
            
                if(rqDAO.save(modelRequest)){
                    request.getSession().setAttribute("exito", "Solicitud registrado exitosamente");
                    response.sendRedirect(request.getContextPath() + "/requests.do?op=ver");
                }else{
                    request.getSession().setAttribute("fracaso", "Solicitud no ha sido ingresado");
                    response.sendRedirect(request.getContextPath() + "/requests.do?op=ver");
                }
            
        } catch (IOException ex) {
             logger.error("Error in insert Requests method. Message: " + ex.getMessage());
        }
    }
     private void nuevo(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Project> pro = pd.getAll();
//            pd.getProjbyDepto(id);
            List<RequestType> rt = rtd.getAll();
            request.setAttribute("proyectos", pro);
            request.setAttribute("rtypes", rt);
            request.getRequestDispatcher("/requestView/newRequest.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            logger.error("Error in logIn method. Message: " + e.getMessage());
        }
    }
     
     public void obtener(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {

            RequestDAO rd = new RequestDAO();
            Request project = getRequestById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("request", project);
            RequestTypeController c = new RequestTypeController();
            RequestType rto = c.findRequestTypeById(project.getIdTypeRequest());
            request.setAttribute("rType", rto);
            if(project.getProjectId() != 0){
                ProjectsController pc = new ProjectsController();
                Project p = pc.findById(project.getProjectId());
            request.setAttribute("proj", p);    
            }
            request.getRequestDispatcher("/requestView/editRequest.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            logger.error("Error in Obtener Request method. Message: " + e.getMessage());
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
             logger.error("Error in update Requests method. Message: " + ex.getMessage());
        }

    }
    private void delete(HttpServletRequest request, HttpServletResponse response){
         try {
            projectId = Integer.parseInt(request.getParameter("id"));
            modelRequest.setProjectId(projectId);
           boolean r = rqDAO.delete(modelRequest);
            if(r){
                request.setAttribute("exito","Solicitud eliminada");
                request.getRequestDispatcher(request.getContextPath() + "/request.do?op=ver").forward(request, response);
            }else{
                request.setAttribute("fracaso","No se ha podido eliminar");
                request.getRequestDispatcher(request.getContextPath() + "/request.do?op=ver").forward(request, response);
            }
             
        } catch (IOException | ServletException ex) {
               logger.error("Error in delete Requests method. Message: " + ex.getMessage());
        }
    }
    
    
       public Request getRequestById(int id) {
        RequestDAO dao = new RequestDAO();
        Optional<Request> req = dao.get(id);
        return req.orElseGet(() -> new Request(DAODefaults.NO_REQUEST_FOUND.getDefaultValue()));
    }
}
