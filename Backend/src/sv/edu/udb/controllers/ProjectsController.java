/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;

import java.util.List;
import java.util.Optional;
import sv.edu.udb.models.Project;
import sv.edu.udb.models.ProjectDAO;
import sv.edu.udb.util.DAODefaults;

/**
 *
 * @author Imer Palma
 */
public class ProjectsController {

    public List<Project> projectsByDepto() {
        ProjectDAO dao = new ProjectDAO();
        List<Project> projectsByDepto = dao.getProjbyDepto();

        return projectsByDepto;
    }

    public List<Project> getProjects() {
        ProjectDAO dao = new ProjectDAO();
        List<Project> projects = dao.getAll();

        return projects;
    }
   
    public boolean insertProject(Project p) {
        ProjectDAO dao = new ProjectDAO();
        return dao.save(p);
    }

    public boolean deleteProject(Project p) {
        ProjectDAO dao = new ProjectDAO();
        return dao.delete(p);
    }

    public Project findById(int id) {
        ProjectDAO dao = new ProjectDAO();
        Optional<Project> projectFound = dao.get(id);
        return projectFound.orElseGet(() -> new Project(DAODefaults.NO_PROJECT_FOUND.getDefaultValue()));
    }

    public Project getProjectName(int id){
        ProjectDAO dao = new ProjectDAO();
        return  dao.getProjectName(id);
    }
}
