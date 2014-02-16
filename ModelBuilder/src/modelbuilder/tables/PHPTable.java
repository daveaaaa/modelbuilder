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
public class PHPTable extends Table {

    public PHPTable(String name) {
        super(name, "int", "DateTime", "string", "float", "bool", Language.PHP);
    }

    @Override
    public String buildTable() {
        String tableModel = buildClassHeader() + "\n";
        tableModel += createAttributes() + "\n\n";
        tableModel += createGettersAndSetters();
        tableModel += buildClassFooter() + "\n";

        return tableModel;
    }

    @Override
    protected String buildClassHeader() {
        return "class " + name + " { \n";
    }

    @Override
    protected String buildClassFooter() {
        return "}";
    }

    @Override
    protected String buildAttribute(String datatype, String identifier) {
        return "\tprivate $" + identifier + ";" + "\t\t\\\\ " + datatype;
    }

    @Override
    protected String buildSetter(String datatype, String identifier) {
        String returnString = "\tpublic function set" + identifier + "($" + identifier + ") {\n";

        if (datatype.equals(dateTime)) {
            returnString += "\t\tif($" + identifier + " instanceof " + datatype + "){\n"; 
            returnString += "\t\t\t$this->" + identifier + " = $" + identifier + ";\n";
            returnString += "\t\t}\n";
        } else {
            returnString += "\t\tif(is_" + datatype + "($" + identifier + ")){ \n";
            returnString += "\t\t\t$this->" + identifier + " = (" + datatype + ")$" + identifier + ";\n";
            returnString += "\t\t}\n";
        }
        returnString += "\t}\n";
        return returnString;
    }

    @Override
    protected String buildGetter(String datatype, String identifier) {
        String returnString = "\tpublic function get" + identifier + "() {\n";
        returnString += "\t\treturn $this->" + identifier + ";\n";
        returnString += "\t}\n";
        return returnString;
    }

    @Override
    protected String toJSON() {
        return "";
    }

}
