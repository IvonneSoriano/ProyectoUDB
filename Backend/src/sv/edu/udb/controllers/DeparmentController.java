/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;

import java.util.List;
import sv.edu.udb.models.Deparment;
import sv.edu.udb.models.DeparmentDAO;
/**
 *
 * @author kiss_
 */
public class DeparmentController {
    
      public Deparment showDeparment(int id) {
        DeparmentDAO dao = new DeparmentDAO();
        return dao.getOne(id);
    }
      public Deparment showDepartment(String nombre){
        DeparmentDAO dao = new DeparmentDAO();
        return dao.getOneByName(nombre);
    }
      
      public List<Deparment> showDeparment() {
        DeparmentDAO dao = new DeparmentDAO();
        return dao.getAll();
    }
}