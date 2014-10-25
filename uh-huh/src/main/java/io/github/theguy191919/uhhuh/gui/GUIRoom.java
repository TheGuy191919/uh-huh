/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.gui;

import io.github.theguy191919.udpft.net.ByteReceiver;
import io.github.theguy191919.udpft.net.ByteSender;
import io.github.theguy191919.udpft.protocol.Protocol;
import io.github.theguy191919.udpft.protocol.Protocol0;
import io.github.theguy191919.udpft.protocol.Protocol2;
import io.github.theguy191919.udpft.protocol.ProtocolEventListener;
import io.github.theguy191919.uhhuh.Uhhuh;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * A room containing Contacts. this is its own thread.
 *
 * @author evan__000
 */
public class GUIRoom implements Runnable, ProtocolEventListener, GUIPaneTab {

    //http://zetcode.com/tutorials/javaswingtutorial/menusandtoolbars/

    public Thread thread;
    public String roomName;
    private GUIChat parentChat;
    private ByteSender sender;
    private ByteReceiver receiver;
    private boolean visible = true;
    private Map<String, Contact> mapOfContact = new ConcurrentHashMap<>();
    private boolean running;
    
    private GroupLayout layout;
    private JPanel jPanel = new JPanel();
    private JTextArea jPanelChatArea;
    private JScrollPane jPaneUsers;
    private JList jListUsers;
    private JTextArea jEnterArea;
    private JButton jButtonSend;
    
    public GUIRoom(String roomName, GUIChat parentChat, String ip) {
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
        parentChat.createTab(this);
    }
    
    public GUIRoom(String roomName, GUIChat parentChat, String ip, boolean visiable) {
        this.roomName = roomName;
        this.parentChat = parentChat;
        try {
            this.sender = new ByteSender(InetAddress.getByName(ip), 58394);
            this.receiver = new ByteReceiver(InetAddress.getByName(ip), 58394);
        } catch (UnknownHostException ex) {
            Logger.getLogger(GUIRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
        Protocol.getProtocolEventHandler().regesterListner(this);
        if (visiable) {
            this.initTab();
            parentChat.createTab(this);
        }
    }
    
    public void start() {
        this.sender.joinGroup();
        this.receiver.startReceiver();
        this.running = true;
        this.thread = new Thread(this, "GUIRoom " + this.roomName);
        this.thread.start();
    }
    
    @Override
    public void run() {
        //every 30 second sent a message
        while (running) {
            long startTime = System.currentTimeMillis();
            Protocol ping = new Protocol2();
            ping.setSender(this.parentChat.userName);
            this.sender.send(ping.returnByteArray());
            try {
                Thread.sleep(30000 - (System.currentTimeMillis() - startTime));
            } catch (InterruptedException ex) {
                Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void stop() {
        this.running = false;
        Iterator iterator = this.mapOfContact.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry paires = (Map.Entry<String, Contact>) iterator.next();
            ((Contact) (paires.getValue())).stop();
            iterator.remove();
        }
        this.mapOfContact.clear();
        this.receiver.stopReveiver();
        this.thread = null;
    }
    
    @Override
    public void gotEvent(Protocol protocol) {
        if (protocol.getProtocolNumber() == 0) {
            this.gotMessage(protocol.getSender(), protocol.getContent());
        } else if (protocol.getProtocolNumber() == 2) {
            if (this.mapOfContact.containsKey(protocol.getSender())) {
                this.mapOfContact.get(protocol.getSender()).gotPing(protocol);
            } else {
                this.addContact(new Contact(protocol.getSender(), this));
                this.mapOfContact.get(protocol.getSender()).gotPing(protocol);
            }
        }
    }
    
    private void initTab() {
        this.layout = new GroupLayout(this.jPanel);
        this.jPanel.setLayout(layout);
        
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        
        this.jPanelChatArea = new JTextArea();
        this.jPanelChatArea.setEditable(false);
        this.jPanel.add(jPanelChatArea);
        this.jListUsers = new JList();
        this.jPaneUsers = new JScrollPane(this.jListUsers);
        this.jPanel.add(jPaneUsers);
        this.jEnterArea = new JTextArea();
        this.jPanel.add(jEnterArea);
        this.jButtonSend = new JButton("Send");
        this.jPanel.add(jButtonSend);
        
        this.layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addGroup(
                        layout.createParallelGroup()
                        .addComponent(this.jPanelChatArea)
                        .addGroup(
                                layout.createSequentialGroup()
                                .addComponent(this.jEnterArea)
                                .addComponent(this.jButtonSend)
                        )
                ).addComponent(jPaneUsers, 200, 200, 200)
        );
        this.layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(
                                layout.createSequentialGroup()
                                .addComponent(this.jPanelChatArea)
                                .addGroup(
                                        layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(this.jEnterArea, 24, 32, 48)
                                        .addComponent(this.jButtonSend)
                                )
                        )
                        .addComponent(this.jPaneUsers)
                )
        );
        this.jButtonSend.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Protocol pro = new Protocol0();
                pro.setContent(jEnterArea.getText());
                pro.setRecipient("none");
                pro.setSender(Uhhuh.guiChat.userName);
                sender.send(pro.returnByteArray());
            }
        });
    }
    
    public void removeTab() {
        
    }
    
    public void gotMessage(String sender, String message) {
        this.jPanelChatArea.append("[" + sender + "] " + message + "\n");
    }
    
    public void addContact(Contact contact) {
        this.mapOfContact.put(contact.contactName, contact);
        contact.start();
    }
    
    public void removeContact(Contact contact) {
        this.mapOfContact.remove(contact.contactName);
        contact.stop();
    }
    
    @Override
    public JPanel getTab() {
        return this.jPanel;
    }
    
    @Override
    public String getName() {
        return this.roomName;
    }
    
    @Override
    public void tabAdded() {
        //this.initTab();
    }
    
    @Override
    public void tabRemoved() {
        //this.removeTab();
    }
    
}
