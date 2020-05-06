/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

import java.io.InputStream;
/**
 *
 * @author kiss_
 */
public class FileRequest {

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    private int id;
    private String fileName;
    private byte[] attachmentFile;
    private InputStream fileIS;
}
