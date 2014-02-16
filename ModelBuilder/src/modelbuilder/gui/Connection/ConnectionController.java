/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelbuilder.gui.Connection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelbuilder.gui.Controller;
import modelbuilder.DataBaseType;

/**
 *
 * @author david
 */
public class ConnectionController implements ActionListener {

    private ConnectionView view;
    private Controller ctrl;

    public ConnectionController(ConnectionView view, Controller ctrl) {
        this.view = view;
        this.ctrl = ctrl;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        createConnection();
    }

    private void createConnection(){
        String user;
        String password;
        String url;
        
        if (isValid()) {
            clearErrorLabels();

            user = view.getUserText();
            password = view.getPasswordText();
            url = view.getURLText();

            try {
                setDBType();
                ctrl.initDBConnector(url, password, user);
                view.nextPanel();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "SQL Connection Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            putErrorMessage();
        }
    }
    
    private boolean isValid() {
        String user = view.getUserText();
        String password = view.getPasswordText();
        String url = view.getURLText();
        boolean returnBool = false;

        if ((user.length() > 0) && (password.length() > 0) && (url.length() > 0)) {
            returnBool = true;
        }
        return returnBool;
    }

    private void setDBType() {
        DataBaseType selectedDBType;

        selectedDBType = view.getSelectedDBType();

        switch (selectedDBType) {
            case SQL:
                ctrl.setDBType(DataBaseType.SQL);
                break;
            case MYSQL:
                ctrl.setDBType(DataBaseType.MYSQL);
                break;
        }
    }
    
    private void putErrorMessage() {
        String user = view.getUserText();
        String password = view.getPasswordText();
        String url = view.getURLText();

        if (user.length() == 0) {
            view.setUserErrorText("*");
            view.setUserToolTipText("A username is required!");
        } else {
            view.setUserErrorText("");
            view.setUserToolTipText("");
        }

        if (password.length() == 0) {
            view.setPasswordErrorText("*");
            view.setPasswordToolTipText("A password is requied!");
        } else {
            view.setPasswordErrorText("");
            view.setPasswordToolTipText("");
        }

        if (url.length() == 0) {
            view.setURLErrorText("*");
            view.setURLToolTipText("A URL is required");
        } else {
            view.setURLErrorText("");
            view.setURLToolTipText("");
        }
    }

    private void clearErrorLabels() {
        putErrorMessage(); // since only called when is valid should auto clear error labels!
    }
    
    public void setDefaultDBType(){
        view.setDefaultDBType(ctrl.getDefaultDBType());
    }
}
