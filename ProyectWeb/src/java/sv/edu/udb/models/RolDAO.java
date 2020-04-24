/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import sv.edu.udb.util.Connect;

/**
 *
 * @author kiss_
 */
public class RolDAO implements Dao<Rol> {

    private static Logger logger = Logger.getLogger(RolDAO.class);

    @Override
    public Optional<Rol> get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rol> getAll() {

        Connect connection = null;
        List<Rol> rolesFound = new ArrayList<>();
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction in getAll() method. Message: " + ex.getMessage());
        }
        try {
            connection.setRs("SELECT * FROM ROLES WHERE NOT ROLID=5;");
            ResultSet roles = (ResultSet) connection.getRs();

            while (roles.next()) {
                Rol rol = new Rol();
                rol.setRolId(roles.getInt("ROLID"));
                rol.setRolName(roles.getString("ROLNAME"));
                rolesFound.add(rol);
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
        return rolesFound;
    }

    @Override
    public boolean save(Rol t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // THIS METHOD CAN BE DONE BY SUPERVISOR  
    @Override
    public boolean update(Rol t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Rol t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Rol getOne(int id) {
        Connect connection = null;
        Rol rol = new Rol();
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction in getAll() method. Message: " + ex.getMessage());
        }
        try {
            switch (id) {
                case 5:
                    connection.setRs("SELECT * FROM roles;");
                    break;

                case 1:
                    connection.setRs("SELECT * FROM `roles` WHERE roles.rolid = 2");
                    break;
                case 3:
                    connection.setRs("SELECT * FROM `roles` WHERE roles.rolid = 4");
                    break;
            }
            ResultSet rolFound = (ResultSet) connection.getRs();

            while (rolFound.next()) {
                rol.setRolId(rolFound.getInt("ROLID"));
                rol.setRolName(rolFound.getString("ROLNAME"));
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
        return rol;
    }

    public Rol getRol(int id) {
        Connect connection = null;
        Rol rol = new Rol();
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction in getRol() method. Message: " + ex.getMessage());
        }
        try {

            connection.setRs("SELECT * FROM `roles` WHERE roles.rolid =" + id + ";");
            ResultSet rolFound = (ResultSet) connection.getRs();

            while (rolFound.next()) {
                rol.setRolId(rolFound.getInt("ROLID"));
                rol.setRolName(rolFound.getString("ROLNAME"));
            }

        } catch (SQLException e) {
            logger.error("Error processing ResultSet in getRol() method. Message: " + e.getMessage());
        } finally {
            try {
                connection.cerrarConexion();
            } catch (SQLException ex) {
                logger.error("Error closing conecction in getRol() method. Message: " + ex.getMessage());
            }
        }
        return rol;
    }

    public int getId(String rol) {
        Connect connection = null;
        Rol roll = new Rol();
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction in getId() method. Message: " + ex.getMessage());
        }
        try {
            connection.setRs("SELECT ROLID FROM `roles` WHERE roles.rolname = '" + rol + "';");
            ResultSet rolFound = (ResultSet) connection.getRs();

            while (rolFound.next()) {
                roll.setRolId(rolFound.getInt("ROLID"));
            }

        } catch (SQLException e) {
            logger.error("Error processing ResultSet in getId() method. Message: " + e.getMessage());
        } finally {
            try {
                connection.cerrarConexion();
            } catch (SQLException ex) {
                logger.error("Error closing conecction in getId() method. Message: " + ex.getMessage());
            }

        }
        return roll.getRolId();
    }
}
