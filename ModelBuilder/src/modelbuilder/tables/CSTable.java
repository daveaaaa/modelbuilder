/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelbuilder.tables;

import modelbuilder.Language;

/**
 *
 * @author david
 */
public class CSTable extends Table{
    
    public CSTable(String name){
         super(name, "int", "Date", "String", "double", "boolean", Language.CS);
    }
    
    @Override
    public String buildTable() {
        return "";
    }

    
    @Override
    protected String buildClassHeader() {
        return "public class " + name + " {\n";
    }

    @Override
    protected String buildClassFooter() {
        return "}";
    }
    
    @Override
    protected String buildAttribute(String datatype, String identifier) {
        return "";
    }

    @Override
    protected String buildSetter(String datatype, String identifier) {
        return "";
    }

    @Override
    protected String buildGetter(String datatype, String identifier) {
        return ""; 
    }

    @Override
    protected String toJSON() {
        return ""; 
    }

    
}
