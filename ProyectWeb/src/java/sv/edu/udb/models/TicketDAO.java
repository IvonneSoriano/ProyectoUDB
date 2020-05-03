/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.sql.*;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import sv.edu.udb.util.Connect;
import sv.edu.udb.models.Ticket;

/**
 *
 * @author Rick
 */
public class TicketDAO implements Dao<Ticket> {

    private static Logger logger = Logger.getLogger(TicketDAO.class);

    @Override
    public Optional<Ticket> get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ticket> getAll() {
        Connect connection = null;
        List<Ticket> ticketFound = new ArrayList<>();
        try {
            connection = new Connect();
            connection.setRs("SELECT * FROM TICKETS");
            ResultSet ticketSet = connection.getRs();
            while (ticketSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setIdTicket(ticketSet.getInt("TICKETID"));
                ticket.setRequestId(ticketSet.getInt("REQUESTID"));
                ticket.setProjectID(ticketSet.getInt("PROJECTID"));
                ticket.setIdProgrammer(ticketSet.getInt("ID_PROGRAMADOR"));
                ticket.setIdTester(ticketSet.getInt("ID_TESTER"));
                ticket.setTicketStatus(ticketSet.getString("TICKET_STATUS"));
                ticket.setInternalCode(ticketSet.getString("INTERNALCODE"));
                ticket.setStartDate(ticketSet.getTimestamp("STARTDATE"));
                ticket.setEndDate(ticketSet.getTimestamp("ENDDATE"));
                ticket.setAvance(ticketSet.getFloat("AVANCE"));
                ticketFound.add(ticket);
            }
        } catch (Exception e) {

            logger.error("Error processing INSERT query in getAll() method. Message: " + e.getMessage());
        }
        return ticketFound;
    }

    @Override
    public boolean save(Ticket t) {
        try {
            Connect connection = new Connect();

            String sql = "INSERT INTO `gestion_tickets`.`tickets` (`REQUESTID`, "
                    + "`PROJECTID`, `ID_PROGRAMADOR`, `ID_TESTER`, `TICKET_STATUS`,"
                    + "`INTERNALCODE`, `STARTDATE`, `ENDDATE`) VALUES("
                    + t.getRequestId() + ", " + t.getProjectID() + ", "
                    + t.getIdProgrammer() + ", " + t.getIdTester() + ", '"
                    + t.getTicketStatus() + "', '" + t.getInternalCode() + "', '"
                    + t.getStartDate() + "', '" + t.getEndDate() + "');";
            int result = connection.setQuery(sql);

            if (result <= 0) {
                logger.warn("INSERT to tickets table has failed");
                return false;
            } else {
                logger.info("INSERT to tickets table has successfully completed!");
                return true;
            }

        } catch (Exception e) {
            logger.error("Error processing INSERT query in save() method. Message: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Ticket t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Ticket t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Ticket getOne(int id) {
        Ticket foundTicket = null;
        try {
            Connect connection = new Connect();
            connection.setRs("SELECT * FROM TICKETS WHERE TICKETID=" + id + ";");
            ResultSet ticket = (ResultSet) connection.getRs();

            while (ticket.next()) {
                foundTicket = new Ticket();
                foundTicket.setIdTicket(ticket.getInt("TICKETID"));
                foundTicket.setRequestId(ticket.getInt("REQUESTID"));
                foundTicket.setProjectID(ticket.getInt("PROJECTID"));
                foundTicket.setIdProgrammer(ticket.getInt("ID_PROGRAMADOR"));
                foundTicket.setIdTester(ticket.getInt("ID_TESTER"));
                foundTicket.setTicketStatus(ticket.getString("TICKET_STATUS"));
                foundTicket.setInternalCode(ticket.getString("INTERNALCODE"));
                foundTicket.setStartDate(ticket.getTimestamp("STARTDATE"));
                foundTicket.setEndDate(ticket.getTimestamp("ENDDATE"));
                foundTicket.setAvance(ticket.getFloat("AVANCE"));
            }
        } catch (Exception e) {
            logger.error("Error processing ResultSet in getOne() method. Message: " + e.getMessage());
        }
        return foundTicket;
    }

    public boolean updateProgrammer(int idTicket, int idP) {
        try {
            Connect connection = new Connect();

            int result = connection.setQuery("UPDATE `gestion_tickets`.`TICKETS` SET "
                    + "`ID_PROGRAMADOR` = " + idP
                    + " WHERE `TICKETID` = " + idTicket + ";"
            );
            if (result <= 0) {
                logger.error("UPDATE to Tickets table has failed");
                return false;
            } else {
                logger.info("UPDATE to Tickets table has successfully completed!");
                return true;
            }
        } catch (Exception e) {
            logger.error("Error processing UPDATE query in updateProgrammer() method. Message: " + e.getMessage());
            return false;
        }
    }

    public boolean checkEmployee(int id, String p) {
        Connect connection = null;
        int result = 0;
        boolean status = false;
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction in checkEmployee() method. Message: " + ex.getMessage());
        }
        try {

            connection.setRs("SELECT COUNT(*) FROM `tickets` WHERE " + p + " = " + id + ";");
            ResultSet isExist = connection.getRs();
            while (isExist.next()) {
                result = isExist.getInt(1);
            }
            if (result > 0) {
                connection.setRs("SELECT COUNT(*) FROM `tickets` WHERE " + p + " = " + id + " AND TICKET_STATUS='EN_DESARROLLO';");
                ResultSet isExist2 = connection.getRs();
                while (isExist2.next()) {
                    result = isExist2.getInt(1);
                }
                if (result == 0) {
                    status = false;
                } else {
                    status = true;
                }
            } else {
                status = false;
            }

        } catch (SQLException e) {
            logger.error("Error processing ResultSet in checkEmployee() method. Message: " + e.getMessage());
        } finally {
            try {
                connection.cerrarConexion();
            } catch (SQLException ex) {
                logger.error("Error closing conecction in checkEmployee() method. Message: " + ex.getMessage());
            }

        }
        return status;
    }

    public boolean updateQA(int idTicket, int idP) {
        try {
            Connect connection = new Connect();

            int result = connection.setQuery("UPDATE `gestion_tickets`.`TICKETS` SET "
                    + "ID_TESTER = " + idP
                    + " WHERE TICKETID = " + idTicket + ";"
            );
            if (result <= 0) {
                logger.error("UPDATE query in Tickets table has failed");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            logger.error("Error processing UPDATE query in updateQA method. Message: " + e.getMessage());
            return false;
        }
    }

    public boolean updateStatus(int idTicket, String s) {
        try {
            Connect connection = new Connect();

            int result = connection.setQuery("UPDATE `gestion_tickets`.`TICKETS` SET "
                    + "TICKET_STATUS = '" + s
                    + "' WHERE TICKETID = " + idTicket + ";"
            );
            if (result <= 0) {
                logger.error("UPDATE query in Tickets table has failed");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            logger.error("Error processing UPDATE query in updateStatus method. Message: " + e.getMessage());
            return false;
        }
    }

    public boolean updateAvance(int idTicket, float a) {
        try {
            Connect connection = new Connect();
            float avance = 0;

            connection.setRs("SELECT AVANCE FROM `tickets` WHERE TICKETID = " + idTicket + ";");
            ResultSet isExist = connection.getRs();
            while (isExist.next()) {
                avance = isExist.getFloat(1);
            }
            avance = a + avance;
            int result = connection.setQuery("UPDATE `gestion_tickets`.`TICKETS` SET "
                    + "AVANCE = " + avance
                    + " WHERE TICKETID = " + idTicket + ";"
            );
            if (result <= 0) {
                logger.error("UPDATE query in Tickets table has failed");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            logger.error("Error processing UPDATE query in updateAvance() method. Message: " + e.getMessage());
            return false;
        }
    }

    public List<Ticket> verifyTesterNeeded(int id) {
        Connect connection = null;
        List<Ticket> ticketFound = new ArrayList<>();
        try {
            connection = new Connect();
            connection.setRs("SELECT `tickets`.`INTERNALCODE` FROM tickets "
                    + "INNER JOIN `requests` on `requests`.`REQUESTID` = `tickets`.`REQUESTID` "
                    + "WHERE `tickets`.`ID_TESTER`=0 "
                    + "AND `requests`.`DepartmentID` = " + id + ";");
            ResultSet ticketSet = connection.getRs();

            while (ticketSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setInternalCode(ticketSet.getString("tickets.INTERNALCODE"));
                ticketFound.add(ticket);
            }
        } catch (Exception e) {
            logger.error("Error processing SELECT query in verifyTesterNeeded() method. Message: " + e.getMessage());
        }
        return ticketFound;
    }

    public int verifyInternalCode(String c) {
        Connect connection = null;
        int result = 0;
        try {
            connection = new Connect();
            connection.setRs("SELECT COUNT(*) FROM tickets WHERE tickets.INTERNALCODE='" + c + "';");
            ResultSet ticketSet = connection.getRs();
            while (ticketSet.next()) {
                result = ticketSet.getInt(1);
            }
        } catch (Exception e) {
            logger.error("Error processing SELECT query in verifyInternalCode() method. Message: " + e.getMessage());
        }
        return result;
    }

    public float getAvance(int id) {
        Connect connection = null;
        float result = 0;
        try {
            connection = new Connect();
            connection.setRs("SELECT AVANCE FROM tickets WHERE tickets.TICKETID= " + id + ";");
            ResultSet ticketSet = connection.getRs();
            while (ticketSet.next()) {
                result = ticketSet.getInt(1);
            }
        } catch (Exception e) {
            logger.error("Error processing SELECT query in getAvance() method. Message: " + e.getMessage());
        }
        return result;
    }

    public List<Ticket> getAll(int dep) {
        Connect connection = null;
        List<Ticket> ticketFound = new ArrayList<>();
        try {
            connection = new Connect();
            connection.setRs("SELECT tickets.* FROM TICKETS "
                    + "INNER JOIN requests ON tickets.REQUESTID = requests.REQUESTID "
                    + "WHERE requests.DEPARTMENTID =" + dep + ";");
            ResultSet ticketSet = connection.getRs();

            while (ticketSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setIdTicket(ticketSet.getInt("TICKETID"));
                ticket.setRequestId(ticketSet.getInt("REQUESTID"));
                ticket.setProjectID(ticketSet.getInt("PROJECTID"));
                ticket.setIdProgrammer(ticketSet.getInt("ID_PROGRAMADOR"));
                ticket.setIdTester(ticketSet.getInt("ID_TESTER"));
                ticket.setTicketStatus(ticketSet.getString("TICKET_STATUS"));
                ticket.setInternalCode(ticketSet.getString("INTERNALCODE"));
                ticket.setStartDate(ticketSet.getTimestamp("STARTDATE"));
                ticket.setEndDate(ticketSet.getTimestamp("ENDDATE"));                
                ticket.setAvance(ticketSet.getFloat("AVANCE"));                
                ticketFound.add(ticket);
            }
        } catch (Exception e) {
            logger.error("Error processing SELECT query in getAll() method. Message: " + e.getMessage());
        }
        return ticketFound;
    }
}
