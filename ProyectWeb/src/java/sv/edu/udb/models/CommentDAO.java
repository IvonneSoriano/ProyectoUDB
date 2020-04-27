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
public class CommentDAO implements Dao<Comment> {

    private static final Logger logger = Logger.getLogger(CommentDAO.class);

    @Override
    public Optional<Comment> get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comment> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Comment> getAllid(int id) {

        Connect connection = null;
        List<Comment> employeesFound = new ArrayList<>();
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction in getAll() method. Message: " + ex.getMessage());
        }
        try {
            connection.setRs("SELECT * FROM COMMENTS WHERE REQUESTID=" + id + ";");
            ResultSet employees = (ResultSet) connection.getRs();

            while (employees.next()) {
                Comment employee = new Comment();
                employee.setCommentId(employees.getInt("COMMENTID"));
                employee.setEmployeeId(employees.getInt("EMPLOYEEID"));
                employee.setDepartmentId(employees.getInt("DEPARTMENTID"));
                employee.setRequestId(employees.getInt("REQUESTID"));
                employee.setCommentText(employees.getString("COMMENTTEXT"));
                employee.setCommentDate(employees.getTimestamp("COMMENTDATE"));
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
    public boolean save(Comment c) {
        try {
            Connect connection = new Connect();

            String sql = "INSERT INTO `gestion_tickets`.`comments` "
                    + "(`EMPLOYEEID`, `DEPARTMENTID`, `REQUESTID`, `COMMENTTEXT`, "
                    + "`COMMENTDATE`) VALUES (" + c.getEmployeeId() + ", "
                    + c.getDepartmentId() + " , " + c.getRequestId()
                    + ", '" + c.getCommentText() + "', '" + c.getCommentDate() + "');";

            int result = connection.setQuery(sql);

            if (result <= 0) {
                logger.warn("INSERT query in Comments table has failed.");
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            logger.error("Error processing INSERT query in save method. Message: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Comment t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Comment t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
