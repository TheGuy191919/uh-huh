/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.console;

import io.github.theguy191919.uhhuh.console.commands.Command;
import java.util.Scanner;

/**
 * Where Logger is keeped
 * Made by main class
 * invoke commands
 * @author evan__000
 */
public class Console implements Runnable{
    
   public Logger logger = new Logger(4);
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
            String command = scanner.nextLine();
            Command.getCommand(command);
        }
    }
    
    public void stop(){
        running = false;
        thread = null;
    }
}
