    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

import java.util.ArrayList;
import java.util.List;
import sv.edu.udb.util.Validations;

/**
 *
 * @author Imer Palma
 */
public class Employee {

    private int id, rolId, departmentId;
    private String name, lastname;
    private String username;
    private String password;
    private List<Comment> commentsList = new ArrayList<>();

    public Employee() {

    }

    public Employee(String username) {
        this.username = username;
    }
    
    public Employee(String name, String surname) {
        this.name = name;
        this.lastname = surname;
    }

    public void setEmployeeId(int employeeID) {
        this.id = employeeID;
    }

    public int getEmployeeId() {
        return id;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public int getRolId() {
        return rolId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setEmployeeName(String employeeName) {
        this.name = employeeName;
    }

    public String getEmployeeName() {
        return name;
    }

    public void setEmployeeLastname(String employeeLastname) {
        this.lastname = employeeLastname;
    }

    public String getEmployeeLastname() {
        return lastname;
    }

    public List<Comment> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comment> commentsList) {
        this.commentsList = commentsList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return name + " " + lastname;
    }

    public String getName(String f) {
        String[] onlyName = f.split(" ");
        return onlyName[0];
    }

    public String getLastname(String f) {
        String[] onlyName = f.split(" ");
        return onlyName[1];
    }
    
    public String getRolName(int id) {
        return Validations.getDisplayRol(id);
    }
    
    public String getDepartmentName(int id) {
        DeparmentDAO d = new DeparmentDAO();
        Deparment de = d.getOne(id);
        return de.getDepartmentName();
    }
}
