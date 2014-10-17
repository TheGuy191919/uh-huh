/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.console;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Where it writes to console and possibly the log files
 * @author evan__000
 */
public class Logger {
    //3 logs all
    //2 logs serious
    //1 logs error only
    public int logLevel = 3;
    private String[] levelName = {
        "HOW", "HIGH", "MEDIUM", "LOW", "DEBUG"
    };
    
    public void setLogLevel(int logLevel){
        this.logLevel = logLevel;
    }
    
    private String getFormatedTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
    }
    
    public void log(String message, Object object){
        int logLevel = 3;
        if(logLevel <= this.logLevel){
            System.out.println(this.getFormatedTime() + ": " + object.getClass() + "[" + levelName[logLevel] + "]: " + message);
        }
    }
    
    public void log(String message, Object object, int logLevel){
        if(logLevel <= this.logLevel){
            System.out.println(this.getFormatedTime() + ": " + object.getClass() + "[LOW]: " + message);
        }
    }
}
