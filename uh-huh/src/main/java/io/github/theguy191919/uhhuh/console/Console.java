/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.console;

import io.github.theguy191919.uhhuh.Uhhuh;
import io.github.theguy191919.uhhuh.console.commands.Command;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;

/**
 * Where Logger is keeped
 * Made by main class
 * invoke commands
 * @author evan__000
 */
public class Console implements Runnable{
    
   public Logger logger = new Logger(Integer.valueOf(Uhhuh.options.getProperty("LogLevel", "3")));
   private Thread thread;
   private Scanner scanner = new Scanner(System.in);
   private boolean running = false;
   
   public void start(){
       thread = new Thread(this, "Console");
       running = true;
       thread.start();
   }
   
    @Override
    public void run() {
        while(running){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = "";
            //String command = scanner.nextLine();
            //Command.getCommand(command);
            do{
                try {
                    while(!reader.ready() && running){
                        Thread.sleep(200);
                    }
                    input = reader.readLine();
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    java.util.logging.Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while("".equals(input) && running);
            if(running){
                Command command = Command.getCommand(input);
                if(command == null){
                    Uhhuh.console.logger.log("Command not Found", this);
                }
            }
        }
    }
    
    public void stop(){
        running = false;
        this.thread.interrupt();
        scanner.close();
        thread = null;
    }
}
