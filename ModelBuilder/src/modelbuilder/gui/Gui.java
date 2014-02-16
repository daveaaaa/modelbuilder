/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelbuilder.gui;

import modelbuilder.gui.MenuBar.MenuBar;
import modelbuilder.gui.Connection.ConnectionView;
import modelbuilder.gui.TableSelector.TableSelectorView;
import modelbuilder.gui.DBSelector.DBSelectorView;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JFrame;

import javax.swing.JPanel;
import modelbuilder.gui.Preferences.PreferencesView;


/**
 *
 * @author david
 */
public class Gui extends JFrame implements Runnable {

    private MenuBar menuBar;
    private ConnectionView pnlConnection;
    private DBSelectorView pnlDataBaseSelection;
    private TableSelectorView pnlTable;
    private PreferencesView pnlPreferences; 
    private JPanel cards; 
    private Container container;
    private Controller ctrl; 
    private final String version;
    
    public enum Panels{
        SERVER_CONNECTION_SETTINGS,
        DB_SELECTION,
        PREFERENCES,
        TABLE_SELECTION;
    }
    
    public Gui(String version) {
        this.version = version;
    }
    
    public String getVersion(){
        return version; 
    }

    private void initCompolents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);        
        menuBar = new MenuBar(this);
        
        ctrl = new Controller(); 
        
        pnlConnection = new ConnectionView(this); 
        pnlDataBaseSelection = new DBSelectorView(this);
        pnlTable = new TableSelectorView(this); 
        
        pnlPreferences = new PreferencesView(this);
        
        container = getContentPane();
        
        container.setLayout(new BorderLayout());
        
        container.add(menuBar, BorderLayout.NORTH); 
        
        cards=new JPanel( new CardLayout()); 
        
        
        cards.add(pnlConnection, Panels.SERVER_CONNECTION_SETTINGS.name()); 
        cards.add(pnlDataBaseSelection, Panels.DB_SELECTION.name()); 
        cards.add(pnlTable, Panels.TABLE_SELECTION.name());
        cards.add(pnlPreferences, Panels.PREFERENCES.name());
        
        container.add(cards, BorderLayout.CENTER);
        
    }

    private void setLookAndFeel() {
        this.setSize(300, 300);
        setLocationRelativeTo(null);
    }

    private void setLabels() {
        setTitle("Model Builder " + version);
    }

    ;

    @Override
    public void run() {
        initCompolents();
        setLookAndFeel();
        setLabels();
        this.setVisible(true);
    }

    public void nextPanel(Panels nextPanel) {
       CardLayout cl = (CardLayout) (cards.getLayout());
       
       cl.show(cards, nextPanel.name());
       switch (nextPanel){
           case DB_SELECTION:
               pnlDataBaseSelection.init(); 
               break;
           case TABLE_SELECTION:
               pnlTable.init(); 
               break;
           case SERVER_CONNECTION_SETTINGS:
               pnlConnection.init();
               break;
           case PREFERENCES:
               pnlPreferences.init();
               break;
       }
    }
    
    public Controller getController(){
        return ctrl; 
    }

}
