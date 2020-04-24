/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import org.apache.log4j.Logger;

/**
 *
 * @author Rick
 */
public class Session {

    private static Logger logger = Logger.getLogger(Session.class);

    public static boolean loggedIn = FALSE; // This determines whether session is open 
    public static int employeeType;
    public static int deparmentId;
    public static int employeeId;
    public static String username;
    public static String fullName;

    public static void logIn(Employee e) {
        Session.loggedIn = TRUE;
        Session.employeeId = e.getEmployeeId();
        Session.employeeType = e.getRolId();
        Session.deparmentId = e.getDepartmentId();
        Session.username = e.getUsername();
        Session.fullName = e.getEmployeeName().concat(" ").concat(e.getEmployeeLastname());
    }

    public static void logOut() {
        Session.loggedIn = FALSE;
        Session.employeeType = -1;
        Session.deparmentId = -1;
        Session.employeeId = -1;
        Session.username = new String();
        Session.fullName = new String();
    }
}
