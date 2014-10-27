/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.gui;

import io.github.theguy191919.udpft.protocol.Protocol;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contain the representation of people in the room. 
 * this will be responsible for pings and call removeContact()
 * this is its own thread
 * @author evan__000
 */
public class Contact implements Runnable{
    
    public Thread thread;
    public String contactName;
    private GUIRoom parentRoom;
    private int lastPringTime = 0;
    private boolean running = false;
    
    public Contact(String contactName, GUIRoom parentRoom){
        this.contactName = contactName;
        this.parentRoom = parentRoom;
    }
    
    public synchronized void gotPing(Protocol protocol){
        this.lastPringTime = (int)(System.currentTimeMillis() / 1000);
    }
    
    public void start(){
        this.running = true;
        this.thread = new Thread(this, "Room " + this.parentRoom.roomName + " with Contact " + this.contactName);
        this.thread.start();
    }

    @Override
    public void run() {
        while(running){
            long startTime = System.currentTimeMillis();
            if((int)(System.currentTimeMillis() / 1000) - this.lastPringTime > 120){
                this.stop();
            }
            try {
                Thread.sleep(30000 - (System.currentTimeMillis() - startTime));
            } catch (InterruptedException ex) {
                Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void stop(){
        this.running = false;
        //this.parentRoom.removeContact(this);
        this.thread = null;
    }
    
    @Override
    public String toString(){
        return this.contactName;
    }
    
}
