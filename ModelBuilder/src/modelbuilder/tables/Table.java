/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelbuilder.tables;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import modelbuilder.Language;

/**
 *
 * @author david
 */
public abstract class Table {
    
    protected final static String nl = "\n"; 
  
    protected String name;    
    protected String integer;
    protected String dateTime;
    protected String varchar;
    protected String decimal;
    protected String bit;
    protected Language fileExtension;
    
    protected List<TableColumn> columns; 
    protected DataType dataType;
        
    protected Table(String name, String integer, String dateTime, String varchar, String decimal, String bit, Language fileExtension){
        this.name = name;
        this.integer = integer; 
        this.dateTime = dateTime;
        this.varchar = varchar;
        this.decimal = decimal;
        this.bit =bit;
        this.fileExtension = fileExtension; 
        columns = new ArrayList<TableColumn>();
        dataType = new DataType(integer,dateTime,varchar,decimal,bit);
    }
    
    
    public String getExtension(){
        return fileExtension.getExtension(); 
    }
    
    public String getTableName(){
        return this.name;
    }
    
    public void setTableName(String name){
        this.name = name;
    }

    
    /***
     * 
     * @param col 
     */
    public void addColumn(TableColumn col){
        columns.add(col); 
    }
    
    public abstract String buildTable(); 
    
        /***
     * Creates private attributes
     * @return 
     */
    protected String createAttributes(){
        String tableModel = "";
        Iterator i = columns.iterator(); 
        
        while(i.hasNext()){
            TableColumn col = (TableColumn)i.next();
            String datatype = getDataType(col.getDataType());
            String identifier = col.getColumnName();            
            tableModel += buildAttribute(datatype, identifier) + nl;
        }
        
        return tableModel;                 
    }
    
    /***
     * Creates getters and setters
     * @return 
     */
    protected String createGettersAndSetters(){
        String tableModel = "";
        Iterator i = columns.iterator();
        
        while (i.hasNext()) {
            TableColumn col = (TableColumn)i.next();
            String datatype = getDataType(col.getDataType());
            String identifier = col.getColumnName();            

            tableModel += buildGetter(datatype, identifier) + nl;
            tableModel += buildSetter(datatype, identifier) + nl; 
        }
        return tableModel; 
    }
    
    protected abstract String buildClassHeader(); 
    protected abstract String buildClassFooter();
    protected abstract String buildAttribute(String datatype, String identifier); 
    protected abstract String buildSetter(String datatype, String identifier);
    protected abstract String buildGetter(String datatype, String identifier);
    
    
      
    private String getDataType(String dataFieldType){
        String returnString = "";
        
        
        if(isDataType(dataFieldType, DataType.SQLDataType.BIT)){
            returnString = dataType.getBit();
        }
        if(isDataType(dataFieldType, DataType.SQLDataType.DATETIME)){
            returnString = dataType.getDateTime();
        }
        if(isDataType(dataFieldType, DataType.SQLDataType.DECIMAL)){
            returnString = dataType.getDecimal();
        }        
        if(isDataType(dataFieldType, DataType.SQLDataType.INT)){
            returnString = dataType.getInt();
        }        
        if(isDataType(dataFieldType, DataType.SQLDataType.VARCHAR)){
            returnString = dataType.getVarchar();
        }
        
        return returnString; 
    }
    
    private boolean isDataType(String dataType, DataType.SQLDataType dt){
        boolean returnBool = false;
            if(dataType.equals(dt.getName())){
                returnBool = true; 
            }
        return returnBool;
    }
    
    protected abstract String toJSON(); 
}

