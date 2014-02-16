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
public class SQLConnector extends DBConnector {

    public SQLConnector(String url, String password, String user) throws SQLException {
        super(url, password, user, DataBaseType.SQL);
    }

    @Override
    public Table executeProcedure(String procedureName, Table table) throws SQLException {
        try {
            table = generateTable("exec " + procedureName, table);
        } finally {
        }
        return table;
    }

    @Override
    public Table executeStatement(String statementString, Table table) throws SQLException {
        try {
            table = generateTable(statementString, table);
        } finally {
        }
        return table;
    }

    @Override
    public String[] executeStatement(String statementString) throws SQLException {

        ResultSet resultSet = null;
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
    public String[] getAvailableDBs() throws SQLException {
        String dbStatement = " SELECT name ";
        dbStatement += " FROM sys.databases ";
        dbStatement += " WHERE name NOT IN ('master', 'tempdb', 'model', 'msdb')";
        return executeStatement(dbStatement);
    }

    @Override
    public String[] getAvailableTables(String selectedDB) throws SQLException {
        String dbStatement = " USE " + selectedDB;
        dbStatement += " GO ";
        dbStatement += " SELECT TABLE_NAME ";
        dbStatement += " FROM sys.Tables";
        dbStatement += " GO ";

        return executeStatement(dbStatement);
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

    @Override
    protected Connection connect() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
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
        String driver = "jdbc:sqlserver://";
        if (url.contains(driver)) {
            return url;
        } else {
            return driver + url;
        }
    }
}
