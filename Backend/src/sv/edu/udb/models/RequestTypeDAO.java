/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import sv.edu.udb.util.Connect;

/**
 *
 * @author Rick
 */
public class RequestTypeDAO implements Dao<RequestType> {

    private static Logger logger = Logger.getLogger(RequestTypeDAO.class);

    @Override
    public Optional<RequestType> get(long id) {
        RequestType requestFound = null;
        try {
            Connect connection = new Connect();
            connection.setRs("SELECT * FROM requesttypes where requesttypeid =" + id + ";");
            ResultSet requesttypes = (ResultSet) connection.getRs();

            while (requesttypes.next()) {
                requestFound = new RequestType();
                requestFound.setId(requesttypes.getInt("REQUESTTYPEID"));
                requestFound.setRequestTypeName(requesttypes.getString("REQUESTTYPENAME"));
            }
        } catch (Exception e) {
            logger.error("Error processing ResultSet. Message: " + e.getMessage());
        }
        return Optional.ofNullable(requestFound);
    }

    @Override
    public List<RequestType> getAll() {
        Connect connection = null;
        List<RequestType> requestTypesFound = new ArrayList<RequestType>();
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction in getAll() method. Message: " + ex.getMessage());
        }
        try {
            connection.setRs("SELECT * FROM requesttypes;");
            ResultSet rTypes = (ResultSet) connection.getRs();

            while (rTypes.next()) {
                RequestType rType = new RequestType();
                rType.setId(rTypes.getInt("REQUESTTYPEID"));
                rType.setRequestTypeName(rTypes.getString("REQUESTTYPENAME"));
                requestTypesFound.add(rType);
            }

        } catch (SQLException e) {
            logger.error("Error processing ResultSet in getAll() method. Message: " + e.getMessage());
        } finally {
            try {
                connection.cerrarConexion();
            } catch (SQLException ex) {
                logger.error("Error closing conecction in getAll() method. Message: " + ex.getMessage());
            }

        }
        return requestTypesFound;
    }

    public RequestType getRequestTypeName(int id) {
        RequestType foundName = null;
        try {
            Connect connection = null;
            String sql = "SELECT * FROM REQUESTTYPE WHERE REQUESTTYPEID=" + id + ";";
            connection.setRs("SELECT * FROM REQUESTTYPE WHERE REQUESTTYPEID=" + id + ";");
            System.out.println(sql);
            ResultSet reType = (ResultSet) connection.getRs();
            while (reType.next()) {
                foundName = new RequestType();
                foundName.setRequestTypeName("REQUESTTYPENAME");

            }
        } catch (Exception e) {
            logger.error("Error processing ResultSet in getRequestTypeName() method. Message: " + e.getMessage());
        }

        return foundName;
    }

    @Override
    public boolean save(RequestType t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(RequestType t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(RequestType t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Optional<RequestType> getRequestTypeByName(String name) {
        RequestType requestFound = null;
        try {
            Connect connection = new Connect();
            connection.setRs("SELECT * FROM requesttypes where requesttypename='" + name.trim() + "';");
            ResultSet requesttypes = (ResultSet) connection.getRs();

            while (requesttypes.next()) {
                requestFound = new RequestType();
                requestFound.setId(requesttypes.getInt("REQUESTTYPEID"));
                requestFound.setRequestTypeName(requesttypes.getString("REQUESTTYPENAME"));
            }
        } catch (Exception e) {
            logger.error("Error processing ResultSet. Message: " + e.getMessage());
        }
        return Optional.ofNullable(requestFound);
    }

}
