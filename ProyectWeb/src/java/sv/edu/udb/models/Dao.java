/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Rick
 */
public interface Dao<T> {

    Optional<T> get(long id);

    List<T> getAll();

    boolean save(T t);

    boolean update(T t, String[] params);

    boolean delete(T t);
}
