/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

/**
 *
 * @author Imer
 */
public class RequestType {

    private int id;
    private String requestName;

    public RequestType() {
    }

    public RequestType(String daoDefault) {
        this.requestName = daoDefault;
    }

    public void setId(int idRequest) {
        this.id = idRequest;
    }

    public int getId() {
        return id;
    }

    public void setRequestTypeName(String requestName) {
        this.requestName = requestName;
    }

    public String getRequestTypeName() {
        return requestName;
    }

}
