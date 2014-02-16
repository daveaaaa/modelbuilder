/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelbuilder.dbconnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelbuilder.DataBaseType;
import modelbuilder.tables.Table;
import modelbuilder.tables.TableColumn;

/**
 *
 * @author david
 */
public class MYSQLConnector extends DBConnector {

    /***
     * 
     * @param url database to connect to 
     * @param password password 
     * @param user user
     * @throws java.sql.SQLException
     */
    public MYSQLConnector(String url, String password, String user) throws SQLException {
        super(url, password, user, DataBaseType.MYSQL);
    }

    /***
     * Name of procedure will automatically put in call and ;
     * @param procedureName
     * @param table
     * @return
     * @throws SQLException 
     */
    @Override
    public Table executeProcedure(String procedureName, Table table) throws SQLException {

        try {
            table = generateTable("Call " + procedureName + ";", table);
        } finally {
        }
        return table;
    }

    /***
     * SQL string
     * @param statementString
     * @param table
     * @return
     * @throws SQLException 
     */
    @Override
    public Table executeStatement(String statementString, Table table) throws SQLException {
        try {
            table = generateTable(statementString + ";", table);
        } finally {
        }
        return table;
    }

    @Override
    public String[] executeStatement(String statementString) throws SQLException {
        ResultSet resultSet = null;
        //ResultSetMetaData rsmd = null;
        String[] returnArray;
        ArrayList<String> results = new ArrayList<String>();
        try {
            conn = connect();
            statement = conn.createStatement();
            resultSet = statement.executeQuery(statementString);

            while (resultSet.next()) {
                results.add(resultSet.getString(1));
            }
            returnArray = new String[results.size()];

            results.toArray(returnArray);

            /*
            rsmd = resultSet.getMetaData();
            
            int numOfCol = rsmd.getColumnCount();
            for (int i = 1; i <= rsmd.getColumnCount(); i++){
            System.out.print(rsmd.getColumnName(i));
            System.out.print(" ");
            System.out.print(rsmd.getColumnTypeName(i));
            System.out.println("");
            }
            
            while (resultSet.next()){
            for (int i = 1; i <= numOfCol; i++){
            System.out.print(resultSet.getString(i) + " ");
            }
            System.out.println("");
            }*/
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ignore) {
                }
            }
            cleanUp();
        }
        
        return returnArray;
    }

    @Override
    protected Table generateTable(String command, Table table) throws SQLException {

        TableColumn col;
        ResultSet resultSet = null;
        try {
            conn = connect();
            statement = conn.createStatement();
            resultSet = statement.executeQuery(command);

            while (resultSet.next()) {
                col = new TableColumn();

                col.setColumnName(resultSet.getString(TableColumn.Columns.COLUMN_NAME.getValue()));
                col.setDataType(resultSet.getString(TableColumn.Columns.DATA_TYPE.getValue()));
                col.setIsNullable(resultSet.getString(TableColumn.Columns.IS_NULLABLE.getValue()));
                col.setLenght(resultSet.getString(TableColumn.Columns.CHARACTER_MAXIMUM_LENGHT.getValue()));
                col.setPrecision(resultSet.getString(TableColumn.Columns.NUMERIC_PRECISION.getValue()));
                col.setScale(resultSet.getString(TableColumn.Columns.NUMERIC_SCALE.getValue()));

                table.addColumn(col);
            }
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ignore) {
                }
            }
            cleanUp();
        }
        return table;
    }

    /***
     * Trys to make a DB connection
     * @return 
     * @throws SQLException 
     */
    @Override
    protected Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            return DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            if (!(ex instanceof SQLException)) {
                System.err.printf(ex.getMessage());
                return null;
            } else {
                throw (SQLException) ex;
            }
        }
    }

    @Override
    protected String addDriver(String url) {
        String driver = "jdbc:mysql://";
        if (url.contains(driver)) {
            return url;
        } else {
            return driver + url;
        }
    }

    @Override
    public String[] getAvailableDBs() throws SQLException {
        String dbStatement = "SELECT distinct TABLE_SCHEMA FROM INFORMATION_SCHEMA.Tables"
                + " WHERE TABLE_SCHEMA != 'performance_schema' "
                + " AND TABLE_SCHEMA != 'mysql' "
                + " AND TABLE_SCHEMA != 'information_schema';";
        return executeStatement(dbStatement);
    }

    @Override
    public String[] getAvailableTables(String selectedDB) throws SQLException {
        String tableStatement = "SELECT  distinct TABLE_NAME FROM INFORMATION_SCHEMA.Columns "
                + " WHERE TABLE_SCHEMA = '" + selectedDB + "'";
        return executeStatement(tableStatement);

    }
    
}
