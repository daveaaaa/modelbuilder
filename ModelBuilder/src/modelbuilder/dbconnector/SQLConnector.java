/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelbuilder.dbconnector;

import java.sql.Connection;
import java.sql.SQLException;
import modelbuilder.DataBaseType;
import modelbuilder.tables.Table;
/**
 *
 * @author david
 */
public class SQLConnector extends DBConnector {

    
    public SQLConnector(String url, String password, String user) throws SQLException {
        super(url, password, user, DataBaseType.SQL);
    }

    @Override
    public Table executeProcedure(String procedureName, Table table) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Table executeStatement(String statementString, Table table) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String[] executeStatement(String statementString) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String[] getAvailableDBs() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String[] getAvailableTables(String selectedDB) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected Table generateTable(String command, Table table) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected Connection connect() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected String addDriver(String url) {
        String driver = "jdbc:sqlserver://";
        if (url.contains(driver)) {
            return url;
        } else {
            return driver + url;
        }
    }
}
