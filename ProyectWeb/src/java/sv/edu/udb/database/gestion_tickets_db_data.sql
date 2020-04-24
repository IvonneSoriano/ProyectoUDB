/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Rick
 * Created: Mar 11, 2020
 */
ALTER TABLE `gestion_tickets`.`roles` AUTO_INCREMENT = 0;
INSERT INTO `gestion_tickets`.`roles` (`ROLNAME`) VALUES ('JEFE_AREA_FUNCIONAL');
INSERT INTO `gestion_tickets`.`roles` (`ROLNAME`) VALUES ('EMPLEADO_AREA_FUNCIONAL');
INSERT INTO `gestion_tickets`.`roles` (`ROLNAME`) VALUES ('JEFE_DE_DESARROLLO');
INSERT INTO `gestion_tickets`.`roles` (`ROLNAME`) VALUES ('PROGRAMADOR');
INSERT INTO `gestion_tickets`.`roles` (`ROLNAME`) VALUES ('ADMINISTRADOR');

ALTER TABLE `gestion_tickets`.`departments` AUTO_INCREMENT = 0;
INSERT INTO `gestion_tickets`.`departments` (`DEPARTMENTNAME`) VALUES ('Finanzas');
INSERT INTO `gestion_tickets`.`departments` (`DEPARTMENTNAME`) VALUES ('Ventas');
INSERT INTO `gestion_tickets`.`departments` (`DEPARTMENTNAME`) VALUES ('Facturacion');
INSERT INTO `gestion_tickets`.`departments` (`DEPARTMENTNAME`) VALUES ('Relaciones Publicas');

ALTER TABLE `gestion_tickets`.`employees` AUTO_INCREMENT = 0;
INSERT INTO `gestion_tickets`.`employees` (`EmployeeID`,`ROLID`,`DEPARTMENTID`,`EMPLOYEENAME`,`EMPLOYEELASTNAME`,`USERNAME`,`PASSWORD`) VALUES (1,1,1,'Administrador','I','admin1',SHA2('admin1', 256));
INSERT INTO `gestion_tickets`.`employees` (`EmployeeID`,`ROLID`,`DEPARTMENTID`,`EMPLOYEENAME`,`EMPLOYEELASTNAME`,`USERNAME`,`PASSWORD`) VALUES (2,1,1,'Alberto','Jefe de Finanzas','albertofinanzas',SHA2('albertofinanzas', 256));
INSERT INTO `gestion_tickets`.`employees` (`EmployeeID`,`ROLID`,`DEPARTMENTID`,`EMPLOYEENAME`,`EMPLOYEELASTNAME`,`USERNAME`,`PASSWORD`) VALUES (3,2,1,'Will','Empleado de Finanzas','willfinanzas',SHA2('willfinanzas', 256));
INSERT INTO `gestion_tickets`.`employees` (`EmployeeID`,`ROLID`,`DEPARTMENTID`,`EMPLOYEENAME`,`EMPLOYEELASTNAME`,`USERNAME`,`PASSWORD`) VALUES (4,3,1,'Rodrigo','Jefe de desarrollo Finanzas','rodrigofinanzas',SHA2('rodrigofinanzas', 256));
INSERT INTO `gestion_tickets`.`employees` (`EmployeeID`,`ROLID`,`DEPARTMENTID`,`EMPLOYEENAME`,`EMPLOYEELASTNAME`,`USERNAME`,`PASSWORD`) VALUES (5,1,2,'Carlos','Jefe de Ventas','carlosventas',SHA2('carlosventas', 256));
INSERT INTO `gestion_tickets`.`employees` (`EmployeeID`,`ROLID`,`DEPARTMENTID`,`EMPLOYEENAME`,`EMPLOYEELASTNAME`,`USERNAME`,`PASSWORD`) VALUES (6,2,2,'Santiago','Empleado de Ventas','santiagoventas',SHA2('santiagoventas', 256));
INSERT INTO `gestion_tickets`.`employees` (`EmployeeID`,`ROLID`,`DEPARTMENTID`,`EMPLOYEENAME`,`EMPLOYEELASTNAME`,`USERNAME`,`PASSWORD`) VALUES (7,3,2,'Armando','Jefe de desarrollo Ventas','armandoventas',SHA2('armandoventas', 256));


ALTER TABLE `gestion_tickets`.`projects` AUTO_INCREMENT = 0;
INSERT INTO `gestion_tickets`.`projects` (`PROJECTID`,`DEPARTMENTID`,`PROJECTNAME`,`PROJECTDESCRIPTION`,`CREATIONDATE`) VALUES (1,1,'Proyecto DNS y Tunneling ','Proyecto prueba','2020-01-01 10:10:10');
INSERT INTO `gestion_tickets`.`projects` (`PROJECTID`,`DEPARTMENTID`,`PROJECTNAME`,`PROJECTDESCRIPTION`,`CREATIONDATE`) VALUES (2,2,'Otro proyecto','Bien bonito el proyecto.','2020-03-19 19:07:18');
INSERT INTO `gestion_tickets`.`projects` (`PROJECTID`,`DEPARTMENTID`,`PROJECTNAME`,`PROJECTDESCRIPTION`,`CREATIONDATE`) VALUES (4,1,'Proyecto regional de marketing','Bien bonito el proyecto 2.','2020-03-19 19:09:02');
INSERT INTO `gestion_tickets`.`projects` (`PROJECTID`,`DEPARTMENTID`,`PROJECTNAME`,`PROJECTDESCRIPTION`,`CREATIONDATE`) VALUES (5,2,'Otro proyecto Facturas 3','Bien bonito el proyecto 2.','2020-03-19 19:09:40');
INSERT INTO `gestion_tickets`.`projects` (`PROJECTID`,`DEPARTMENTID`,`PROJECTNAME`,`PROJECTDESCRIPTION`,`CREATIONDATE`) VALUES (6,1,'Proyecto de facturacion de emergencia','Un Proyecto','2020-03-19 05:46:30');

ALTER TABLE `gestion_tickets`.`requesttypes` AUTO_INCREMENT = 0;
INSERT INTO `gestion_tickets`.`requesttypes` (`REQUESTTYPEID`,`REQUESTTYPENAME`) VALUES (1,'SISTEMA_NUEVO');
INSERT INTO `gestion_tickets`.`requesttypes` (`REQUESTTYPEID`,`REQUESTTYPENAME`) VALUES (2,'NUEVA_FUNCIONALIDAD');
INSERT INTO `gestion_tickets`.`requesttypes` (`REQUESTTYPEID`,`REQUESTTYPENAME`) VALUES (3,'CORRECCION');

UPDATE `employees` SET `PASSWORD`= SHA2('admin1', 256) WHERE `EMPLOYEENAME` = 'ADMINISTRADOR'
