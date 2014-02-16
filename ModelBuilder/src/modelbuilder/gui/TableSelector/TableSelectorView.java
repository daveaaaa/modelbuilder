/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelbuilder.gui.TableSelector;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import modelbuilder.gui.Gui;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import modelbuilder.Language;

/**
 *
 * @author david
 */
public class TableSelectorView extends JPanel {

    private boolean initalised = false;
    private JTextField txtFilePath;
    private JLabel lblFilePathError;
    private JComboBox ddTable;
    private JPanel rbPanel;
    private JLabel lblTable;
    private ButtonGroup rbgTableType;
    private JRadioButton rbJava;
    private JRadioButton rbCS;
    private JRadioButton rbPHP;
    private JLabel lblFilePath;
    private TableSelectorController ctrl;
    private Gui parent;
    private GridBagConstraints gc;
    private JButton btnCreate;
    private JButton btnBack;
    private JPanel middlePanel;
    private JPanel topPanel;
    private JPanel buttonPanel;

    public enum ActionCommands {

        CREATE,
        BACK
    }

    public TableSelectorView(Gui parent) {
        this.parent = parent;
        this.ctrl = new TableSelectorController(this, this.parent.getController());
        initalised = false;
    }

    public String getFilePath() {
        return txtFilePath.getText();
    }
    
    public void setDefaultLang(Language lang){
        switch(lang){
            case PHP:
                rbPHP.setSelected(true);
                break;
            case JAVA:
                rbJava.setSelected(true);
                break;
            case CS:
                rbCS.setSelected(true);
                break;
        }
    }
    
    public void setDefaultPath(String path){
        txtFilePath.setText(path);
    }

    public void setFilePathError(String string) {
        lblFilePathError.setText(string);
    }

    public void setFilePathToolTipText(String string) {
        txtFilePath.setToolTipText(string);
    }

    public String getSelectedTable() {
        return (String) ddTable.getSelectedItem();
    }

    public void previousPanel() {
        parent.nextPanel(Gui.Panels.DB_SELECTION);
    }

    public Language getSelectedRB() {
        Language selectedLang = null;
        
        String value = rbgTableType.getSelection().getActionCommand();

        if (value.equals(Language.JAVA.getName())) {
            selectedLang = Language.JAVA;
        } else if (value.equals(Language.CS.getName())) {
            selectedLang = Language.CS;
        } else if (value.equals(Language.PHP.getName())) {
            selectedLang = Language.PHP;
        } 
        return selectedLang;
    }

    public void init() {
        if (initalised == false) {
            middlePanel = new JPanel();
            topPanel = new JPanel();
            buttonPanel = new JPanel();

            middlePanel.setLayout(new GridBagLayout());

            gc = setLookAndFeel();
            initFilePath();

            initDDTable();
            initRBTableType();

            initBtnCreate();

            this.setLayout(new GridBagLayout());
            gc.gridx = 0;
            gc.gridy = 0;
            gc.weightx = 5;
            gc.weighty = 5;

            this.add(topPanel, gc);
            gc.gridx = 0;
            gc.gridy = 1;

            gc.weightx = 0.50;
            gc.weighty = 0.50;

            this.add(middlePanel, gc);
            gc.gridx = 0;
            gc.gridy = 2;
            gc.weightx = 50;
            gc.weighty = 50;
            this.add(buttonPanel, gc);

            initalised = true;
        }
        populateDDTable();
        ctrl.setDefaultLang();
        ctrl.setDefaultPath();
    }

    private GridBagConstraints setLookAndFeel() {
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createTitledBorder("Create Table"));
        Dimension size = getPreferredSize();
        size.width = 200;
        size.height = 150;
        setPreferredSize(size);
        return new GridBagConstraints();
    }

    private void populateDDTable() {
        String[] tables;

        ddTable.removeAllItems();

        tables = ctrl.getTables();

        if (tables.length > 1) {
            ddTable.addItem("All");

        }

        for (int i = 0; i < tables.length; i++) {
            ddTable.addItem(tables[i]);
        }

    }

    private void initDDTable() {
        ddTable = new JComboBox();
        lblTable = new JLabel("Select tables");
        JPanel subPanel = new JPanel();

        subPanel.setLayout(new GridLayout(2, 1));

        subPanel.add(lblTable);
        subPanel.add(ddTable);

        gc.gridx = 0;
        gc.gridy = 0;

        middlePanel.add(subPanel, gc);
    }

    private void initRBTableType() {
        rbPanel = new JPanel(new GridLayout(0, 1));
        rbgTableType = new ButtonGroup();
        rbJava = new JRadioButton(Language.JAVA.getName(), true);
        rbCS = new JRadioButton(Language.CS.getName());
        rbPHP = new JRadioButton(Language.PHP.getName());

        rbJava.setActionCommand(Language.JAVA.getName());
        rbCS.setActionCommand(Language.CS.getName());
        rbPHP.setActionCommand(Language.PHP.getName());

        rbgTableType.add(rbJava);
        rbgTableType.add(rbCS);
        rbgTableType.add(rbPHP);

        rbPanel.add(rbJava);
        rbPanel.add(rbCS);
        rbPanel.add(rbPHP);
        
        rbPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(5), "Type"));

        gc.gridx = 1;
        gc.gridy = 0;

        middlePanel.add(rbPanel, gc);
    }

    private void initFilePath() {
        lblFilePath = new JLabel("File Path: ");
        txtFilePath = new JTextField(15);
        lblFilePathError = new JLabel(" ");
        lblFilePathError.setForeground(Color.red);

        topPanel.setLayout(new GridBagLayout());
        gc.gridx = 0;
        gc.gridy = 0;
        topPanel.add(lblFilePath, gc);
        gc.gridx = 1;
        gc.gridy = 0;
        topPanel.add(txtFilePath, gc);
        gc.gridx = 2;
        gc.gridy = 0;
        topPanel.add(lblFilePathError, gc);

    }

    private void initBtnCreate() {
        btnCreate = new JButton("Create models");
        btnCreate.addActionListener(ctrl);
        btnCreate.setActionCommand(ActionCommands.CREATE.toString());
        btnBack = new JButton("< Back");
        btnBack.addActionListener(ctrl);
        btnBack.setActionCommand(ActionCommands.BACK.toString());

        buttonPanel.setLayout(new GridLayout(1, 2));

        buttonPanel.add(btnBack);
        buttonPanel.add(btnCreate);

    }
}
