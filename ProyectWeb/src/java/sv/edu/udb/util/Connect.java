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

    //Metodo que cierra la conexion
    public void cerrarConexion() throws SQLException {
        conexion.close();
    }
}
