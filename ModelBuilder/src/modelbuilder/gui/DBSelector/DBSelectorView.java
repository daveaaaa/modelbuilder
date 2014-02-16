/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelbuilder.gui.DBSelector;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modelbuilder.gui.Gui;

/**
 *
 * @author david
 */
public class DBSelectorView extends JPanel {

    private boolean initialised = false;
    private JComboBox ddDataBase;
    private JLabel lblDataBase;
    private DBSelectorController ctrl;
    private JButton btnSelectDB;
    private Gui parent;
    private GridBagConstraints gc;
    private JButton btnBack;
    private JPanel buttonPanel;
    private JPanel DBPanel;
    
    public enum ActionCommands{
        SELECT,
        BACK,
    }

    public DBSelectorView(Gui parent) {
        this.parent = parent;
        ctrl = new DBSelectorController(this, this.parent.getController());
        initialised = false;
    }

    public void nextPanel() {
        this.parent.nextPanel(Gui.Panels.TABLE_SELECTION);
    }

    public void previousPanel() {
        this.parent.nextPanel(Gui.Panels.SERVER_CONNECTION_SETTINGS);
    }

    public String getSelectedValue() {
        return (String) ddDataBase.getSelectedItem();
    }

    public void disableBtnSelectDB() {
        btnSelectDB.setEnabled(false);
    }

    public void enableBtnSelectDB() {
        btnSelectDB.setEnabled(true);
    }
    

    public void init() {

        if (initialised == false) {
            gc = setLookAndFeel();
            buttonPanel = new JPanel();
            DBPanel = new JPanel();

            initDDDataBase();
            initBtnSelectDB();

            gc.weightx = 0.5;
            gc.weighty = 0.5;
            gc.gridx = 0;
            gc.gridy = 0;

            this.add(DBPanel, gc);

            gc.weightx = 50;
            gc.weighty = 50;
            gc.gridx = 0;
            gc.gridy = 1;

            this.add(buttonPanel, gc);

            initialised = true;
        }
        populateDDDataBase();

    }

    private GridBagConstraints setLookAndFeel() {
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createTitledBorder("Database Details"));
        Dimension size = getPreferredSize();
        size.width = 200;
        size.height = 150;
        setPreferredSize(size);
        return new GridBagConstraints();
    }

    private void populateDDDataBase() {
        ddDataBase.removeAllItems();
        
        String[] dataBases = ctrl.getDataBases();
        for (int i = 0; i < dataBases.length; i++) {
            ddDataBase.addItem(dataBases[i]);
        }
    }

    private void initDDDataBase() {
        ddDataBase = new JComboBox();

        lblDataBase = new JLabel("Select database: ");

        DBPanel.setLayout(new GridLayout(1, 2));
        DBPanel.add(lblDataBase);

        DBPanel.add(ddDataBase);

    }

    private void initBtnSelectDB() {
        btnSelectDB = new JButton("Select");
        btnSelectDB.addActionListener(ctrl);
        btnBack = new JButton("< Back");
        btnBack.addActionListener(ctrl);

        btnSelectDB.setActionCommand(ActionCommands.SELECT.toString());
        btnBack.setActionCommand(ActionCommands.BACK.toString());
        
        buttonPanel.add(btnBack);
        buttonPanel.add(btnSelectDB);
    }
}
