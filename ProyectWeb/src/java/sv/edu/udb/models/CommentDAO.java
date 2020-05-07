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
import sv.edu.udb.util.DAODefaults;

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

    public List<Comment> getAllByRequest(int requestID) {

        Connect connection = null;
        List<Comment> employeesFound = new ArrayList<>();
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction in getAllByRequest() method. Message: " + ex.getMessage());
        }
        try {
            connection.setRs("SELECT * FROM COMMENTS WHERE REQUESTID=" + requestID + ";");
            ResultSet comment = (ResultSet) connection.getRs();

            while (comment.next()) {
                Comment c = new Comment();
                c.setCommentId(comment.getInt("COMMENTID"));
                c.setEmployeeId(comment.getInt("EMPLOYEEID"));
                c.setDepartmentId(comment.getInt("DEPARTMENTID"));
                c.setRequestId(comment.getInt("REQUESTID"));
                c.setCommentText(comment.getString("COMMENTTEXT"));
                c.setCommentDate(comment.getTimestamp("COMMENTDATE"));

                // Get associated attachment if present
                Attachment att = new Attachment();
                Optional<Attachment> attachment = this.getAssociatedAttachment(comment.getInt("COMMENTID"));
                att = attachment.orElseGet(() -> new Attachment(DAODefaults.NO_ATTACHMENT_FOUND.getDefaultValue()));
                c.setAssociatedAttachment(att);               
                
                employeesFound.add(c);
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

    public Optional<Comment> getLastCommentFromTicket(int id) {
        Comment c = null;
        try {
            Connect connection = new Connect();

            String sql = " SELECT * FROM `gestion_tickets`.`comments` "
                    + "WHERE REQUESTID = " + id
                    + " ORDER BY commentId DESC LIMIT 1;";

            connection.setRs(sql);

            ResultSet lastComment = (ResultSet) connection.getRs();

            while (lastComment.next()) {
                c = new Comment();
                c.setCommentId(lastComment.getInt("COMMENTID"));
                c.setEmployeeId(lastComment.getInt("EMPLOYEEID"));
                c.setDepartmentId(lastComment.getInt("DEPARTMENTID"));
                c.setRequestId(lastComment.getInt("REQUESTID"));
                c.setCommentText(lastComment.getString("COMMENTTEXT"));
                c.setCommentDate(lastComment.getTimestamp("COMMENTDATE"));
            }

        } catch (Exception e) {
            logger.error("Error processing SELECT query in getLastCommentFromTicket() method. Message: " + e.getMessage());
            return null;
        }

        return Optional.ofNullable(c);
    }

    public Optional<Attachment> getAssociatedAttachment(int commentId) {
        return new AttachmentDAO().get(commentId);
    }

}
