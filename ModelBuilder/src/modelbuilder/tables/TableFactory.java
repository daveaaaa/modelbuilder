/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelbuilder.tables;

import java.sql.SQLException;
import modelbuilder.DataBaseType;
import modelbuilder.dbconnector.DBConnector;
import modelbuilder.dbconnector.MYSQLConnector;
import modelbuilder.dbconnector.SQLConnector;

/**
 * Used to create the correct table depending upon language
 *
 * @author david
 */
public abstract class TableFactory {

    DBConnector db;

    protected TableFactory(DBConnector db) {
        this.db = db;
    }

    public static TableFactory getTableFactory(DBConnector db) {
        TableFactory newFactory = null;

        if (db instanceof MYSQLConnector) {
            newFactory = new TableFactoryMYSQL((MYSQLConnector) db);
        } else if (db instanceof SQLConnector) {
            newFactory = new TableFactorySQL((SQLConnector) db);
        }

        return newFactory;
    }

    public CSTable CSTable(String dbName, String tableName) throws java.sql.SQLException {
        CSTable tbl;

        tbl = new CSTable(tableName);
        tbl = (CSTable) GetTable(dbName, tableName, tbl);
        tbl.setTableName(tableName);

        return tbl;
    }

    public JavaTable JavaTable(String dbName, String tableName) throws java.sql.SQLException {
        JavaTable tbl;

        tbl = new JavaTable(tableName);
        tbl = (JavaTable) GetTable(dbName, tableName, tbl);
        tbl.setTableName(tableName);

        return tbl;
    }

    public Table PHPTable(String dbName, String tableName) throws java.sql.SQLException {
        PHPTable tbl;

        tbl = new PHPTable(tableName);
        tbl = (PHPTable) GetTable(dbName, tableName, tbl);
        tbl.setTableName(tableName);

        return tbl;
    }

    protected abstract Table GetTable(String dbName, String tableName, Table table) throws java.sql.SQLException;
}
