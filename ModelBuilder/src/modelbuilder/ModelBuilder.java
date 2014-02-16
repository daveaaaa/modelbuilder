package modelbuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import modelbuilder.gui.Gui;

/**
 *
 * @author david
 */
public class ModelBuilder {

    /**
     * @param args the command line arguments
     */
    private static Properties properties;
    private static final String configFile = "config.properties";
    private static final String version = "1.1";
    private static final String propertyLang = "defaultLang";
    private static final String propertyDB = "defaultDB";
    private static final String propertyPath = "defaultPath";

    public static void main(String[] args) {
        try {
            init();
            new Gui(version).run();
        } catch (IOException ioEx) {
            //no default properties file found
            createConfigFile("", "", "");
            new Gui(version).run();
        }
    }

    private static void init() throws IOException {
        FileReader file;
        try {
            properties = new Properties();

            file = new FileReader(configFile);

            properties.load(file);

            file.close();
        } finally {
        }

    }

    public static void createConfigFile(String lang, String DBType, String path) {
        FilePrinter fp = new FilePrinter("./");
        fp.createFile("config", "properties", propertyLang + "=" + lang + "\n" + propertyDB + "=" + DBType + "\n" + propertyPath + "=" + path + "\n");
        try {
            init();
        } catch (IOException ioEx) {
        }
    }

    public static String getDefaultDBType() {
        String propertyString;

        propertyString = properties.getProperty(propertyDB);
      
        return propertyString;
    }

    public static String getDefaultLanguage() {
        String propertyString;
        
        propertyString = properties.getProperty(propertyLang);
        
        return propertyString;
    }

    public static String getDefaultPath() {
        String pathString = null;

        pathString = properties.getProperty(propertyPath);

        return pathString;
    }
}
