package modelbuilder;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
/**
 *
 * @author david
 */
public class FilePrinter {
    
    private File file;
    private Writer writer;
    private String path;
    
    public FilePrinter(String path){
        this.path = pathSorting(path); 
    }
    
    public void setPath(String path){
        this.path = path; 
    }
    
    /***
     * Creates a new file in the setup path
     * @param fileName
     * @param extension
     * @param fileContents
     * @return  file created and writen
     */
    public boolean createFile(String fileName,  String extension, String fileContents){
        boolean returnBool = false;
        try{
            
            openFile(fileName, extension);
            
            writer = createFileWriter();
            
            writeToFile(fileContents);
            
            closeWriter();
            
            returnBool = true; 
        } catch (IOException ex){            
            returnBool  = false;
        } finally {
            writer = null; 
            file = null; 
        }
        
        return returnBool;
    }
    
    private void openFile(String fileName, String extension) throws IOException {
        file = new File(path + "//" + fileName + "." + extension); 
        file.createNewFile();
    }
    
    private BufferedWriter createFileWriter() throws IOException {

        FileWriter fileWriter;        
        fileWriter = new FileWriter(file);        
        return new BufferedWriter(fileWriter); 
    }
    
    private void writeToFile(String fileContents) throws IOException {
        writer.write(fileContents, 0, fileContents.length());
    }
    
    private void closeWriter() throws IOException{
        writer.close();
    }
    
    
    private String pathSorting(String path){
        return path;
    }
}
