/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelbuilder.tables;

import java.util.Iterator;
import modelbuilder.Language;


/**
 *
 * @author david
 */
public class JavaTable extends Table { 

    
    /***
     * 
     * @param name 
     */
    public JavaTable(String name){
        super(name, "int", "Date", "String", "double", "boolean", Language.JAVA);
    }
    
    /***
     * Build table into a string
     * @return 
     */
    @Override
    public String buildTable(){        
        String tableModel = "";
        
        tableModel += importDate() + nl + nl; 
        tableModel += buildClassHeader() + nl;
        tableModel += createAttributes(); 
        tableModel += nl + nl; 
        tableModel += createGettersAndSetters();
        tableModel += toJSON(); 
        
        tableModel += nl + buildClassFooter(); 
        
        return tableModel;
    }
      
    @Override
    protected String buildClassHeader(){
        return "public class " + name + "{" + nl;   
    }
    
    @Override
    protected String buildClassFooter(){
        return nl + "}" + nl; 
    }
    
    @Override
    protected String buildAttribute(String datatype ,String identifier){
        return "\tprivate " + datatype +" " + identifier +" ;" ;
    }
    
    @Override
    protected String buildGetter(String datatype, String identifier){
        return "\tpublic " + datatype + " get" + identifier + "(){" + nl +"\t\treturn this." + identifier + ";" + nl + "\t}" + nl; 
    }
    
    @Override
    protected String buildSetter(String datatype, String identifier){
        return "\tpublic void set" + identifier + "(" + datatype + " " + identifier + "){" + nl + "\t\tthis." + identifier + " = " + identifier + ";" + nl + "\t}" + nl;
    }
    
    private String importDate(){
        return "import java.util.Date;" + nl;
    }
    
    
    @Override
    protected String toJSON(){
        String toJSON = "";
        toJSON += "\tpublic " + dataType.getVarchar() + " toJSON() {" + nl;
        toJSON += "\t\t return \"{";
        Iterator i = columns.iterator();
        while (i.hasNext()){
            TableColumn col = (TableColumn)i.next();            
            String identifier = col.getColumnName();
            toJSON += "\\\"" + identifier + "\\\":\\\"\" + " + identifier + " + \"\\\","; 
        }
        toJSON = toJSON.substring(0, toJSON.length() -1);
        
        toJSON += "}\";" + nl + "\t}";
        return toJSON;
    }
  
}
