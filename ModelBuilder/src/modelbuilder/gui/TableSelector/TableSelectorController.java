/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelbuilder.gui.TableSelector;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelbuilder.Language;
import modelbuilder.gui.Controller;

/**
 *
 * @author david
 */
public class TableSelectorController implements ActionListener {

    private String[] tables;
    private TableSelectorView view;
    private Controller ctrl;
    private Language choice;

    public TableSelectorController(TableSelectorView view, Controller ctrl) {
        this.view = view;
        this.ctrl = ctrl;
    }

    public String[] getTables() {

        try {
            tables = ctrl.getAvaliableTables();
        } catch (SQLException ex) {
            tables = new String[1];
            tables[0] = "None";
        }
        return tables;
    }

    public void setDefaultLang(){
        view.setDefaultLang(ctrl.getDefaultLanguage());
    }
    
    public void setDefaultPath(){
        view.setDefaultPath(ctrl.getDefaultPath());
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        if (command.equals(TableSelectorView.ActionCommands.CREATE.toString())) {
            createCommand();
        } else if (command.equals(TableSelectorView.ActionCommands.BACK.toString())) {
            view.previousPanel();
        }
    }

    private void createCommand() {
        choice = view.getSelectedRB();
        String selectedTable = view.getSelectedTable();

        if (view.getFilePath().length() > 0) {
            
            ctrl.setPath(view.getFilePath());
            
            clearFilePathError();
            
            if (selectedTable.equals("None")) {
                
                JOptionPane.showMessageDialog(new JFrame(), "There are no tables", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (selectedTable.equals("All")) {
                
                createAllTables();
                completeMessage(tables.length);
                
            } else {
                createSingleTable(selectedTable);
                completeMessage(1);
            }
        } else {
            filePathError();
        }
    }

    private void createAllTables() {
        try {
            for (int i = 0; i < tables.length; i++) {
                createTable(tables[i]); 
            }
        } catch (SQLException sql) {
            JOptionPane.showMessageDialog(new JFrame(), sql.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ex) {
        }
    }

    private void createSingleTable(String tableName) {
        try {
            createTable(tableName);
        } catch (SQLException sql) {
            JOptionPane.showMessageDialog(new JFrame(), sql.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ex) {
        }
    }

    private void createTable(String tableName) throws SQLException{
        switch (choice){
            case JAVA:
                ctrl.createJavaTable(tableName);
                break;
            case PHP:
                ctrl.createPHPTable(tableName);
                break;
            case CS:
                ctrl.createCSTable(tableName);
                break;
        }
    }
    
    private void filePathError() {
        view.setFilePathError("*");
        view.setFilePathToolTipText("Please select file destination");
    }

    private void clearFilePathError() {
        view.setFilePathError("");
        view.setFilePathToolTipText("");
    }
    
    private void completeMessage(int count){
        String msg;    
        if(count > 1){
               msg = count + " datamodels created";
            } else {
               msg = count + " datamodel created";
            }
            JOptionPane.showMessageDialog(new JFrame(), msg, "Complete", JOptionPane.INFORMATION_MESSAGE);
    }
}
