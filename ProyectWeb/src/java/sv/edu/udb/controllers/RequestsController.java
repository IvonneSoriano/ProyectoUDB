/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;

import com.sun.org.apache.xerces.internal.dom.DeepNodeListImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
import sv.edu.udb.models.Deparment;
import sv.edu.udb.models.Employee;
import sv.edu.udb.models.Project;
import sv.edu.udb.models.Request;
import sv.edu.udb.models.RequestDAO;
import sv.edu.udb.models.ProjectDAO;
import sv.edu.udb.models.RequestType;
import sv.edu.udb.models.Ticket;
import sv.edu.udb.models.RequestTypeDAO;
import sv.edu.udb.models.TicketDAO;
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
        try (PrintWriter out = response.getWriter()) {
            if (request.getParameter("op") == null) {
                list(request, response);
                return;
            }
            String operacion = request.getParameter("op");
            switch (operacion) {
                case "ver":
                    list(request, response);
                    break;
                case "listar":
                    listAwait(request, response);
                    break;
                case "crear":
                    nuevo(request, response);
                    break;
                case "insert":
                    insert(request, response);
                    break;
                case "modificar":
                    obtener(request, response);
                    break;
                case "aprobar":
                    update(request, response);
                    break;
                case "reject":
                    reject(request, response);
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

    private void list(HttpServletRequest request, HttpServletResponse response) {
        HttpSession miSesion = (HttpSession) request.getSession();
        try {

            Employee actual = (Employee) miSesion.getAttribute("employee");
            departmentId = actual.getDepartmentId();
            List<Request> re = new ArrayList<>();
            if (actual.getRolId() == 5) {
                re = rqDAO.getAll();
            } else {
                re = rqDAO.getRequestsByDepartmentId(departmentId);
            }

//            List<Request> re =  rqDAO.getAll();
            if (re.size() != 0) {
                request.setAttribute("listRequest", re);
            } else {
                request.setAttribute("listRequest", re);
            }

            request.getRequestDispatcher("/requestView/ListRequest.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            logger.error("Error in list Requests method. Message: " + ex.getMessage());
        }
    }

    private void listAwait(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        HttpSession miSesion = (HttpSession) request.getSession();
        try {

            Integer actual = (Integer) miSesion.getAttribute("depto");
            List<Request> re = rqDAO.getRequestsByStatusAndDep(RequestStatus.EN_ESPERA.toString(), actual);
//            List<Request> re =  rqDAO.getAll();
            if (re.size() != 0) {
                request.setAttribute("listRequest", re);
            } else {
                request.setAttribute("listRequest", re);
            }

            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            logger.error("Error in list Requests method. Message: " + ex.getMessage());
        }
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, ServletException {
        try {
            typeId = Integer.parseInt(request.getParameter("tsoli"));
//            Aqui debe ir el depto del usuario actual
            Employee actual = (Employee) request.getSession().getAttribute("employee");
            departmentId = actual.getDepartmentId();
//            terminar cometn]
            requestDate = new Timestamp(System.currentTimeMillis());

            modelRequest.setIdTypeRequest(typeId);
            if (typeId == 1) {
                modelRequest.setProjectId(0);
            } else {
                modelRequest.setProjectId(Integer.parseInt(request.getParameter("proj")));
            }

            modelRequest.setDepartmentId(departmentId);
            modelRequest.setRequestDate(requestDate);
            modelRequest.setRequestStatus(RequestStatus.EN_ESPERA.toString());
            modelRequest.setRequestDescription(request.getParameter("description"));
            Part file = request.getPart("file");

            modelRequest.setFileIS(file.getInputStream());

            if (rqDAO.save(modelRequest)) {
                request.getSession().setAttribute("exito", "Solicitud registrado exitosamente");
                response.sendRedirect(request.getContextPath() + "/requests.do?op=ver");
            } else {
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
            if (project.getProjectId() != 0) {
                ProjectsController pc = new ProjectsController();
                Project p = pc.findById(project.getProjectId());
                request.setAttribute("proj", p);
            }
            request.getRequestDispatcher("/requestView/editRequest.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            logger.error("Error in Obtener Request method. Message: " + e.getMessage());
        }

    }

    private void abrirPDF(int id) {

    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            modelRequest.setId(id);
            typeId = Integer.parseInt(request.getParameter("tsoli"));
            requestDate = new Timestamp(System.currentTimeMillis());
            modelRequest.setRequestDate(requestDate);
            modelRequest.setRequestStatus(RequestStatus.SOLICITUD_ACEPTADA.toString());
            if (typeId == 1) {
                ProjectsController np = new ProjectsController();
                Project p = new Project();
                p.setCreationDate(requestDate);
                p.setDepartmentId(departmentId);
                p.setProjectDescription("Proyecto para el request " + id + ".");
                p.setProjectName("New Project R" + id);
                if (np.insertProject(p)) {
                    Project npid = pd.getLastProjectId();
                    projectId = npid.getProjectsId();
                    modelRequest.setProjectId(projectId);

                    if (rqDAO.updateAprobadoP(modelRequest)) {
                        request.getSession().setAttribute("exito", "Request aprobado");
                    }
                } else {
                    request.setAttribute("error", "Error al guardar el proyecto");
                }

            } else {
                projectId = Integer.parseInt(request.getParameter("proj"));
                modelRequest.setProjectId(projectId);
                if (rqDAO.updateStatusD(modelRequest)) {
                    request.getSession().setAttribute("exito", "Request aprobado");
                } else {
                    request.getSession().setAttribute("Error", "Request no ha podido ser aprobado");
                }
            }

            Employee actual = (Employee) request.getSession().getAttribute("employee");
            departmentId = actual.getDepartmentId();
            Ticket t = new Ticket();
            t.setRequestId(id);
            t.setStartDate(requestDate);
            t.setEndDate(requestDate);
            t.setProjectID(projectId);
            t.setTicketStatus(RequestStatus.ASIGNAR_PROGRAMADOR.toString());
            t.setIdProgrammer(0);
            t.setIdTester(0);
            DeparmentController d = new DeparmentController();
            Deparment depto = d.showDeparment(departmentId);
            t.setInternalCode(generateInternalCode(depto.getDepartmentName()));
            TicketDAO td = new TicketDAO();
            if (td.save(t)) {
                request.getSession().setAttribute("exito", "Ticket insertado");
            } else {
                request.getSession().setAttribute("fracaso", "Ticket no insertado");
            }
            response.sendRedirect(request.getContextPath() + "/requests.do?op=ver");
        } catch (IOException ex) {
            logger.error("Error in update Requests method. Message: " + ex.getMessage());
        }

    }

    private void reject(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            modelRequest.setId(id);
            requestDate = new Timestamp(System.currentTimeMillis());
            modelRequest.setRequestDate(requestDate);
            modelRequest.setRequestStatus(RequestStatus.SOLICITUD_RECHAZADA.toString());

            if (rqDAO.updateStatusD(modelRequest)) {
                request.getSession().setAttribute("exito", "Request rechazado");
            } else {
                request.getSession().setAttribute("Error", "Request no ha podido ser rechazado");
            }

            response.sendRedirect(request.getContextPath() + "/requests.do?op=ver");
        } catch (IOException ex) {
            logger.error("Error in update Requests method. Message: " + ex.getMessage());
        }

    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            projectId = Integer.parseInt(request.getParameter("id"));
            modelRequest.setProjectId(projectId);
            boolean r = rqDAO.delete(modelRequest);
            if (r) {
                request.setAttribute("exito", "Solicitud eliminada");
                request.getRequestDispatcher(request.getContextPath() + "/request.do?op=ver").forward(request, response);
            } else {
                request.setAttribute("fracaso", "No se ha podido eliminar");
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

    public String generateInternalCode(String name) {
        String code = "";
        code = name.toUpperCase().substring(0, 3);
        Calendar cal = Calendar.getInstance();
        String a = Integer.toString(cal.get(1)).substring(0, 2);
        code = code + a + ramdomNum();
        return code;
    }

    public String ramdomNum() {
        TicketDAO c = new TicketDAO();
        String num = "";
        int n;
        for (int i = 0; i < 3; i++) {
//        n = Math.floor(Math.random()*9);
            n = (int) Math.floor(Math.random() * 6 + 1);
            num += Integer.toString(n);
        }
        while (c.verifyInternalCode(num) > 0) {
            num = ramdomNum();
        }
        return num;
    }
}
