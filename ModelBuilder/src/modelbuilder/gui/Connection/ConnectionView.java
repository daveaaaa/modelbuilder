package modelbuilder.gui.Connection;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import modelbuilder.DataBaseType;

import modelbuilder.gui.Gui;

/**
 *
 * @author david
 */
public class ConnectionView extends JPanel {

    private Gui parent;
    private JLabel lblURL;
    private JTextField txtURL;
    private JLabel lblURLError;
    private JLabel lblUser;
    private JTextField txtUser;
    private JLabel lblUserError;
    private JLabel lblPassword;
    private JTextField txtPassword;
    private JLabel lblPasswordError;
    private JButton btnConnect;
    private GridBagConstraints gc;
    private ConnectionController ctrl;
    private boolean initalised;
    private JPanel formPanel;
    private JPanel dbSelectionPanel;
    private JPanel buttonPanel;
    private JRadioButton rbSQL;
    private JRadioButton rbMYSQL;
    private ButtonGroup rbgDBType;

    public ConnectionView(Gui parent) {
        this.parent = parent;
        ctrl = new ConnectionController(this, parent.getController());
        initalised = false;
        init();
    }

    public DataBaseType getSelectedDBType() {
        String actionCommand;
        DataBaseType returnEnum = null;
        actionCommand = rbgDBType.getSelection().getActionCommand();

        if (actionCommand.equals(DataBaseType.SQL.getName())) {
            returnEnum = DataBaseType.SQL;
        } else if (actionCommand.equals(DataBaseType.MYSQL.getName())) {
            returnEnum = DataBaseType.MYSQL;
        }

        return returnEnum;
    }

    public void setDefaultDBType(DataBaseType db) {
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

    public String getURLText() {
        return txtURL.getText();
    }

    public String getUserText() {
        return txtUser.getText();
    }

    public String getPasswordText() {
        return txtPassword.getText();
    }

    public void setURLText(String string) {
        this.txtURL.setText(string);
    }

    public void setUserText(String string) {
        this.txtUser.setText(string);
    }

    public void setPasswordText(String string) {
        this.txtPassword.setText(string);
    }

    public void setURLToolTipText(String string) {
        this.txtURL.setToolTipText(string);
    }

    public void setUserToolTipText(String string) {
        this.txtUser.setToolTipText(string);
    }

    public void setPasswordToolTipText(String string) {
        this.txtPassword.setToolTipText(string);
    }

    public void setUserErrorText(String string) {
        this.lblUserError.setText(string);
    }

    public void setURLErrorText(String string) {
        this.lblURLError.setText(string);
    }

    public void setPasswordErrorText(String string) {
        this.lblPasswordError.setText(string);
    }

    public void nextPanel() {
        this.parent.nextPanel(Gui.Panels.DB_SELECTION);
    }

    public void init() {
        if (initalised == false) {
            gc = setLookAndFeel();
            formPanel = new JPanel();
            formPanel.setLayout(new GridBagLayout());
            dbSelectionPanel = new JPanel();
            buttonPanel = new JPanel();
            initURL(0);
            initUser(1);
            initPassword(2);
            initDBSelection();
            initButton();

            this.setLayout(new GridLayout(3, 1));
            this.add(formPanel);
            this.add(dbSelectionPanel);
            this.add(buttonPanel);
            initalised = true;
        }
        ctrl.setDefaultDBType();
    }

    private GridBagConstraints setLookAndFeel() {
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createTitledBorder("Connection Details"));
        Dimension size = getPreferredSize();
        size.width = 200;
        size.height = 150;
        setPreferredSize(size);
        return new GridBagConstraints();
    }

    private void initURL(int y) {
        lblURL = new JLabel("DB URL:");
        txtURL = new JTextField(15);
        lblURLError = new JLabel("");
        lblURLError.setForeground(Color.red);
        setGCWeight(0.5, 0.5);
        addLabel(lblURL, y);
        addTextField(txtURL, y);
        addErrorField(lblURLError, y);
    }

    private void initUser(int y) {
        lblUser = new JLabel("Username:");
        txtUser = new JTextField(15);
        lblUserError = new JLabel("");
        lblUserError.setForeground(Color.red);
        setGCWeight(0.5, 0.5);
        addLabel(lblUser, y);
        addTextField(txtUser, y);
        addErrorField(lblUserError, y);
    }

    private void initPassword(int y) {
        lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField(15);
        lblPasswordError = new JLabel("");
        lblPasswordError.setForeground(Color.red);
        setGCWeight(0.5, 0.5);
        addLabel(lblPassword, y);
        addTextField(txtPassword, y);
        addErrorField(lblPasswordError, y);
    }

    private void initButton() {
        btnConnect = new JButton("Connect");
        btnConnect.addActionListener(ctrl);

        buttonPanel.add(btnConnect);

    }

    private void initDBSelection() {
        JPanel subPanel = new JPanel();

        rbgDBType = new ButtonGroup();
        rbSQL = new JRadioButton("SQL");
        rbMYSQL = new JRadioButton("MYSQL", true);

        rbSQL.setActionCommand(DataBaseType.SQL.getName());
        rbMYSQL.setActionCommand(DataBaseType.MYSQL.getName());

        rbgDBType.add(rbSQL);
        rbgDBType.add(rbMYSQL);
        subPanel.setLayout(new GridLayout(1, 2));
        subPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(5), "Type"));
        subPanel.add(rbSQL);
        subPanel.add(rbMYSQL);

        dbSelectionPanel.add(subPanel);
    }

    private void addLabel(JLabel label, int y) {
        int COL1 = 0;
        setGC(COL1, y);
        gc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(label, gc);
    }

    private void addTextField(JTextField txtfield, int y) {
        int COL2 = 1;
        setGC(COL2, y);
        formPanel.add(txtfield, gc);
    }

    private void addErrorField(JLabel label, int y) {
        int col3 = 2;
        setGC(col3, y);
        gc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(label, gc);
    }

    private void setGC(int x, int y) {
        gc.gridx = x;
        gc.gridy = y;
    }

    private void setGCWeight(double x, double y) {
        gc.weightx = x;
        gc.weighty = y;
    }
}
