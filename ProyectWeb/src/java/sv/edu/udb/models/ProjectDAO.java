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
import java.sql.Timestamp;

/**
 *
 * @author Rick
 */
public class ProjectDAO implements Dao<Project> {

    private static Logger logger = Logger.getLogger(ProjectDAO.class);

    @Override
    public Optional<Project> get(long id) {
        Project pr = null;
        try {
            Connect connection = new Connect();
            connection.setRs("SELECT * FROM projects WHERE projectid = " + id + ";");
            ResultSet rs = (ResultSet) connection.getRs();

            while (rs.next()) {
                pr = new Project();
                pr.setProjectsId(rs.getInt("PROJECTID"));
                pr.setProjectName(rs.getString("PROJECTNAME"));
                pr.setProjectDescription(rs.getString("PROJECTDESCRIPTION"));
                pr.setDepartmentId(rs.getInt("DEPARTMENTID"));
                pr.setCreationDate(rs.getTimestamp("CREATIONDATE"));
            }
        } catch (Exception e) {
            logger.error("Error processing ResultSet in gets() method. Message: " + e.getMessage());
        }
        return Optional.ofNullable(pr);
    }

    @Override
    public List<Project> getAll() {

        Connect connection = null;
        List<Project> projectFound = new ArrayList<>();
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction in getAll() method. Message: " + ex.getMessage());
        }
        try {
            connection.setRs("SELECT * FROM PROJECTS");
            ResultSet projectSet = (ResultSet) connection.getRs();

            while (projectSet.next()) {
                Project project = new Project();
                project.setProjectsId(projectSet.getInt("PROJECTID"));

                project.setDepartmentId(projectSet.getInt("DEPARTMENTID"));
                project.setProjectName(projectSet.getString("PROJECTNAME"));
                project.setProjectDescription(projectSet.getString("PROJECTDESCRIPTION"));
                project.setCreationDate(projectSet.getTimestamp("CREATIONDATE"));

                projectFound.add(project);

            }

        } catch (SQLException e) {
            logger.error("Error processing ResultSet in getAll() method. Message: " + e.getMessage());
        } finally {
            try {
                connection.cerrarConexion();
            } catch (SQLException ex) {
                logger.error("Error closing conecction in getAll() method. Message: " + ex.getMessage());
            }

        }
        return projectFound;

    }

    @Override
    public boolean save(Project t) {

        try {
            Connect connection = new Connect();
            int result = connection.setQuery("INSERT INTO `gestion_tickets`.`projects` "
                    + "( `DEPARTMENTID`, `PROJECTNAME`, `PROJECTDESCRIPTION`, `CREATIONDATE`) "
                    + "VALUES ( '" + t.getDepartmentId()
                    + "', '" + t.getProjectName() + "', '"
                    + t.getProjectDescription() + "', '"
                    + t.getCreationDate() + "');");

            if (result <= 0) {
                logger.warn("INSERT query in Projects table has failed");
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
    public boolean update(Project t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Project t) {
        try {
            Connect connection = new Connect();

            int result = connection.setQuery("DELETE FROM `gestion_tickets`.`projects` WHERE `ProjectID` = "
                    + t.getProjectsId() + ";");

            if (result <= 0) {
                logger.error("DELETE query in Projects table has failed");
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            logger.error("Error processing DELETE query in save method. Message: " + e.getMessage());
            return false;
        }
    }

    public Project getProjectName(int id) {
        Project foundName = null;
        try {
            Connect connection = new Connect();
            connection.setRs("SELECT * FROM PROJECTS WHERE PROJECTID=" + id + ";");
            ResultSet projectRs = (ResultSet) connection.getRs();
            while (projectRs.next()) {
                foundName = new Project();
                foundName.setProjectName("PROJECTNAME");
            }
        } catch (Exception e) {
            logger.error("Error processing ResultSet in getProjectName() method. Message: " + e.getMessage());
        }
        return foundName;
    }

    public List<Project> getProjbyDepto(int id) {

        Connect connection = null;
        List<Project> projectFound = new ArrayList<>();
        try {
            connection = new Connect();
        } catch (SQLException ex) {
            logger.error("Error creating conecction in getProjbyDepto() method. Message: " + ex.getMessage());
        }
        try {

            connection.setRs("SELECT * FROM PROJECTS WHERE DEPARTMENTID = " + id+ ";");
            ResultSet projectSet = (ResultSet) connection.getRs();

            while (projectSet.next()) {
                Project project = new Project();
                project.setProjectsId(projectSet.getInt("PROJECTID"));
                project.setDepartmentId(projectSet.getInt("DEPARTMENTID"));
                project.setProjectName(projectSet.getString("PROJECTNAME"));
                project.setProjectDescription(projectSet.getString("PROJECTDESCRIPTION"));
                project.setCreationDate(projectSet.getTimestamp("CREATIONDATE"));

                projectFound.add(project);

            }

        } catch (SQLException e) {
            logger.error("Error processing ResultSet in getProjbyDepto() method. Message: " + e.getMessage());
        } finally {
            try {
                connection.cerrarConexion();
            } catch (SQLException ex) {
                logger.error("Error closing conecction in getProjbyDepto() method. Message: " + ex.getMessage());
            }

        }
        return projectFound;

    }

}
