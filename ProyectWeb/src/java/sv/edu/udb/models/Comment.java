/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Imer Palma
 */
public class Comment {

    private int id, employeeId, departmentId, requestId;
    private String commentText;
    private Date commentDate;
    private Attachment associatedAttachment;

    public Comment() {
    }

    public Comment(String daoDefault) {
        this.commentText = daoDefault;
    }

    public void setCommentId(int commentId) {
        this.id = commentId;
    }

    public int getCommentId() {
        return id;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setDepartmentId(int department) {
        this.departmentId = department;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setRequestId(int request) {
        this.requestId = request;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setCommentText(String comment) {
        this.commentText = comment;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public Attachment getAssociatedAttachment() {
        return associatedAttachment;
    }

    public void setAssociatedAttachment(Attachment associatedAttachment) {
        this.associatedAttachment = associatedAttachment;
    }    
}
