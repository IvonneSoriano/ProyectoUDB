/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Imer
 */
public class Deparment {

    private int id;
    private String name;
    private List<Project> projectsList = new ArrayList<>();
    private List<Employee> employeesList = new ArrayList<>();

    public Deparment() {
    }

    public void setDepartmentId(int idDepartment) {
        this.id = idDepartment;
    }

    public int getDepartmentId() {
        return id;
    }

    public void setDepartmentName(String departmentName) {
        this.name = departmentName;
    }

    public String getDepartmentName() {
        return name;
    }

    public List<Project> getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(List<Project> projectsList) {
        this.projectsList = projectsList;
    }

    public List<Employee> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employee> employeesList) {
        this.employeesList = employeesList;
    }

}
