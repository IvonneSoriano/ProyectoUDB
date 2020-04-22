/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;

import java.util.List;
import java.util.Optional;
import sv.edu.udb.models.RequestType;
import sv.edu.udb.models.RequestTypeDAO;
import sv.edu.udb.util.DAODefaults;

/**
 *
 * @author Rick
 */
public class RequestTypeController {

    public List<RequestType> findRequestTypes() {
        RequestTypeDAO dao = new RequestTypeDAO();
        return dao.getAll();
    }

    public RequestType findRequestTypeByName(String name) {
        RequestTypeDAO dao = new RequestTypeDAO();
        Optional<RequestType> req = dao.getRequestTypeByName(name);
        return req.orElseGet(() -> new RequestType(DAODefaults.NON_EXISTING_REQUEST_TYPE.getDefaultValue()));
    }

    public RequestType findRequestTypeById(int id) {
        RequestTypeDAO dao = new RequestTypeDAO();
        Optional<RequestType> req = dao.get(id);
        return req.orElseGet(() -> new RequestType(DAODefaults.NON_EXISTING_REQUEST_TYPE.getDefaultValue()));
    }
    public RequestType getName(int id){
        RequestTypeDAO dao = new RequestTypeDAO();
        return dao.getRequestTypeName(id);

    }
}
