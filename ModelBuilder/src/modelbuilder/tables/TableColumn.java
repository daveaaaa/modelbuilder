/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelbuilder.tables;

/**
 *
 * @author david
 */
public class TableColumn {

    public static enum Columns {
        COLUMN_NAME(1),
        DATA_TYPE(2),
        IS_NULLABLE(3),
        CHARACTER_MAXIMUM_LENGHT(4),
        NUMERIC_PRECISION(5),
        NUMERIC_SCALE(6);
        private final int value;

        Columns(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    private String columnName;
    private String dataType;
    private String isNullable;
    private String lenght;
    private String precision;
    private String scale;

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getLenght() {
        return lenght;
    }

    public void setLenght(String lenght) {
        this.lenght = lenght;
    }   
    
}
