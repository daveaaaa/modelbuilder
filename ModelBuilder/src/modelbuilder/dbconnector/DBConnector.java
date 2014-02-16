/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelbuilder.dbconnector;

import modelbuilder.tables.Table;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import modelbuilder.DataBaseType;

/**
 *
 * @author david
 */
public abstract class DBConnector {

    protected String url;
    protected String password;
    protected String user;
    protected Connection conn;
    protected Statement statement;
    protected DataBaseType dbType;   
    
    protected DBConnector(String url, String password, String user, DataBaseType dbType) throws SQLException{
        this.url = addDriver(url);
        this.password = password;
        this.user = user;
        this.dbType = dbType;
        
        testConnection(); 
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public abstract Table executeProcedure(String procedureName, Table table) throws SQLException;

    public abstract Table executeStatement(String statementString, Table table) throws SQLException;

    public abstract String[] executeStatement(String statementString) throws SQLException;

    public abstract String[] getAvailableDBs() throws SQLException;

    public abstract String[] getAvailableTables(String selectedDB) throws SQLException;

    protected abstract String addDriver(String url);

    /***
     * Generates a table to create a data model for
     * @param command
     * @return
     * @throws SQLException 
     */
    protected abstract Table generateTable(String command, Table table) throws SQLException;

    protected abstract Connection connect() throws SQLException;

    /***
     * Cleans up connections
     */
    protected void cleanUp() {

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ignore) {
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
        statement = null;
        conn = null;
    }

    private void testConnection() throws SQLException {
        try {
            connect();
        } finally {
            cleanUp();
        }
    }
}
