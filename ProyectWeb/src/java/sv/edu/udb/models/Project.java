/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Imer Palma
 */
public class Project {

    private int id, departmentId;
    private String name, description;
    private Timestamp creationDate;

    public Project() {
    }
    
    public Project(String daoDefault) {
        this.name = daoDefault;
    }

    public void setProjectsId(int projectsId) {
        this.id = projectsId;
    }

    public int getProjectsId() {
        return id;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setProjectName(String name) {
        this.name = name;
    }

    public String getProjectName() {
        return name;
    }

    public void setProjectDescription(String description) {
        this.description = description;
    }

    public String getProjectDescription() {
        return description;
    }

    public void setCreationDate(Timestamp date) {
        this.creationDate = date;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

}
