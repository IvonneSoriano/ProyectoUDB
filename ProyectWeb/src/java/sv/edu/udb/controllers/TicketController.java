/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.log4j.Logger;
import sv.edu.udb.models.Attachment;
import sv.edu.udb.models.AttachmentDAO;
import sv.edu.udb.models.Comment;
import sv.edu.udb.models.CommentDAO;
import sv.edu.udb.models.EmployeeDAO;
import sv.edu.udb.models.Ticket;
import sv.edu.udb.models.TicketDAO;
import sv.edu.udb.util.Roles;

/**
 *
 * @author Rick
 */
@WebServlet(name = "TicketController", urlPatterns = {"/tickets.do"})
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class TicketController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(TicketController.class);
    private final TicketDAO dao = new TicketDAO();

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
                //logOut(request, response);
                return;
            }

            String operacion = request.getParameter("op");

            switch (operacion) {
                case "agregarComentario":
                    agregarComentario(request, response);
                    break;
                case "modificar":
                    actualizarTicket(request, response);
                    break;
                case "listar":
                    listarTickets(request, response);
                    break;
                case "verTicket":
                    verTicket(request, response);
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

    public void agregarComentario(HttpServletRequest request, HttpServletResponse response) {

        try {
            String reqId = (String) request.getParameter("reqId");
            String ticketId = (String) request.getParameter("ticketId");
            int empId = (int) request.getSession().getAttribute("sessionEmployeeId");
            int depId = (int) request.getSession().getAttribute("sessionEmpDeparmentId");
            String text = (String) request.getParameter("ticketDescription");

            Comment c = new Comment();
            c.setCommentText(text);
            c.setRequestId(Integer.parseInt(reqId));
            c.setEmployeeId(empId);
            c.setDepartmentId(depId);
            c.setCommentDate(new Timestamp(System.currentTimeMillis()));

            if (new CommentDAO().save(c)) {
                request.setAttribute("comment_info", "Comentario agregado exitosamente!");
            } else {
                request.setAttribute("comment_error", "Comentario no fue agregado. Intente mas tarde!");
            }

            // ===== file upload code section =====
            Attachment a =  new Attachment();
            a.setCommentId(depId);
            InputStream inputStream = null; // input stream of the upload file            
            Part filePart = request.getPart("attachment"); // // obtains the upload file part in this multipart request

            if (filePart != null) {
                // prints out some information for debugging
                System.out.println(filePart.getName());
                System.out.println(filePart.getSize());
                System.out.println(filePart.getContentType());

                // obtains input stream of the upload file
                inputStream = filePart.getInputStream();
            }

//            if (new AttachmentDAO().save(c)) {
//                request.setAttribute("comment_info", "Archivo agregado exitosamente!");
//            } else {
//                request.setAttribute("comment_error", "Archivo no fue agregado. Intente mas tarde!");
//            }
            
            request.getRequestDispatcher("/tickets.do?op=verTicket&id=" + ticketId).forward(request, response);
        } catch (IOException | NumberFormatException | ServletException e) {
            logger.error("Error in agregarComentario() method. Message: " + e.getMessage());
        }

    }

    public void actualizarTicket(HttpServletRequest request, HttpServletResponse response) {

    }

    public void listarTickets(HttpServletRequest request, HttpServletResponse response) {
        try {
            int depId = (int) request.getSession().getAttribute("sessionEmpDeparmentId");
            request.setAttribute("ticketsList", dao.getAll(depId)); // setear los tickets filtrados por departamento
            request.getRequestDispatcher("tickets/TicketList.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            logger.error("Error in listarTickets() method. Message: " + ex.getMessage());
        }
    }

    public void verTicket(HttpServletRequest request, HttpServletResponse response) {
        try {
            EmployeeDAO empDao = new EmployeeDAO();
            String id = (String) request.getParameter("id");
            int depId = (int) request.getSession().getAttribute("sessionEmpDeparmentId");
            request.setAttribute("ticketEntity", dao.getOne(Integer.parseInt(id)));

            // enviar programadores disponibles            
            request.setAttribute("programmersList", empDao.getAllByRolAndDepto(Roles.PROGRAMADOR.getRolId(), depId));

            request.getRequestDispatcher("tickets/TicketView.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in verTicket() method. Message: " + e.getMessage());
        }
    }

    public boolean saveComment(Comment c) {
        return new CommentDAO().save(c);
    }

    public List<Comment> getAllid(int id) {
        return new CommentDAO().getAllByRequest(id);
    }

    public List<Ticket> getAllTickets() {
        TicketDAO dao = new TicketDAO();
        List<Ticket> tickets = dao.getAll();

        return tickets;
    }

    public List<Ticket> getAllTickets(int d) {
        TicketDAO dao = new TicketDAO();
        List<Ticket> tickets = dao.getAll(d);

        return tickets;
    }

    public Ticket showTicket(int id) {
        TicketDAO dao = new TicketDAO();
        return dao.getOne(id);
    }

    public boolean showStatusTicket(int p, String par) {
        TicketDAO dao = new TicketDAO();
        return dao.checkEmployee(p, par);
    }

    public boolean updateP(int t, int p) {
        TicketDAO dao = new TicketDAO();
        return dao.updateProgrammer(t, p);
    }

    public boolean updateT(int t, int p) {
        TicketDAO dao = new TicketDAO();
        return dao.updateQA(t, p);
    }

    public boolean updateS(int t, String p) {
        TicketDAO dao = new TicketDAO();
        return dao.updateStatus(t, p);
    }

    public List<Ticket> checkTickets(int id) {
        TicketDAO dao = new TicketDAO();
        return dao.verifyTesterNeeded(id);
    }

    public int checkIC(String id) {
        TicketDAO dao = new TicketDAO();
        return dao.verifyInternalCode(id);
    }

    public boolean saveTicket(Ticket t) {
        return new TicketDAO().save(t);
    }

    public boolean updateAvance(int idTicket, float a) {
        TicketDAO dao = new TicketDAO();
        return dao.updateAvance(idTicket, a);
    }

    public float getAvance(int id) {
        TicketDAO dao = new TicketDAO();
        return dao.getAvance(id);
    }
}
