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
public class DeparmentDAO implements Dao<Deparment> {

    private static Logger logger = Logger.getLogger(DeparmentDAO.class);

    @Override
    public Optional<Deparment> get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Deparment> getAll() {

        Connect connection = null;
        List<Deparment> departmentsFound = new ArrayList<>();
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction. Message: " + ex.getMessage());
        }
        try {
            connection.setRs("SELECT * FROM DEPARTMENTS;");
            ResultSet departments = (ResultSet) connection.getRs();

            while (departments.next()) {
                Deparment department = new Deparment();
                department.setDepartmentId(departments.getInt("departmentid"));
                department.setDepartmentName(departments.getString("departmentname"));
                departmentsFound.add(department);
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
        return departmentsFound;
    }

    @Override
    public boolean save(Deparment t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // THIS METHOD CAN BE DONE BY SUPERVISOR  
    @Override
    public boolean update(Deparment t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Deparment t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Deparment getOne(int id) {
        Connect connection = null;
        Deparment department = new Deparment();
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction. Message: " + ex.getMessage());

        }
        try {
            connection.setRs("SELECT * FROM departments WHERE departments.departmentid=" + id + ";");
            ResultSet departmentFound = (ResultSet) connection.getRs();

            while (departmentFound.next()) {
                department.setDepartmentId(departmentFound.getInt("DEPARTMENTID"));
                department.setDepartmentName(departmentFound.getString("DEPARTMENTNAME"));
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
        return department;
    }

    public Deparment getOneByName(String nombre) {
        Connect connection = null;
        Deparment department = new Deparment();
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction in getAll() method. Message: " + ex.getMessage());
        }
        try {
            connection.setRs("SELECT * FROM departments WHERE departments.departmentname='" + nombre + "';");
            ResultSet departmentFound = (ResultSet) connection.getRs();

            while (departmentFound.next()) {
                department.setDepartmentId(departmentFound.getInt("DEPARTMENTID"));
                department.setDepartmentName(departmentFound.getString("DEPARTMENTNAME"));
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
        return department;
    }
}
