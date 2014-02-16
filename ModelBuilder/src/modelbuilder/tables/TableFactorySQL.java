/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelbuilder.tables;

import java.sql.SQLException;
import modelbuilder.dbconnector.SQLConnector;

/**
 *
 * @author david
 */
public class TableFactorySQL extends TableFactory {

    protected TableFactorySQL(SQLConnector db) {
        super(db);
    }

    @Override
    protected Table GetTable(String dbName, String tableName, Table table) throws SQLException {
        String sql_statement = "USE " + dbName + " GO ";
               sql_statement += " SELECT c.name";
               sql_statement += " ,dt.name";
               sql_statement += " ,c.is_nullable";
               sql_statement += " ,c.max_length";
               sql_statement += " ,c.precision";
               sql_statement += " ,c.scale"; 
               sql_statement += " FROM sys.columns c";
               sql_statement += " INNER JOIN sys.types dt ON c.system_type_id = dt.system_type_id";
               sql_statement += " INNER JOIN sys.Tables t on c.object_id = t.object_id"; 
               sql_statement += " WHERE t.name = " + tableName;
               sql_statement += " GO "; 
        
        table = db.executeStatement(sql_statement, table);

        return table;
    }

}
