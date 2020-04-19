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
    SOLICITUD_ACEPTADA    ;
    
    public String getStatus(RequestStatus p){
        return p.toString();
    }
   
}
