/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.util;

import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Imer Palma
 */
public class Validations {

    public void numberTyped(KeyEvent ke) {
        char c = ke.getKeyChar();
        if (Character.isDigit(c)) {
            ke.consume();
            //JOptionPane.showMessageDialog(null, "Error en el ingreso de datos");
        }
    }

    public boolean emptyField(String cadena) {
        return cadena.isEmpty();
    }

    public boolean checkPassword(char password[]) {
        for (int i = 0; i < password.length; i++) {
            char c = password[i];
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        return true;

    }
}
