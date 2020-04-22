/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;

import java.util.List;
import sv.edu.udb.models.Rol;
import sv.edu.udb.models.RolDAO;

/**
 *
 * @author kiss_
 */
public class RolController {
    
      public Rol showRol(int id) {
        RolDAO dao = new RolDAO();
        return dao.getOne(id);
    }
      
       public Rol getRol(int id) {
        RolDAO dao = new RolDAO();
        return dao.getRol(id);
    }
      public List<Rol> showRol() {
        RolDAO dao = new RolDAO();
        return dao.getAll();
    }
      
        public int showID(String rol){
          RolDAO dao = new RolDAO();
          return dao.getId(rol);
      }
}
