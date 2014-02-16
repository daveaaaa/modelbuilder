/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelbuilder.tables;


import modelbuilder.dbconnector.MYSQLConnector;

/**
 *
 * @author david
 */
public class TableFactoryMYSQL extends TableFactory {

    protected TableFactoryMYSQL(MYSQLConnector db) {
        super(db);
    }

    @Override
    protected Table GetTable(String dbName, String tableName, Table table) throws java.sql.SQLException {
        String mysql_statement;

        mysql_statement = String.format("SELECT COLUMN_NAME,DATA_TYPE,IS_NULLABLE,CHARACTER_MAXIMUM_LENGTH,NUMERIC_PRECISION,NUMERIC_SCALE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '%s' AND   Lower(TABLE_NAME) = Lower('%s');", dbName, tableName);

        table = db.executeStatement(mysql_statement, table);

        return table;
    }
}
