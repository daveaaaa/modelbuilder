/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelbuilder;

/**
 *
 * @author david
 */
public enum DataBaseType {
    SQL ("SQL"),
    MYSQL("MYSQL");
        
    private String name;
    DataBaseType(String name){
        this.name = name;
    }
    
    public String getName(){
        return name; 
    }
}
