/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh;

import io.github.theguy191919.uhhuh.io.FileReader;
import io.github.theguy191919.uhhuh.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author evan__000
 */
public class Options {
    
    private Properties properties;
    private String fileLocation = System.getProperty("user.dir") + File.separator + "config.properties";
    private FileReader reader;
    private FileWriter writer;
    private Map<String, String> mapOfProperties = new LinkedHashMap<>();
    
    public Options(){
        try {
            this.reader = new FileReader(this.fileLocation);
            this.writer = new FileWriter(this.fileLocation, true);
            this.properties = new Properties();
            this.properties.load(this.reader.getInputStream());
            
            Iterator iter = this.properties.entrySet().iterator();
            while(iter.hasNext()){
                Entry entry = (Entry)iter.next();
                this.mapOfProperties.put((String)entry.getKey(), (String)entry.getValue());
            }
            
            //this.properties.store(null, fileLocation);
        } catch (IOException ex) {
            Logger.getLogger(Options.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getProperty(String property){
        if(!this.mapOfProperties.containsKey(property)){
            this.mapOfProperties.put(property, "");
        }
        return this.mapOfProperties.get(property);
    }
    
    public void setProperty(String property, String value){
        this.mapOfProperties.put(property, value);
    }
    
    public void closeProperties(){
        this.properties.putAll(this.mapOfProperties);
        try {
            this.properties.store(this.writer.getOutputStream(), "Properties For Uh huh, updated on " + System.currentTimeMillis());
            this.reader.getInputStream().close();
            this.writer.getOutputStream().flush();
            //this.writer.getOutputStream().close();
        } catch (IOException ex) {
            Logger.getLogger(Options.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
