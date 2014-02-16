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
public class TableFactorySQL extends TableFactory{

    protected TableFactorySQL(SQLConnector db){
        super(db);
    }
    
    @Override
    protected Table GetTable(String dbName, String tableName, Table table) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
