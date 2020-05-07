/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;
import javax.servlet.ServletContext;
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
import sv.edu.udb.models.TicketDAO;
import sv.edu.udb.util.DAODefaults;
import sv.edu.udb.util.Roles;

/**
 *
 * @author Rick
 */
@WebServlet(name = "TicketController", urlPatterns = {"/tickets.do"})
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class TicketController extends HttpServlet {

    // size of byte buffer to send file
    private static final int BUFFER_SIZE = 16177215;
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
                case "descargarAdjuntoC":
                    descargarAdjuntoC(request, response);
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
            InputStream inputStream = null; // input stream of the upload file            
            Part filePart = request.getPart("attachment"); // // obtains the upload file part in this multipart request

            if (filePart != null) {
                // prints out some information for debugging
                System.out.println(filePart.getName());
                System.out.println(filePart.getSize());
                System.out.println(filePart.getContentType());

                if (filePart.getSize() != 0L) {
                    // obtains input stream of the upload file
                    inputStream = filePart.getInputStream();

                    Optional<Comment> comment = new CommentDAO().getLastCommentFromTicket(Integer.parseInt(reqId));
                    c = comment.orElseGet(() -> new Comment(DAODefaults.NO_COMMENT_FOUND.getDefaultValue()));

                    if (!c.getCommentText().equals(DAODefaults.NO_COMMENT_FOUND.getDefaultValue())) {
                        Attachment a = new Attachment();
                        a.setCommentId(c.getCommentId());
                        a.setAttachmentName(filePart.getName());
                        a.setAttachmentSize(filePart.getSize());
                        a.setContentType(filePart.getContentType());
                        a.setFileIS(inputStream);

                        new AttachmentDAO().save(a);
                        request.setAttribute("addAttachmentToTicketSuccess", "Archivo adjunto agregado exitosamente!");

                    } else {
                        logger.warn("There was an error to store the comment! No attachment was added.");
                        request.setAttribute("addAttachmentToTicketError", "Archivo no fue agregado. Porque hubo un problema previo con el comentario intente mas tarde!");
                    }
                }
            }

            request.setAttribute("addCommentToTicketSuccess", "Comentario agregado exitosamente!");
            request.getRequestDispatcher("/tickets.do?op=verTicket&id=" + ticketId).forward(request, response);
        } catch (IOException | NumberFormatException | ServletException e) {
            logger.error("Error in agregarComentario() method. Message: " + e.getMessage());
        }

    }

    public void actualizarTicket(HttpServletRequest request, HttpServletResponse response) {
        try {
            int ticketId = Integer.parseInt(request.getParameter("ticketId"));
            float progress = Float.valueOf(request.getParameter("rangeAvance"));
            String status = (String) request.getParameter("ticketStatus");
            int programmerId = Integer.parseInt(request.getParameter("programador"));
            String start = (String) request.getParameter("fechaInicio");
            String end = (String) request.getParameter("fechaFin");

            dao.updateStatus(ticketId, status);
            dao.updateProgrammer(ticketId, programmerId);
            dao.updateAvance(ticketId, progress);

            // tester is optional - check before udpate since it might be null
            if (null != request.getParameter("tester") && request.getParameter("tester").length() > 0) {
                System.out.println("Updating QA");
                int testerId = Integer.parseInt(request.getParameter("tester"));
                dao.updateQA(ticketId, testerId);
            }

            // dates from UI to DB format
            try {
                DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date dateS = formatter.parse(start);
                Date dateE = formatter.parse(end);
                dao.updateFechas(ticketId, new Timestamp(dateS.getTime()), new Timestamp(dateE.getTime()));

                // regresar a la vista del ticket
                request.setAttribute("msgUpdateTicket", "Ticket Actualizado Correctamente!");
                request.getRequestDispatcher("/tickets.do?op=verTicket&id=" + ticketId).forward(request, response);
            } catch (Exception e) {
                logger.warn("Error en formato al actualizar fechas! Mensaje: " + e.getMessage());
            }
        } catch (Exception e) {
            logger.warn("Error actualizando ticket! Mensaje: " + e.getMessage());
            request.setAttribute("errorUpdateTicket", "Ticket no pudo ser actualizado!");
        }

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

            // enviar QA disponibles
            request.setAttribute("testersList", empDao.getAllByRolAndDepto(Roles.EMPLEADO_AREA_FUNCIONAL.getRolId(), depId));

            request.getRequestDispatcher("tickets/TicketView.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in verTicket() method. Message: " + e.getMessage());
        }
    }

    public void descargarAdjuntoC(HttpServletRequest request, HttpServletResponse response) {
        try {
            int commentId = Integer.parseInt(request.getParameter("id"));

            System.out.println("Comment ID: " + commentId);
            AttachmentDAO dao = new AttachmentDAO();
            Optional<Attachment> att = dao.get(commentId);
            Attachment attachmentFound = att.orElseGet(() -> new Attachment(DAODefaults.NO_ATTACHMENT_FOUND.getDefaultValue()));

            Blob fileData = attachmentFound.getFile();
            InputStream is = fileData.getBinaryStream();
            int fileLength = is.available();

            System.out.println("fileLength = " + fileLength);

            ServletContext context = getServletContext();

            // sets MIME type for the file download
            String mimeType = context.getMimeType(attachmentFound.getContentType());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }

            // set content properties and header attributes for the response
            response.setContentType(mimeType);
            response.setContentLength(fileLength);
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", attachmentFound.getAttachmentName() + ".jpg");
            response.setHeader(headerKey, headerValue);

            // writes the file to the client
            OutputStream outStream = response.getOutputStream();

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;

            while ((bytesRead = is.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

            is.close();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
