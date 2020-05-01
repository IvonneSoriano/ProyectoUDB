/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sv.edu.udb.models.Ticket;
import sv.edu.udb.models.TicketDAO;

/**
 *
 * @author Rick
 */
@WebServlet(name = "TicketController", urlPatterns = {"/tickets.do"})
public class TicketController extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TicketController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TicketController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
   
   public boolean updateAvance(int idTicket, float a){
       TicketDAO dao = new TicketDAO();
        return dao.updateAvance(idTicket,a);
   }
   
   public float getAvance(int id){
        TicketDAO dao = new TicketDAO();
        return dao.getAvance(id);
   }
}
