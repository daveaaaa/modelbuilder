/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelbuilder.gui.MenuBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelbuilder.gui.Controller;

/**
 *
 * @author david
 */
public class MenuBarController implements ActionListener {

    private MenuBar menuBar;
    private Controller ctrl;

    public MenuBarController(MenuBar menuBar, Controller ctrl) {
        this.menuBar = menuBar;
        this.ctrl = ctrl; 
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        String command = arg0.getActionCommand();

        if (command.contains(("about"))) {
            about();
        } else if (command.contains("exit")) {
            exit();
        } else if (command.contains("preferences")){
            showPreferences();
        }

    }

    private void showPreferences(){
        menuBar.showPreferences();
    }
    
    private void about() {
        String version = menuBar.getVersion();
        JOptionPane.showMessageDialog(new JFrame(), "Model builder " + version + " by David Armstrong", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exit() {
        System.exit(0);
    }
}
