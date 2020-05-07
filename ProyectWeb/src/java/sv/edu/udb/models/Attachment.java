/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

import java.io.InputStream;
import java.sql.Blob;

/**
 *
 * @author Rick
 */
public class Attachment {
    private int commentId;
    private String attachmentName;
    private float attachmentSize;
    private String contentType;
    private byte[] attachmentFile;
    private InputStream fileIS;
    private Blob file;
    
    public Attachment() {
        
    }
    
    public Attachment(String daoDefault) {
        this.attachmentName = daoDefault;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public float getAttachmentSize() {
        return attachmentSize;
    }

    public void setAttachmentSize(float attachmentSize) {
        this.attachmentSize = attachmentSize;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getAttachmentFile() {
        return attachmentFile;
    }

    public void setAttachmentFile(byte[] attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    public InputStream getFileIS() {
        return fileIS;
    }

    public void setFileIS(InputStream fileIS) {
        this.fileIS = fileIS;
    }

    public Blob getFile() {
        return file;
    }

    public void setFile(Blob file) {
        this.file = file;
    }
    
}
