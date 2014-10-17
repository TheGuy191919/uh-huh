/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.console;

import java.util.Scanner;

/**
 * Where Logger is keeped
 * Made by main class
 * invoke commands
 * @author evan__000
 */
public class Console implements Runnable{
    
   public Logger logger = new Logger();
   private Thread thread;
   private Scanner scanner = new Scanner(System.in);
   
   public void start(){
       thread = new Thread(this, "Console");
       thread.start();
   }
   
    @Override
    public void run() {
        String command = scanner.next();
    }
    
    public void stop(){
        thread = null;
    }
}
