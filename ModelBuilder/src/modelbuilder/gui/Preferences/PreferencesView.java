package modelbuilder.gui.Preferences;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import modelbuilder.DataBaseType;
import modelbuilder.Language;
import modelbuilder.gui.Gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author david
 */
public class PreferencesView extends JPanel {

    private boolean initalised = false;
    private Gui parent;
    private JLabel lblPath;
    private JTextField txtPath;
    private ButtonGroup rbgDB;
    private JRadioButton rbSQL;
    private JRadioButton rbMYSQL;
    private ButtonGroup rbgLang;
    private JRadioButton rbJava;
    private JRadioButton rbPHP;
    private JRadioButton rbCS;
    private JPanel pnlDataBase;
    private JPanel pnlLang;
    private JPanel pnlPath;
    private JPanel pnlButtons;
    private JButton btnSave;
    private JButton btnCancel;
    private PreferencesController ctrl;
    private JPanel pnlTitle;
    private JLabel lblTitle;
    private JPanel pnlMiddle;
    private GridBagConstraints gc;

    public PreferencesView(Gui parent) {
        this.parent = parent;
        ctrl = new PreferencesController(this, parent.getController());
        init();
    }

    public void init() {
        if (initalised == false) {
            this.setLayout(new GridBagLayout());
            gc = new GridBagConstraints();

            initTitle();

            pnlMiddle = new JPanel();
            pnlMiddle.setLayout(new GridLayout(1, 2));
            initDBType();
            initLangType();

            initPath();
            initButtons();

            gc.anchor = GridBagConstraints.NORTH;
            gc.gridx = 0;
            gc.gridy = 0;
            this.add(pnlTitle, gc);
            gc.gridy = 1;
            this.add(pnlMiddle, gc);
            gc.gridy = 2;
            this.add(pnlPath, gc);
            gc.anchor = GridBagConstraints.CENTER;
            gc.weighty = 30;
            gc.gridy = 3;
            this.add(pnlButtons, gc);

            initalised = true;
        }
        getPreferences();
    }

    private void initTitle() {
        pnlTitle = new JPanel();
        lblTitle = new JLabel("Preferences");

        pnlTitle.add(lblTitle);

    }

    private void initDBType() {
        pnlDataBase = new JPanel();
        rbgDB = new ButtonGroup();
        rbSQL = new JRadioButton("SQL");
        rbMYSQL = new JRadioButton("MYSQL", true);

        rbSQL.setActionCommand(DataBaseType.SQL.getName());
        rbMYSQL.setActionCommand(DataBaseType.MYSQL.getName());

        rbgDB.add(rbSQL);
        rbgDB.add(rbMYSQL);

        pnlDataBase.setLayout(new GridLayout(2, 1));
        pnlDataBase.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(5), "Database Type"));
        pnlDataBase.add(rbSQL);
        pnlDataBase.add(rbMYSQL);

        pnlMiddle.add(pnlDataBase);
    }

    private void initLangType() {
        pnlLang = new JPanel();

        rbgLang = new ButtonGroup();

        rbJava = new JRadioButton(Language.JAVA.getName(), true);
        rbCS = new JRadioButton(Language.CS.getName());
        rbPHP = new JRadioButton(Language.PHP.getName());

        rbJava.setActionCommand(Language.JAVA.getName());
        rbCS.setActionCommand(Language.CS.getName());
        rbPHP.setActionCommand(Language.PHP.getName());

        rbgLang.add(rbJava);
        rbgLang.add(rbCS);
        rbgLang.add(rbPHP);

        pnlLang.add(rbJava);
        pnlLang.add(rbCS);
        pnlLang.add(rbPHP);

        pnlLang.setLayout(new GridLayout(3, 1));
        pnlLang.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(5), "Model Type"));

        pnlMiddle.add(pnlLang);
    }

    private void initPath() {
        pnlPath = new JPanel();
        lblPath = new JLabel("Path:");
        txtPath = new JTextField(15);

        pnlPath.add(lblPath);
        pnlPath.add(txtPath);

    }

    private void initButtons() {
        pnlButtons = new JPanel();

        btnSave = new JButton("Save");
        btnSave.setActionCommand("save");
        btnSave.addActionListener(ctrl);

        btnCancel = new JButton("Cancel");
        btnCancel.setActionCommand("cancel");
        btnCancel.addActionListener(ctrl);
        
        pnlButtons.add(btnSave);
        pnlButtons.add(btnCancel);

    }

    private void getPreferences() {
        ctrl.getPreferences();
    }

    public void setDBType(DataBaseType db) {
        if (db != null) {
            switch (db) {
                case SQL:
                    rbSQL.setSelected(true);
                    break;
                case MYSQL:
                    rbMYSQL.setSelected(true);
                    break;
            }
        }
    }

    public void setLang(Language lang) {
        if (lang != null) {
            switch (lang) {
                case JAVA:
                    rbJava.setSelected(true);
                    break;
                case PHP:
                    rbPHP.setSelected(true);
                    break;
                case CS:
                    rbCS.setSelected(true);
                    break;
            }
        }
    }

    public void setPath(String path) {
        txtPath.setText(path);
    }

    public String getDBType() {
        return rbgDB.getSelection().getActionCommand();
    }

    public String getLang() {
        return rbgLang.getSelection().getActionCommand();
    }

    public String getPath() {
        return txtPath.getText();
    }

    public void closePreferences() {
        parent.nextPanel(Gui.Panels.SERVER_CONNECTION_SETTINGS);
    }
}
