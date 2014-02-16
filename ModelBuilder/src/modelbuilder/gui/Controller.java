/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelbuilder.gui;

import java.sql.SQLException;
import modelbuilder.DataBaseType;
import modelbuilder.FilePrinter;
import modelbuilder.Language;
import modelbuilder.ModelBuilder;
import modelbuilder.dbconnector.DBConnector;
import modelbuilder.dbconnector.MYSQLConnector;
import modelbuilder.dbconnector.SQLConnector;
import modelbuilder.tables.Table;
import modelbuilder.tables.TableFactory;

/**
 *
 * @author david
 */
public class Controller {

    private DBConnector db;
    private TableFactory tableFactory;
    private FilePrinter filePrinter;
    private String selectedDB;
    private DataBaseType DBtype;

    public Controller() {
        filePrinter = new FilePrinter("");
    }

    public void initDBConnector(String url, String password, String user) throws SQLException {
        try {
            switch (DBtype) {
                case SQL:
                    this.db = new SQLConnector(url, password, user);
                    break;
                case MYSQL:
                    this.db = new MYSQLConnector(url, password, user);
                    break;
            }
            createTableFactory();
        } finally {
        }

    }

    public void setPath(String path) {
        filePrinter.setPath(path);
    }

    private void createTableFactory() {
        this.tableFactory = TableFactory.getTableFactory(db);
    }

    public void setDBType(DataBaseType type) {
        this.DBtype = type;
    }

    public void setSelectedDB(String selectedDB) {
        this.selectedDB = selectedDB;
    }

    public String[] getAvailableDBs() throws SQLException {
        return db.getAvailableDBs();
    }

    public String[] getAvaliableTables() throws SQLException, NullPointerException {
        return db.getAvailableTables(selectedDB);
    }

    public void createJavaTable(String tableName) throws SQLException, NullPointerException {

        Table table = null;
        try {
            table = tableFactory.JavaTable(selectedDB, tableName);
            filePrinter.createFile(table.getTableName(), table.getExtension(), table.buildTable());
        } finally {
        }
    }

    public void createCSTable(String tableName) throws SQLException, NullPointerException {

        Table table = null;
        try {
            table = tableFactory.CSTable(selectedDB, tableName);
            filePrinter.createFile(table.getTableName(), table.getExtension(), table.buildTable());
        } finally {
        }
    }

    public void createPHPTable(String tableName) throws SQLException, NullPointerException {
        Table table = null;
        try {
            table = tableFactory.PHPTable(selectedDB, tableName);
            filePrinter.createFile(table.getTableName(), table.getExtension(), table.buildTable());
        } finally {
        }
    }

    public Language getDefaultLanguage() {
        Language language = null;
        String lang = ModelBuilder.getDefaultLanguage();
        lang = lang.toLowerCase();

        if (lang.equals(Language.CS.getName().toLowerCase())) {
            language = Language.CS;
        } else if (lang.equals(Language.JAVA.getName().toLowerCase())) {
            language = Language.JAVA;
        } else if (lang.equals(Language.PHP.getName().toLowerCase())) {
            language = Language.PHP;
        }
        
        return language;
    }

    public DataBaseType getDefaultDBType() {
        String dbType = ModelBuilder.getDefaultDBType();
        DataBaseType type = null;
        dbType = dbType.toLowerCase(); 
        if(dbType.equals(DataBaseType.SQL.getName().toLowerCase())){
            type = DataBaseType.SQL;
        } else if (dbType.equals(DataBaseType.MYSQL.getName().toLowerCase())){
            type = DataBaseType.MYSQL;
        }
    
        return type; 
    }

    public String getDefaultPath() {
        return ModelBuilder.getDefaultPath();
    }

    public void createConfigFile(String language, String dataBase, String path) {
        ModelBuilder.createConfigFile(language, dataBase, path);
    }
}
