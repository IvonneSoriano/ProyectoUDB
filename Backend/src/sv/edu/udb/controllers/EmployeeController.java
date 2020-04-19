/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;

import java.util.List;
import java.util.Optional;
import sv.edu.udb.models.Employee;
import sv.edu.udb.models.EmployeeDAO;
import sv.edu.udb.util.DAODefaults;

/**
 *
 * @author Rick
 */
public class EmployeeController {

    public boolean insertEmployee(Employee e) {
        EmployeeDAO dao = new EmployeeDAO();
        return dao.save(e);
    }

    public Employee findEmployee(String username, char [] password) {
        EmployeeDAO dao = new EmployeeDAO();
        Optional<Employee> foundEmp = dao.getEmployeeByUsername(username, password);

        return foundEmp.orElseGet(() -> new Employee(DAODefaults.NON_EXISTING_USER.getDefaultValue()));
    }

    public List<Employee> findEmployee(int rol) {
        EmployeeDAO dao = new EmployeeDAO();
        List<Employee> foundEmp = dao.getAllByRol(rol);

        return foundEmp;
    }

    public List<Employee> findEmployee(int rol, int dep) {
        EmployeeDAO dao = new EmployeeDAO();
        List<Employee> foundEmp = dao.getAllByRolAndDepto(rol, dep);

        return foundEmp;
    }

    public List<Employee> findEmployees() {
        EmployeeDAO dao = new EmployeeDAO();
        List<Employee> foundEmp = dao.getAllEmployees();

        return foundEmp;
    }

    public List<Employee> findSupervisors() {
        EmployeeDAO dao = new EmployeeDAO();
        List<Employee> foundEmp = dao.getAllSupervisors();

        return foundEmp;
    }

    public boolean deleteEmployee(Employee t) {
        EmployeeDAO dao = new EmployeeDAO();
        return dao.delete(t);
    }

    public Employee getEmployeeById(int id) {
        EmployeeDAO dao = new EmployeeDAO();
        Employee foundEmp = dao.getEmployeeById(id);

        return foundEmp;
    }
    
    public boolean updateEmployee(Employee e, String[] p, String pass){
         EmployeeDAO dao = new EmployeeDAO();
         return dao.updateUser(e,p, pass);
        
    }
    
    public List<Employee> findByRolAndDepto(int rol, int depto) {
        EmployeeDAO dao = new EmployeeDAO();
        List<Employee> foundEmp = dao.getAll(rol, depto);
        return foundEmp;
    }
    
    public Employee getEmployee(String name, String last) {
        EmployeeDAO dao = new EmployeeDAO();
        return  dao.getEmployeeByFullName(name, last);
       
    }
}
