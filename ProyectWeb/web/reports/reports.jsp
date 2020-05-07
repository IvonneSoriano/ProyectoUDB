<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte de estados</title>
      
    </head>
    <body>
         
          <div class="container">
            <div class="row">
                <h2>Reportes</h2>
                <form action="ReportsStatus" method="POST" class="form-control">
                <select name="estado" class="form-control" >
                <option value="VENCIDO">Vencido</option>
                <option value="EN_ESPERA">En espera</option>
                <option value="FINALIZADO">Finalizado</option>
                <option value="ASIGNAR_PROGRAMADOR">Asignar programador</option>
                <option value="EN_DESARROLLO">En desarrollo</option>
                <option value="SOLICITUD_RECHAZADA">Solicitud rechazada</option>
                <option value="DEVUELTO_CON_OBSERVACIONES">Devuelto con observaciones</option>
                <option value="ESPERANDO_APROBACION_AREA_SOLICITANTE">Esperando aprobacion area solicitante</option>
                <option value="SOLICITUD_ACEPTADA">Solicitud aceptada</option>

                </select>
                <input type="submit" value="Enviar" class="btn-primary" >
                </form>
            </div>
            <div class="row">
                <form action="ReportAllRequest" method="POST" class="form-control">
                 <input type="submit" value="Enviar" class="btn-primary" >
                </form>
            </div> 
              <div class="row">
                <form action="ReportAllRequest" method="POST" class="form-control">
                 <input type="submit" value="Enviar" class="btn-primary" >
                </form>
            </div> 
          </div>
    </body>
</html>
