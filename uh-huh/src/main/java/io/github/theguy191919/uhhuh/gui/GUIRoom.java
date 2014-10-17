/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.gui;

import io.github.theguy191919.udpft.net.ByteReceiver;
import io.github.theguy191919.udpft.net.ByteSender;
import io.github.theguy191919.udpft.protocol.Protocol;
import io.github.theguy191919.udpft.protocol.ProtocolEventHandler;
import io.github.theguy191919.udpft.protocol.ProtocolEventListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *A room containing Contacts.
 * this is its own thread.
 * @author evan__000
 */
public class GUIRoom implements Runnable, ProtocolEventListener{
    
    public Thread thread;
    public String roomName;
    private GUIChat parentChat;
    private ByteSender sender;
    private ByteReceiver receiver;
    private boolean visible;
    private Map<String, Contact> mapOfContact = new ConcurrentHashMap<>();
    private boolean running;
    
    public GUIRoom(String roomName, GUIChat parentChat, String ip){
        this.roomName = roomName;
        this.parentChat = parentChat;
        try {
            this.sender = new ByteSender(InetAddress.getByName(ip), 58394);
            this.receiver = new ByteReceiver(InetAddress.getByName(ip), 58394);
        } catch (UnknownHostException ex) {
            Logger.getLogger(GUIRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
        Protocol.getProtocolEventHandler().regesterListner(this);
        this.initTab();
    }
    
    public GUIRoom(String roomName, GUIChat parentChat, String ip, boolean visiable){
        this.roomName = roomName;
        this.parentChat = parentChat;
        try {
            this.sender = new ByteSender(InetAddress.getByName(ip), 58394);
            this.receiver = new ByteReceiver(InetAddress.getByName(ip), 58394);
        } catch (UnknownHostException ex) {
            Logger.getLogger(GUIRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
        Protocol.getProtocolEventHandler().regesterListner(this);
        if(visiable){
            this.initTab();
        }
    }
    
    public void start(){
        this.sender.joinGroup();
        this.receiver.startReceiver();
        this.thread = new Thread(this, "GUIRoom " + this.roomName);
        this.thread.start();
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //every 30 second sent a message
    }
    
    public void stop(){
        
        this.thread = null;
    }

    @Override
    public void gotEvent(Protocol protocol) {
        if(protocol.getProtocolNumber() == 0){
            this.gotMessage(protocol.getSender(), protocol.getContent());
        }else if(protocol.getProtocolNumber() == 2){
            if(this.mapOfContact.containsKey(protocol.getSender())){
                this.mapOfContact.get(protocol.getSender()).gotPing(protocol);
            }else{
                this.addContact(new Contact(protocol.getSender(), this));
                this.mapOfContact.get(protocol.getSender()).gotPing(protocol);
            }
        }
    }
    
    private void initTab(){
        
    }
    
    private void removeTab(){
        
    }
    
    public void gotMessage(String sender, String message){
        
    }
    
    public void addContact(Contact contact){
        this.mapOfContact.put(contact.contactName, contact);
        contact.start();
    }
    
    public void removeContact(Contact contact){
        this.mapOfContact.remove(contact.contactName);
        contact.stop();
    }
}
