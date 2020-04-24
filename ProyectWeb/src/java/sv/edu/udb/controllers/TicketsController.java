/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;

import java.util.List;
import sv.edu.udb.models.Ticket;
import sv.edu.udb.models.TicketDAO;

/**
 *
 * @author kiss_
 */
public class TicketsController {

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
