/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import sv.edu.udb.util.Connect;

/**
 *
 * @author Rick
 */
public class RequestDAO implements Dao<Request> {

    private static Logger logger = Logger.getLogger(RequestDAO.class);

    @Override
    public Optional<Request> get(long id) {
        Request lastRequest = null;
        try {
            Connect connection = new Connect();
            connection.setRs("SELECT * FROM requests WHERE requestid = " + id + ";");
            ResultSet requests = (ResultSet) connection.getRs();

            while (requests.next()) {
                lastRequest = new Request();
                lastRequest.setId(requests.getInt("REQUESTID"));
                lastRequest.setIdTypeRequest(requests.getInt("REQUESTTYPEID"));
                lastRequest.setRequestDate(requests.getTimestamp("REQUESTDATE"));
                lastRequest.setRequestDescription(requests.getString("REQUESTDESCRIPTION"));
                lastRequest.setRequestStatus(requests.getString("REQUESTSTATUS"));
                lastRequest.setProjectId(requests.getInt("PROJECTID"));
                lastRequest.setDepartmentId(requests.getInt("DEPARTMENTID"));
                lastRequest.setAttachmentFile(requests.getBytes("FILE"));
            }
        } catch (Exception e) {
            logger.error("Error processing ResultSet in get() method. Message: " + e.getMessage());
        }
        return Optional.ofNullable(lastRequest);
    }

    @Override
    public List<Request> getAll() {
        Connect connection = null;
        List<Request> requestFound = new ArrayList<>();
        try {
            connection = new Connect();
            connection.setRs("SELECT * FROM REQUESTS");
            ResultSet requestSet = connection.getRs();

            while (requestSet.next()) {
                Request request = new Request();
                request.setId(requestSet.getInt("REQUESTID"));
                request.setIdTypeRequest(requestSet.getInt("REQUESTTYPEID"));
                request.setRequestDate(requestSet.getTimestamp("REQUESTDATE"));
                request.setRequestDescription(requestSet.getString("REQUESTDESCRIPTION"));
                request.setRequestStatus(requestSet.getString("REQUESTSTATUS"));
                request.setProjectId(requestSet.getInt("PROJECTID"));
                request.setDepartmentId(requestSet.getInt("DEPARTMENTID"));
                request.setAttachmentFile(requestSet.getBytes("FILE"));
                requestFound.add(request);
            }
        } catch (SQLException e) {
            logger.error("Error processing SELECT query in save method. Message: " + e.getMessage());
        } finally {
            try {
                connection.cerrarConexion();
            } catch (SQLException ex) {
                logger.error("Error closing conecction in getAll() method. Message: " + ex.getMessage());
            }

        }
        return requestFound;
    }

    @Override
    public boolean save(Request t) {
        try {
            Connect connection = new Connect();

            String sql = "INSERT INTO `gestion_tickets`.`requests`"
                    + " (`REQUESTTYPEID`, `REQUESTDATE`, `REQUESTDESCRIPTION`,"
                    + " `REQUESTSTATUS`, `PROJECTID`, `DEPARTMENTID`, `FILE`) VALUES (?, ? , ?, ?, ?, ?, ?);";
            
            
           int result = connection.setPreparedStatementFile(sql, t);

            if (result <= 0) {
                logger.warn("INSERT query in Requests table has failed");
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            logger.error("Error processing INSERT query. Message: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Request r, String[] params) {
        try {
            Connect connection = new Connect();

            int result = connection.setQuery("UPDATE `gestion_tickets`.`requests` SET "
                    + "`REQUESTTYPEID` = " + r.getIdTypeRequest()
                    + ", `REQUESTDATE` = '" + r.getRequestDate()
                    + "', `REQUESTDESCRIPTION` = '" + r.getRequestDescription()
                    + "', `REQUESTSTATUS` = '" + r.getRequestStatus()
                    + "', `PROJECTID` = " + r.getProjectId()
                    + ", `DEPARTMENTID` = " + r.getDepartmentId()
                    + " WHERE `REQUESTID` = " + r.getId() + ";"
            );
            if (result <= 0) {
                logger.error("UPDATE query in Requests table has failed");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            logger.error("Error processing UPDATE query. Message: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Request t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Optional<Request> getLastReequestId() {
        Request lastRequest = null;
        try {
            Connect connection = new Connect();
            connection.setRs("SELECT * FROM requests ORDER BY requestid DESC LIMIT 1;");
            ResultSet requests = (ResultSet) connection.getRs();

            while (requests.next()) {
                lastRequest = new Request();
                lastRequest.setId(requests.getInt("REQUESTID"));
                lastRequest.setIdTypeRequest(requests.getInt("REQUESTTYPEID"));
                lastRequest.setRequestDate(requests.getTimestamp("REQUESTDATE"));
                lastRequest.setRequestDescription(requests.getString("REQUESTDESCRIPTION"));
                lastRequest.setRequestStatus(requests.getString("REQUESTSTATUS"));
                lastRequest.setProjectId(requests.getInt("PROJECTID"));
                lastRequest.setDepartmentId(requests.getInt("DEPARTMENTID"));
            }
        } catch (Exception e) {
            logger.error("Error processing ResultSet. Message: " + e.getMessage());
        }
        return Optional.ofNullable(lastRequest);
    }

    public List<Request> getRequestsByStatusAndDep(String status, int id) {
        Connect connection = null;
        List<Request> requestsFound = new ArrayList<>();
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction in getRequestsByStatusAndDep() method. Message: " + ex.getMessage());
        }
        try {
            connection.setRs("SELECT * FROM requests WHERE requeststatus = '" + status + "' and departmentid = " + id + ";");
            ResultSet rs = (ResultSet) connection.getRs();

            while (rs.next()) {
                Request request = new Request();
                request.setId(rs.getInt("REQUESTID"));
                request.setIdTypeRequest(rs.getInt("REQUESTTYPEID"));
                request.setRequestDate(rs.getTimestamp("REQUESTDATE"));
                request.setRequestDescription(rs.getString("REQUESTDESCRIPTION"));
                request.setRequestStatus(rs.getString("REQUESTSTATUS"));
                request.setProjectId(rs.getInt("PROJECTID"));
                request.setDepartmentId(rs.getInt("DEPARTMENTID"));
                requestsFound.add(request);
            }

        } catch (SQLException e) {
            logger.error("Error processing ResultSet. Message: " + e.getMessage());
        } finally {
            try {
                connection.cerrarConexion();
            } catch (SQLException ex) {
                logger.error("Error closing conecction. Message: " + ex.getMessage());
            }

        }
        return requestsFound;
    }

    public List<Request> getRequestsByDepartmentId(int id) {
        Connect connection = null;
        List<Request> requestsFound = new ArrayList<>();
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating connection. Message: " + ex.getMessage());
        }
        try {
            connection.setRs("SELECT * FROM requests WHERE departmentid = " + id + ";");
            ResultSet rs = (ResultSet) connection.getRs();

            while (rs.next()) {
                Request request = new Request();
                request.setId(rs.getInt("REQUESTID"));
                request.setIdTypeRequest(rs.getInt("REQUESTTYPEID"));
                request.setRequestDate(rs.getTimestamp("REQUESTDATE"));
                request.setRequestDescription(rs.getString("REQUESTDESCRIPTION"));
                request.setRequestStatus(rs.getString("REQUESTSTATUS"));
                request.setProjectId(rs.getInt("PROJECTID"));
                request.setDepartmentId(rs.getInt("DEPARTMENTID"));
                requestsFound.add(request);
            }

        } catch (SQLException e) {
            logger.error("Error processing ResultSet. Message: " + e.getMessage());
        } finally {
            try {
                connection.cerrarConexion();
            } catch (SQLException ex) {
                logger.error("Error closing conecction. Message: " + ex.getMessage());
            }

        }
        return requestsFound;
    }

    public boolean updateStatus(Request r) {
        try {
            Connect connection = new Connect();

            int result = connection.setQuery("UPDATE `gestion_tickets`.`requests` SET "
                    + "`REQUESTSTATUS` = '" + r.getRequestStatus()
                    + "' WHERE `REQUESTID` = " + r.getId() + ";"
            );
            if (result <= 0) {
                logger.error("UPDATE status query in Requests table has failed");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            logger.error("Error processing UPDATE query. Message: " + e.getMessage());
            return false;
        }
    }

    public Request getOneById(int id) {
        Request request = null;
        try {
            Connect connection = new Connect();
            connection.setRs("SELECT * FROM requests WHERE REQUESTID = " + id + ";");
            ResultSet requests = (ResultSet) connection.getRs();

            while (requests.next()) {
                request = new Request();
                request.setId(requests.getInt("REQUESTID"));
                request.setIdTypeRequest(requests.getInt("REQUESTTYPEID"));
                request.setRequestDate(requests.getTimestamp("REQUESTDATE"));
                request.setRequestDescription(requests.getString("REQUESTDESCRIPTION"));
                request.setRequestStatus(requests.getString("REQUESTSTATUS"));
            }
        } catch (Exception e) {
            logger.error("Error processing ResultSet in getOneById() method. Message: " + e.getMessage());
        }
        return request;
    }
}
