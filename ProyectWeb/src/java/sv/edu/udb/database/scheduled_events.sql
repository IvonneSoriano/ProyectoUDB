/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Rick
 * Created: Mar 26, 2020
 */

SET SQL_SAFE_UPDATES = 0; # se necesita correr solo una vez, antes de ejecutar evento 

CREATE EVENT tickets_vencidos_7_dias
ON SCHEDULE
	EVERY 1 DAY
        STARTS (TIMESTAMP(CURRENT_DATE) + INTERVAL 1 DAY + INTERVAL 7 HOUR)
ON COMPLETION PRESERVE
DO		
	UPDATE `gestion_tickets`.`tickets` ti
	SET    ti.`ticket_status` = 'VENCIDO' 
	WHERE  ti.`ticketid` IN 
		( 
			SELECT ids_matching_criteria.id 
			FROM   ( 
					SELECT pks.compare, 
						pks.id 
					FROM   ( 
							SELECT now() + interval 0 day  AS `now` , 
                                                                    now() + interval -7 day AS `days_ago` , 
                                                                    time_difference.datetimeadded , 
                                                                    time_difference.datetimeadded < now() + interval -7 day AS `compare` ,
                                                                    time_difference.id 
							FROM   ( 
										SELECT enddate  AS 'DateTimeAdded', 
                                                                                            ticketid AS 'Id' 
										FROM   tickets 
										WHERE ticket_status = 'DEVUELTO_CON_OBSERVACIONES' 
										) time_difference 
									) pks
					WHERE  pks.compare = 1 ) ids_matching_criteria 
		);