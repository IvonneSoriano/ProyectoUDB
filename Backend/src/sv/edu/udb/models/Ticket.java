package sv.edu.udb.models;

import java.util.*;
import java.sql.*;

/**
 *
 * @author Imer
 */
public class Ticket {

    private int id, request, programmer, tester, projectID;
    private String ticketStatus, internalCode;
    private Timestamp startDate, endDate;
    private float avance;

    public Ticket() {
    }

    public void setIdTicket(int ticketId) {
        this.id = ticketId;
    }

    public int getIdTicket() {
        return id;
    }

    public void setRequestId(int requestTicket) {
        this.request = requestTicket;
    }

    public int getRequestId() {
        return request;
    }

    public void setIdProgrammer(int programmer) {
        this.programmer = programmer;
    }

    public int getIdProgrammer() {
        return programmer;
    }

    public void setIdTester(int idTester) {
        this.tester = idTester;
    }

    public int getIdTester() {
        return tester;
    }

    public void setTicketStatus(String status) {
        this.ticketStatus = status;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setAvance(float status) {
        this.avance = status;
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
}
