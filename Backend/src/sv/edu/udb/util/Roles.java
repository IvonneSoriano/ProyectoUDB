/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.util;

/**
 *
 * @author Rick
 */
public enum Roles {
    JEFE_AREA_FUNCIONAL(1),
    EMPLEADO_AREA_FUNCIONAL(2),
    JEFE_DE_DESARROLLO(3),
    PROGRAMADOR(4),
    ADMINISTRADOR(5);

    private int rolId;

    private Roles(int rolId) {
        this.rolId = rolId;
    }

    public int getRolId() {
        return rolId;
    }

}
