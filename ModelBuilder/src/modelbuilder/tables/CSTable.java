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
public class CSTable extends Table {

    public CSTable(String name) {
        super(name, "int", "Date", "String", "double", "boolean", Language.CS);
    }

    @Override
    public String buildTable() {
        String tableModel = buildClassHeader() + "\n";
        tableModel += createAttributes();
        tableModel += buildClassFooter();
        return tableModel;
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
        String smallIdentifier = identifier.substring(0, 1).toLowerCase() + identifier.substring(1, identifier.length());

        String returnString = "\tprivate " + datatype + " _" + smallIdentifier + ";\n";
        returnString += "\tpublic " + datatype + " " + identifier + " {\n";
        returnString += "\t" + addCSGetter(datatype, smallIdentifier) + "\n";
        returnString += "\t" + addCSSetter(datatype, smallIdentifier) + "\n";
        returnString += "\t}\n";
        return returnString;
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

    private String addCSSetter(String datatype, String identifier) {
        String returnString = "\tset { this._" + identifier + " = value; }";
        return returnString;
    }

    private String addCSGetter(String datatype, String identifier) {
        String returnString = "\tget { return this._" + identifier + " ; }";
        return returnString;
    }

}
