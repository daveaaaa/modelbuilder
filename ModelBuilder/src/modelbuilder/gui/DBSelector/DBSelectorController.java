/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelbuilder.gui.DBSelector;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import modelbuilder.gui.Controller;

/**
 *
 * @author david
 */
public class DBSelectorController implements ActionListener{
    
    private Controller ctrl; 
    private DBSelectorView view;
    
    public DBSelectorController(DBSelectorView view, Controller controller){
        this.ctrl = controller;
        this.view = view; 
    }
 
    public String[] getDataBases(){
        String [] availableDBs;
        try{
            availableDBs =  ctrl.getAvailableDBs();
        } catch (SQLException ex){
            availableDBs = new String[1];
            availableDBs[0] = "None";
        }
        return availableDBs; 
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
               String command = arg0.getActionCommand();

        if (command.equals(DBSelectorView.ActionCommands.SELECT.toString())) {
            nextPanel();
        } else if (command.equals((DBSelectorView.ActionCommands.BACK.toString()))) {
            previousPanel();
        }
    }
    
    
    private void nextPanel(){
        String selectedDB;
        
        selectedDB = view.getSelectedValue(); 
        
        ctrl.setSelectedDB(selectedDB);
        
        view.nextPanel();
    }
    
    private void previousPanel(){
        view.previousPanel();
    }
}
