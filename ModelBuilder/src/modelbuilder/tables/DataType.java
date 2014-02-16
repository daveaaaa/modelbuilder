package modelbuilder.tables;

/**
 * Used to convert SQL datatypes to the target langauge datatype
 * @author david
 */
public class DataType {
    public enum SQLDataType{
        INT("int"),
        DATETIME("datetime"),
        VARCHAR("varchar"),
        BIT("bit"),
        DECIMAL("decimal");       
        private final String name;
         
        SQLDataType(String name) {
            this.name = name;
        }
        
        public String getName(){
            return this.name;
        }
    }            
    
    private String integer = "";
    private String dateTime = "";
    private String varchar = "";
    private String decimal = "";
    private String bit = "";
    
    
    public DataType(String integer, String dateTime, String varchar, String decimal, String bit){
        this.integer = integer;
        this.dateTime = dateTime;
        this.varchar = varchar;
        this.decimal = decimal;
        this.bit = bit; 
    }
    
    public String getInt(){
        return integer;
    }
    
    public String getDateTime(){
        return dateTime;
    }
    
    public String getVarchar(){
        return varchar;
    }
    
    public String getDecimal(){
        return decimal;
    }
    
    public String getBit(){
        return bit; 
    }
   
}
