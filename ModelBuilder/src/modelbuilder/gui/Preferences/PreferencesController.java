package modelbuilder.gui.Preferences;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelbuilder.ModelBuilder;
import modelbuilder.gui.Controller;

/**
 *
 * @author david
 */
public class PreferencesController implements ActionListener{
    
    private PreferencesView view;
    private Controller ctrl; 
    
    public PreferencesController(PreferencesView view, Controller controller){
        this.view = view;
        this.ctrl = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if(command.contains("save")){
            savePreferences();
            closePreferences();
        } else if(command.contains("cancel")){
            closePreferences();
        }
    }
    
    public void getPreferences(){
        view.setDBType(ctrl.getDefaultDBType());
        view.setLang(ctrl.getDefaultLanguage());
        view.setPath(ctrl.getDefaultPath());
    }
    
    private void savePreferences(){
        String dbType = view.getDBType();
        String lang = view.getLang();
        String path = view.getPath();
                
        ctrl.createConfigFile(lang, dbType, path);
    }
    
    private void closePreferences(){
        view.closePreferences(); 
    }
}
