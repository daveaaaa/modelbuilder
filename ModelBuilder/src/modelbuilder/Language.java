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
public enum Language {
    JAVA("Java", "java"),
    CS("C#", "cs"),
    PHP("PHP", "php");
    
    private String name;
    private String extension;
    Language(String name, String extension){
        this.name = name;
        this.extension = extension;       
    }
    
    public String getName(){
        return name;
    }
    
    public String getExtension(){
        return extension;
    }
    
}
