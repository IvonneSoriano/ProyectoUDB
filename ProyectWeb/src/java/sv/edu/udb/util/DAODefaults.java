/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.util;

/**
 *
 * @author Rick
 */
public enum DAODefaults {
    NON_EXISTING_USER("Did not find any user that matches your query."),
    NON_EXISTING_REQUEST_TYPE("Did not find any request type that matches your request"),
    NO_LAST_REQUEST_FOUND("No last request was found on the DB"),
    NO_REQUEST_FOUND("Did not find any request that matches your query"),
    NO_PROJECT_FOUND("Did not find any project that matches your query"),
    NO_COMMENT_FOUND("Did not find any comment that matches your query"),
    NO_ATTACHMENT_FOUND("Did not find any attachment that matches your query");

    private String defaultValue;

    private DAODefaults(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
