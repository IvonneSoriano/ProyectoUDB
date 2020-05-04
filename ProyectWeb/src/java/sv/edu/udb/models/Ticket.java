package sv.edu.udb.models;

import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import sv.edu.udb.util.DAODefaults;

/**
 *
 * @author Imer
 */
public class Ticket {

    private int id, requestID, programmerID, testerID, projectID;
    private String ticketStatus, internalCode;
    private Timestamp startDate, endDate;
    private float avance;
    private Request request;

    public Ticket() {
    }

    public void setIdTicket(int ticketId) {
        this.id = ticketId;
    }

    public int getIdTicket() {
        return id;
    }

    public void setRequestId(int requestTicket) {
        this.requestID = requestTicket;
    }

    public int getRequestId() {
        return requestID;
    }

    public void setIdProgrammer(int programmer) {
        this.programmerID = programmer;
    }

    public int getIdProgrammer() {
        return programmerID;
    }

    public void setIdTester(int idTester) {
        this.testerID = idTester;
    }

    public int getIdTester() {
        return testerID;
    }

    public void setTicketStatus(String status) {
        this.ticketStatus = status;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setAvance(float progress) {
        this.avance = progress;
    }

    public float getAvance() {
        return avance;
    }

    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }

    public String getInternalCode() {
        return internalCode;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public String getStartDDate() {
        String[] f = startDate.toString().split(" ");
        return f[0];
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public Request getRequest() {
        RequestDAO dao = new RequestDAO();
        Optional<Request> foundReq = dao.get(this.requestID);
        return foundReq.orElseGet(() -> new Request(DAODefaults.NO_REQUEST_FOUND.getDefaultValue()));
    }

    public void setRequest(Request request) {
        this.request = request;
    }
    
      public String getFormattedDate(String type) {
        Timestamp dateFromBD = type.equals("start") ? this.getStartDate() : this.getEndDate();
        return new SimpleDateFormat("MM/dd/yyyy").format(new java.sql.Date(dateFromBD.getTime()));
    }    
}
