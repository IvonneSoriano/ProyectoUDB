/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

/**
 *
 * @author kiss_
 */
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import sv.edu.udb.util.Connect;

public class FileRequestDAO implements Dao<FileRequest> {

    private static final Logger logger = Logger.getLogger(FileRequestDAO.class);

    @Override
    public Optional<FileRequest> get(long id) {
        FileRequest fileRetrieved = null;
        try {
            Connect connection = new Connect();
            connection.setRs("SELECT * FROM attachments WHERE commentid = " + id + ";");
            ResultSet attachments = (ResultSet) connection.getRs();

            while (attachments.next()) {
                fileRetrieved = new FileRequest();
                fileRetrieved.setId(attachments.getInt("ID"));
                fileRetrieved.setFileName(attachments.getString("NOMBRE"));

                // convert blob into bytes array
                Blob blob = attachments.getBlob("CONTENIDO");
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
    public List<FileRequest> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(FileRequest t) {
        try {
            Connect connection = new Connect();

            try {
                String sql = "INSERT INTO `gestion_tickets`.`filerequests` "
                        + "( `NOMBRE`, `CONTENIDO`) "
                        + " VALUES ( '" + t.getFileName() + "' , " + t.getAttachmentFile() + ");";

                int result = connection.setQuery(sql);
                if (result <= 0) {
                    return true;
                } else {
                    return false;
                }

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
    public boolean update(FileRequest t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(FileRequest t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
