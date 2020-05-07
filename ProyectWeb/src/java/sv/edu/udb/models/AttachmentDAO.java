/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import sv.edu.udb.util.Connect;

/**
 *
 * @author Rick
 */
public class AttachmentDAO implements Dao<Attachment> {

    private static final Logger logger = Logger.getLogger(AttachmentDAO.class);

    @Override
    public Optional<Attachment> get(long id) {
        Attachment fileRetrieved = null;
        try {
            Connect connection = new Connect();
            connection.setRs("SELECT * FROM attachments WHERE commentid = " + id + ";");
            ResultSet attachments = (ResultSet) connection.getRs();

            while (attachments.next()) {
                fileRetrieved = new Attachment();
                fileRetrieved.setCommentId(attachments.getInt("COMMENTID"));
                fileRetrieved.setAttachmentName(attachments.getString("ATTACHMENTNAME"));
                fileRetrieved.setAttachmentSize(attachments.getFloat("ATTACHMENTSIZE"));
                fileRetrieved.setContentType(attachments.getString("CONTENTTYPE"));

                // convert blob into bytes array
                Blob blob = attachments.getBlob("ATTACHMENT");
                int blobLength = (int) blob.length();
                byte[] blobAsBytes = blob.getBytes(1, blobLength);

                //release the blob and free up memory. (since JDBC 4.0)
                blob.free();
                fileRetrieved.setAttachmentFile(blobAsBytes);
            }
        } catch (Exception e) {
            logger.error("Error processing ResultSet in get() method. Message: " + e.getMessage());
        }

        return Optional.ofNullable(fileRetrieved);
    }

    @Override
    public List<Attachment> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(Attachment t) {
        try {
            Connect connection = new Connect();

            try {
                String sql = "INSERT INTO `gestion_tickets`.`attachments` "
                        + "(`CommentId`, `AttachmentName`, `AttachmentSize`, "
                        + "`ContentType`, `Attachment`) "
                        + " VALUES ( ?  , ? , ? , ? , ?); ";

                connection.setPreparedStatement(sql, t);

                ResultSet rs = (ResultSet) connection.getRs();
                

            } catch (Exception e) {
                logger.error("Failed in prepared statement SQL! Message: " + e.getMessage());
            }

//            String sql = "INSERT INTO `gestion_tickets`.`attachments`\n"
//                    + "(`CommentId`,\n"
//                    + "`AttachmentName`,\n"
//                    + "`AttachmentSize`,\n"
//                    + "`ContentType`,\n"
//                    + "`Attachment`)\n"
//                    + "VALUES (" + t.getCommentId() + ", '"
//                    + t.getAttachmentName() + "' , " + t.getAttachmentSize()
//                    + ", '" + t.getContentType() + "', " + t.getAttachmentFile() + ");";
//            
//            System.out.println("SQL para el attachment: " + sql);
//            int result = connection.setQuery(sql);
//
//            if (result <= 0) {
//                logger.warn("INSERT query in Attachments table has failed.");
//                return false;
//            } else {
//                return true;
//            }
        } catch (Exception e) {
            logger.error("Error processing INSERT query in save method. Message: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean update(Attachment t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Attachment t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
