/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Imer
 */
public class Request {

    private int id, typeId, projectId;
    private String description, status;
    private Timestamp requestDate;
    private int departmentId;
    private List<Comment> commentsList = new ArrayList<>();

    public Request() {
    }

    public Request(String daoDefault) {
        this.description = daoDefault;
    }

    public void setId(int idRequest) {
        this.id = idRequest;
    }

    public int getId() {
        return id;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setIdTypeRequest(int typeRequest) {
        this.typeId = typeRequest;
    }

    public int getIdTypeRequest() {
        return typeId;
    }

    public void setRequestDescription(String requestDescription) {
        this.description = requestDescription;
    }

    public String getRequestDescription() {
        return description;
    }

    public void setRequestStatus(String requestStatus) {
        this.status = requestStatus;
    }

    public String getRequestStatus() {
        return status;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    public Timestamp getRequestDate() {
        return requestDate;
    }

    public List<Comment> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comment> commentsList) {
        this.commentsList = commentsList;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
