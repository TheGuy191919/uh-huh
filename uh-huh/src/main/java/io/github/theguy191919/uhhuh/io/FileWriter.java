/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.io;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author evan__000
 */
public class FileWriter {
    
    private OutputStream outputStream;
    private String fileLocation;
    private File file;
    private boolean replaceFile = false;

    public FileWriter(String fileLocation){
        this.setFileLocation(fileLocation);
    }
    
    public FileWriter(String fileLocation, boolean replaceFile){
        this.replaceFile = replaceFile;
        this.setFileLocation(fileLocation);
    }
    
    /**
     * @return the outputStream
     */
    public OutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * @return the fileLocation
     */
    public String getFileLocation() {
        return fileLocation;
    }

    /**
     * @param fileLocation the fileLocation to set
     */
    public void setFileLocation(String fileLocation) {
        
        this.fileLocation = fileLocation;
        this.file = new File(this.fileLocation);
        
        if(!this.replaceFile){
            if(this.file.isFile()){
                this.fileLocation = this.fileLocation + "-1";
                this.file = new File(this.fileLocation);
            }
            while(this.file.isFile()){
                this.fileLocation = this.fileLocation.substring(0, this.fileLocation.length() - 2);
                this.file = new File(this.fileLocation);
            }
        }
        
        if(!this.file.isFile()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(FileWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            this.outputStream = new FileOutputStream(this.file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }
    
}
