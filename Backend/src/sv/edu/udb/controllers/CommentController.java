/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controllers;

import java.util.List;
import sv.edu.udb.models.Comment;
import sv.edu.udb.models.CommentDAO;

/**
 *
 * @author Rick
 */
public class CommentController {

    public boolean saveComment(Comment c) {
        return new CommentDAO().save(c);
    }
    public List<Comment> getAllid(int id) {
        return new CommentDAO().getAllid(id);
    }
}
