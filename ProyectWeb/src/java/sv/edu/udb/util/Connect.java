/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import org.apache.log4j.Logger;
import sv.edu.udb.models.Request;
import sv.edu.udb.models.Attachment;

/**
 *
 * @author Imer Palma
 */
public class Connect {

    private Connection conexion = null;
    private Statement s = null;
    private ResultSet rs = null;
    private String query = "";

    private static final Logger logger = Logger.getLogger(Connect.class);

    public Connect() throws SQLException {
        try {
            //obtenemos el driver de para mysql
            Class.forName("com.mysql.jdbc.Driver");

            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("config.properties");
            //FileInputStream ip = new FileInputStream("nbproject/config.properties");

            Properties prop = new Properties();
            prop.load(input);

            // Se obtiene una conexión con la base de datos.
            conexion = DriverManager.getConnection(prop.getProperty("db_url"), prop.getProperty("db_user"), prop.getProperty("db_pswd"));

            // Permite ejecutar sentencias SQL sin parámetros
            s = conexion.createStatement();
        } catch (Exception e1) {
            logger.error("ERROR:No encuentro el driver de la BD: " + e1.getMessage());
        }
    }

    public int setPreparedStatementFile(String sql, Request t) {
        int r = 0;
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, t.getIdTypeRequest());
            ps.setTimestamp(2, t.getRequestDate());
            ps.setString(3, t.getRequestDescription());
            ps.setString(4, t.getRequestStatus());
            ps.setInt(5, t.getProjectId());
            ps.setInt(6, t.getDepartmentId());
            ps.setInt(5, t.getProjectId());
            ps.setBlob(7, t.getFileIS());
            

          r=  ps.executeUpdate();
        } catch (Exception e) {
            logger.error("Failed in prepared statement SQL! Message: " + e.getMessage());
        }
        return r;
    }
    public ResultSet getRs() {
        return rs;
    }

    //Metodo que permite fijar la tabla resultado de la pregunta
    //SQL realizada
    public void setRs(String consulta) {
        try {
            this.rs = s.executeQuery(consulta);
        } catch (SQLException e2) {
            logger.error("ERROR:No encuentro el driver de la BD: " + e2.getMessage() + " Sql: " + consulta);
        }
    }

    //Metodo que recibe un sql como parametro que sea un update,insert.delete
    public int setQuery(String query) throws SQLException {
        return this.s.executeUpdate(query);
    }

    public void setPreparedStatement(String sql, Attachment t) {
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, (int) t.getCommentId());
            ps.setString(2, t.getAttachmentName());
            ps.setFloat(3, t.getAttachmentSize());
            ps.setString(4, t.getContentType());
            ps.setBlob(5, t.getFileIS());

            ps.executeUpdate();
        } catch (Exception e) {
            logger.error("Failed in prepared statement SQL! Message: " + e.getMessage());
        }
    }

    //Metodo que cierra la conexion
    public void cerrarConexion() throws SQLException {
        conexion.close();
    }
  public Connection getConexion() throws  SQLException{
        return conexion;
     }
}

