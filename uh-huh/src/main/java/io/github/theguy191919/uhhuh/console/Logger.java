/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.console;

import io.github.theguy191919.uhhuh.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

/**
 * Where it writes to console and possibly the log files
 * @author evan__000
 */
public class Logger {
    
    private String fileLocation = System.getProperty("user.dir") +File.separator + "logs" + File.separator + "log" + this.getFormatedTime("yyyyMMddHHmmss") + ".log";
    private FileWriter writer = new FileWriter(fileLocation, false);
    
    //3 logs all
    //2 logs serious
    //1 logs error only
    public int logLevel = 3;
    private String[] levelName = {
        "HOW", "HIGH", "MEDIUM", "LOW", "DEBUG"
    };
    
    public Logger(){
        
    }
    
    public Logger(int logLevel){
        this.logLevel = logLevel;
    }
    
    public void setLogLevel(int logLevel){
        this.logLevel = logLevel;
    }
    
    private String getFormatedTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
    }
    
    private String getFormatedTime(String format){
        return new SimpleDateFormat(format).format(new Date(System.currentTimeMillis()));
    }
    
    /*
    * Presistant log, will be written to file
    */
    public void log(String message, Object object){
        int logLevel = 3;
        try {
            writer.getOutputStream().write((this.getFormatedTime() + ": " + object.getClass().getName() + "[" + levelName[logLevel] + "]: " + message + "\n").getBytes(Charset.forName("UTF-8")));
            writer.getOutputStream().flush();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(logLevel <= this.logLevel){
            System.out.println(this.getFormatedTime() + ": " + object.getClass().getName() + "[" + levelName[logLevel] + "]: " + message);
        }
    }
    
    public void log(String message, Object object, int logLevel){
        try {
            writer.getOutputStream().write((this.getFormatedTime() + ": " + object.getClass().getName() + "[" + levelName[logLevel] + "]: " + message + "\n").getBytes(Charset.forName("UTF-8")));
            writer.getOutputStream().flush();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(logLevel <= this.logLevel){
            System.out.println(this.getFormatedTime() + ": " + object.getClass() + "[" + levelName[logLevel] + "]: " + message);
        }
    }
    
    public void print(String message){
        System.out.println(this.getFormatedTime() + ": " + message);
    }
    
    public void stopLogging(){
        try {
            writer.getOutputStream().close();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
