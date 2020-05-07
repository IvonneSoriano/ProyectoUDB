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
public enum RequestStatus {
    VENCIDO,
    EN_ESPERA,
    FINALIZADO,
    ASIGNAR_PROGRAMADOR,
    EN_DESARROLLO,
    SOLICITUD_RECHAZADA,
    DEVUELTO_CON_OBSERVACIONES,
    ESPERANDO_APROBACION_AREA_SOLICITANTE,
    SOLICITUD_ACEPTADA;

    public String getStatus(RequestStatus p) {
        return p.toString();
    }

    public RequestStatus[] getStatusByRol(int rolId) {
        int statusCount = 0;
        RequestStatus[] names = new RequestStatus[8];

        if (rolId == Roles.PROGRAMADOR.getRolId()) {
            names[statusCount] = ASIGNAR_PROGRAMADOR;
            names[++statusCount] = EN_DESARROLLO;
            names[++statusCount] = ESPERANDO_APROBACION_AREA_SOLICITANTE;
        } else if (rolId == Roles.EMPLEADO_AREA_FUNCIONAL.getRolId() || rolId == Roles.JEFE_AREA_FUNCIONAL.getRolId()) {
            names[statusCount] = ESPERANDO_APROBACION_AREA_SOLICITANTE;
            names[++statusCount] = DEVUELTO_CON_OBSERVACIONES;
            names[++statusCount] = FINALIZADO;
        } else if (rolId == Roles.ADMINISTRADOR.getRolId() || rolId == Roles.JEFE_DE_DESARROLLO.getRolId()) {
            names[statusCount] = ASIGNAR_PROGRAMADOR;
            names[++statusCount] = EN_DESARROLLO;
            names[++statusCount] = ESPERANDO_APROBACION_AREA_SOLICITANTE;
            names[++statusCount] = DEVUELTO_CON_OBSERVACIONES;
            names[++statusCount] = FINALIZADO;
        }

        return names;
    }

}
