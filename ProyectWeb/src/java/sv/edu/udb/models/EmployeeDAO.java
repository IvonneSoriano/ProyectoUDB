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
 * @author Rick
 */
public class EmployeeDAO implements Dao<Employee> {

    private static Logger logger = Logger.getLogger(EmployeeDAO.class);

    @Override
    public Optional<Employee> get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Employee> getAll() {

        Connect connection = null;
        List<Employee> employeesFound = new ArrayList<>();
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction in getAll() method. Message: " + ex.getMessage());
        }
        try {
            connection.setRs("SELECT * FROM EMPLOYEES;");
            ResultSet employees = (ResultSet) connection.getRs();

            while (employees.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(employees.getInt("EmployeeID"));
                employee.setRolId(employees.getInt("ROLID"));
                employee.setDepartmentId(employees.getInt("DEPARTMENTID"));
                employee.setEmployeeName(employees.getString("EMPLOYEENAME"));
                employee.setEmployeeLastname(employees.getString("EMPLOYEELASTNAME"));
                employee.setUsername(employees.getString("USERNAME"));
                employee.setPassword(employees.getString("PASSWORD"));
                employeesFound.add(employee);
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
        return employeesFound;
    }

    @Override
    public boolean save(Employee t) {
        try {
            Connect connection = new Connect();

            int result = connection.setQuery("INSERT INTO `gestion_tickets`.`employees` "
                    + "(`ROLID`, `DEPARTMENTID`, `EMPLOYEENAME`, `EMPLOYEELASTNAME`, `USERNAME`, `PASSWORD`) "
                    + "VALUES ('" + t.getRolId() + "', '" + t.getDepartmentId()
                    + "', '" + t.getEmployeeName() + "', '"
                    + t.getEmployeeLastname() + "', '"
                    + t.getUsername() + "', SHA2('" + t.getPassword() + "',256));");

            if (result <= 0) {
                logger.warn("INSERT query in Employees table has failed");
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            logger.error("Error processing INSERT query in save method. Message: " + e.getMessage());
            return false;
        }
    }

    // THIS METHOD CAN BE DONE BY SUPERVISOR  
    public boolean update(Employee t, String[] params) {
        try {
            Connect connection = new Connect();

            int result = connection.setQuery("UPDATE `gestion_tickets`.`employees` SET "
                    + "`ROLID` = '" + t.getRolId()
                    + "', `DEPARTMENTID` = '" + t.getDepartmentId()
                    + "', `EMPLOYEENAME` = '" + t.getEmployeeName()
                    + "', `EMPLOYEELASTNAME` = '" + t.getEmployeeLastname()
                    + "', `USERNAME` = '" + t.getUsername()
                    + "', `PASSWORD` = SHA2('" + t.getPassword()
                    + " ',256) WHERE `EmployeeID` = " + t.getEmployeeId() + ";"
            );
            if (result <= 0) {
                logger.warn("UPDATE query in Employees table has failed");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            logger.error("Error processing UPDATE query. Message: " + e.getMessage());
            return false;
        }
    }

    public boolean updateUser(Employee t, String pass) {
        try {
            Connect connection = new Connect();

            int result = connection.setQuery("UPDATE `gestion_tickets`.`employees` SET "
                    + "`ROLID` = " + t.getRolId()
                    + ", `DEPARTMENTID` = " + t.getDepartmentId()
                    + ", `EMPLOYEENAME` = '" + t.getEmployeeName()
                    + "', `EMPLOYEELASTNAME` = '" + t.getEmployeeLastname()
                    + "', `USERNAME` = '" + t.getUsername()
                    + "', `PASSWORD` = SHA2('" + t.getPassword()
                    + "',256) WHERE `EmployeeID` = " + t.getEmployeeId() + " and `PASSWORD` = SHA2('" + pass + "',256);"
            );
            if (result <= 0) {
                logger.error("UPDATE query in Employees table has failed");
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
    public boolean delete(Employee t) {
        try {
            Connect connection = new Connect();

            int result = connection.setQuery("DELETE FROM `gestion_tickets`.`employees` WHERE `EmployeeID` = "
                    + t.getEmployeeId() + ";");

            if (result <= 0) {
                logger.error("DELETE query in Employees table has failed");
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            logger.error("Error processing DELETE query. Message: " + e.getMessage());
            return false;
        }
    }

    public Optional<Employee> getEmployeeByUsername(String username, char[] password) {
        Employee foundEmployee = null;
        try {
            Connect connection = new Connect();
            String pass = new String(password);
            connection.setRs("SELECT * FROM EMPLOYEES WHERE username='" + username + "' and PASSWORD= SHA2('" + pass + "',256);");
            ResultSet employees = (ResultSet) connection.getRs();
            while (employees.next()) {
                foundEmployee = new Employee();
                foundEmployee.setEmployeeId(employees.getInt("EmployeeID"));
                foundEmployee.setRolId(employees.getInt("ROLID"));
                foundEmployee.setDepartmentId(employees.getInt("DEPARTMENTID"));
                foundEmployee.setEmployeeName(employees.getString("EMPLOYEENAME"));
                foundEmployee.setEmployeeLastname(employees.getString("EMPLOYEELASTNAME"));
                foundEmployee.setUsername(employees.getString("USERNAME"));
                foundEmployee.setPassword(employees.getString("PASSWORD"));

            }
        } catch (Exception e) {
            logger.error("Error processing ResultSet in getEmployeeByUsername() method. Message: " + e.getMessage());
        }
        return Optional.ofNullable(foundEmployee);
    }

    public Employee getEmployeeByFullName(String name, String last) {
        Employee foundEmployee = null;
        try {
            Connect connection = new Connect();
            connection.setRs("SELECT * FROM EMPLOYEES WHERE 	EMPLOYEENAME='" + name + "' and EMPLOYEELASTNAME ='" + last + "' ;");
            ResultSet employees = (ResultSet) connection.getRs();

            while (employees.next()) {
                foundEmployee = new Employee();
                foundEmployee.setEmployeeId(employees.getInt("EmployeeID"));
                foundEmployee.setRolId(employees.getInt("ROLID"));
                foundEmployee.setDepartmentId(employees.getInt("DEPARMENTID"));
                foundEmployee.setEmployeeName(employees.getString("EMPLOYEENAME"));
                foundEmployee.setEmployeeLastname(employees.getString("EMPLOYEELASTNAME"));
                foundEmployee.setUsername(employees.getString("USERNAME"));
                foundEmployee.setPassword(employees.getString("PASSWORD"));

            }
        } catch (Exception e) {
            logger.error("Error processing ResultSet in getEmployeeByFullname() method. Message: " + e.getMessage());
        }
        return foundEmployee;
    }

    public Employee getEmployeeById(int id) {
        Employee foundEmployee = new Employee("Sin", "Asignar");
        try {
            Connect connection = new Connect();
            connection.setRs("SELECT * FROM EMPLOYEES WHERE EMPLOYEEID=" + id + ";");
            ResultSet employees = (ResultSet) connection.getRs();

            while (employees.next()) {
                foundEmployee = new Employee();
                foundEmployee.setEmployeeId(employees.getInt("EmployeeID"));
                foundEmployee.setRolId(employees.getInt("ROLID"));
                foundEmployee.setDepartmentId(employees.getInt("DEPARTMENTID"));
                foundEmployee.setEmployeeName(employees.getString("EMPLOYEENAME"));
                foundEmployee.setEmployeeLastname(employees.getString("EMPLOYEELASTNAME"));
                foundEmployee.setUsername(employees.getString("USERNAME"));
                foundEmployee.setPassword(employees.getString("PASSWORD"));
            }
        } catch (Exception e) {
            logger.error("Error processing ResultSet in getEmployeeById() method. Message: " + e.getMessage());
        }
        return foundEmployee;
    }

    public List<Employee> getAllByRol(int rol) {

        Connect connection = null;
        List<Employee> employeesFound = new ArrayList<>();
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction in getAllByRol() method. Message: " + ex.getMessage());
        }
        try {
            connection.setRs("SELECT * FROM EMPLOYEES WHERE ROLID =" + rol + " ;");
            ResultSet employees = (ResultSet) connection.getRs();

            while (employees.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(employees.getInt("EmployeeID"));
                employee.setRolId(employees.getInt("ROLID"));
                employee.setDepartmentId(employees.getInt("DEPARTMENTID"));
                employee.setEmployeeName(employees.getString("EMPLOYEENAME"));
                employee.setEmployeeLastname(employees.getString("EMPLOYEELASTNAME"));
                employee.setUsername(employees.getString("USERNAME"));
                employee.setPassword(employees.getString("PASSWORD"));
                employeesFound.add(employee);
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
        return employeesFound;
    }

    public List<Employee> getAllByRolAndDepto(int rol, int depto) {

        Connect connection = null;
        List<Employee> employeesFound = new ArrayList<>();
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction in getAllByRolAndDepto() method. Message: " + ex.getMessage());
        }
        try {
            connection.setRs("SELECT * FROM EMPLOYEES WHERE ROLID =" + rol + " AND DEPARTMENTID = " + depto + ";");
            ResultSet employees = (ResultSet) connection.getRs();

            while (employees.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(employees.getInt("EmployeeID"));
                employee.setRolId(employees.getInt("ROLID"));
                employee.setDepartmentId(employees.getInt("DEPARTMENTID"));
                employee.setEmployeeName(employees.getString("EMPLOYEENAME"));
                employee.setEmployeeLastname(employees.getString("EMPLOYEELASTNAME"));
                employee.setUsername(employees.getString("USERNAME"));
                employee.setPassword(employees.getString("PASSWORD"));
                employeesFound.add(employee);
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
        return employeesFound;
    }

    public List<Employee> getAllEmployees() {

        Connect connection = null;
        List<Employee> employeesFound = new ArrayList<>();
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction in getAllEmployees() method. Message: " + ex.getMessage());
        }
        try {
            connection.setRs("SELECT * FROM EMPLOYEES WHERE ROLID = 2 OR ROLID=4;");
            ResultSet employees = (ResultSet) connection.getRs();

            while (employees.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(employees.getInt("EmployeeID"));
                employee.setRolId(employees.getInt("ROLID"));
                employee.setDepartmentId(employees.getInt("DEPARTMENTID"));
                employee.setEmployeeName(employees.getString("EMPLOYEENAME"));
                employee.setEmployeeLastname(employees.getString("EMPLOYEELASTNAME"));
                employee.setUsername(employees.getString("USERNAME"));
                employee.setPassword(employees.getString("PASSWORD"));
                employeesFound.add(employee);
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
        return employeesFound;
    }

    public List<Employee> getAllSupervisors() {

        Connect connection = null;
        List<Employee> employeesFound = new ArrayList<>();
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction in getAllSupervisors() method. Message: " + ex.getMessage());
        }
        try {
            connection.setRs("SELECT * FROM EMPLOYEES WHERE ROLID = 1 OR ROLID=3;");
            ResultSet employees = (ResultSet) connection.getRs();

            while (employees.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(employees.getInt("EmployeeID"));
                employee.setRolId(employees.getInt("ROLID"));
                employee.setDepartmentId(employees.getInt("DEPARTMENTID"));
                employee.setEmployeeName(employees.getString("EMPLOYEENAME"));
                employee.setEmployeeLastname(employees.getString("EMPLOYEELASTNAME"));
                employee.setUsername(employees.getString("USERNAME"));
                employee.setPassword(employees.getString("PASSWORD"));
                employeesFound.add(employee);
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
        return employeesFound;
    }

    public List<Employee> getAll(int rol, int depto) {

        Connect connection = null;
        List<Employee> employeesFound = new ArrayList<>();
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction in getAll(int rol, int depto) method. Message: " + ex.getMessage());
        }
        try {
            connection.setRs("SELECT employees.EmployeeID, "
                    + "employees.EMPLOYEENAME,"
                    + "employees.EMPLOYEELASTNAME "
                    + "FROM `employees` INNER JOIN tickets "
                    + "ON employees.EmployeeID = tickets.ID_PROGRAMADOR  "
                    + "WHERE employees.ROLID = "
                    + rol + " AND employees.DEPARMENTID = " + depto
                    + " AND NOT tickets.TICKET_STATUS = \"DESARROLLO\" ");
            ResultSet employees = (ResultSet) connection.getRs();

            while (employees.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(employees.getInt("EmployeeID"));
                employee.setRolId(employees.getInt("ROLID"));
                employee.setDepartmentId(employees.getInt("DEPARMENTID"));
                employee.setEmployeeName(employees.getString("EMPLOYEENAME"));
                employee.setEmployeeLastname(employees.getString("EMPLOYEELASTNAME"));
                employee.setUsername(employees.getString("USERNAME"));
                employee.setPassword(employees.getString("PASSWORD"));
                employeesFound.add(employee);
            }

        } catch (SQLException e) {
            logger.error("Error processing ResultSet in getAll(int rol, int depto) method. Message: " + e.getMessage());
        } finally {
            try {
                connection.cerrarConexion();
            } catch (SQLException ex) {
                logger.error("Error closing conecction in getAll(int rol, int depto) method. Message: " + ex.getMessage());
            }

        }
        return employeesFound;
    }

}
