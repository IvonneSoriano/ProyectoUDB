/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.util;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author Imer Palma
 */
public class Connect {

    private Connection conexion = null;
    private Statement s = null;
    private ResultSet rs = null;
    private String query = "";

    public Connect() throws SQLException {
        try {
            //obtenemos el driver de para mysql
            Class.forName("com.mysql.jdbc.Driver");
            // Se obtiene una conexión con la base de datos.
            Properties prop = new Properties();
            FileInputStream ip = new FileInputStream("nbproject/config.properties");
            prop.load(ip);

            conexion = DriverManager.getConnection(prop.getProperty("db_url"), prop.getProperty("db_user"), prop.getProperty("db_pswd"));

            // Permite ejecutar sentencias SQL sin parámetros
            s = conexion.createStatement();
            //  System.err.println("exito");
        } catch (Exception e1) {
            System.out.println("ERROR:No encuentro el driver de la BD:" + e1.getMessage());
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
            System.out.println("ERROR:Fallo en SQL: " + e2.getMessage() + " Sql: " + consulta);
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
