/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

/**
 *
 * @author Imer Palma
 */
public class Rol {

    private int id;
    private String name;

    public Rol() {
    }

    public void setRolId(int rolId) {
        this.id = rolId;
    }

    public int getRolId() {
        return id;
    }

    public void setRolName(String rolName) {
        this.name = rolName;
    }

    public String getRolName() {
        return name;
    }
}
