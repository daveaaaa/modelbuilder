/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelbuilder.gui.MenuBar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import modelbuilder.gui.Gui;

/**
 *
 * @author david
 */
public class MenuBar extends JMenuBar {

    private JMenu menuFile;
    private JMenu menuAbout;
    private MenuBarController ctrl;
    private Gui parent;

    public MenuBar(Gui parent) {
        this.parent = parent;

        ctrl = new MenuBarController(this, parent.getController());
        init();
    }

    public String getVersion() {
        return parent.getVersion();
    }

    public void showPreferences(){
        parent.nextPanel(Gui.Panels.PREFERENCES);
    }
    
    private void init() {
        initMenuFile();
        initMenuAbout();
    }

    private void initMenuFile() {
        menuFile = new JMenu("File");
        menuFile.setMnemonic('f');

        JMenuItem preferences = new JMenuItem("Preferences");
        preferences.setActionCommand("preferences");
        preferences.addActionListener(ctrl);
        preferences.setMnemonic('p');

        JMenuItem exit = new JMenuItem("Exit");
        exit.setActionCommand("exit");
        exit.addActionListener(ctrl);
        exit.setMnemonic('x');

        menuFile.add(preferences);
        menuFile.addSeparator();
        menuFile.add(exit);

        this.add(menuFile);
    }

    private void initMenuAbout() {
        menuAbout = new JMenu("About");
        menuAbout.setMnemonic('a');

        JMenuItem about = new JMenuItem("About");
        about.setMnemonic('a');
        about.setActionCommand("about");
        about.addActionListener(ctrl);

        menuAbout.add(about);
        this.add(menuAbout);
    }

}
