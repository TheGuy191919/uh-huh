/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.gui;

import io.github.theguy191919.udpft.encryption.AbstractCrypto;
import io.github.theguy191919.udpft.encryption.SimpleCrypto;
import io.github.theguy191919.udpft.net.ByteReceiver;
import io.github.theguy191919.udpft.net.ByteSender;
import io.github.theguy191919.udpft.protocol.Protocol;
import io.github.theguy191919.udpft.protocol.Protocol0;
import io.github.theguy191919.udpft.protocol.Protocol2;
import io.github.theguy191919.udpft.protocol.ProtocolEventListener;
import io.github.theguy191919.uhhuh.Uhhuh;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * A room containing Contacts. this is its own thread.
 *
 * @author evan__000
 */
public class GUIRoom implements Runnable, GUIPaneTab {

    //http://zetcode.com/tutorials/javaswingtutorial/menusandtoolbars/

    public Thread thread;
    public String roomName;
    private GUIChat parentChat;
    private ByteSender sender;
    private ByteReceiver receiver;
    private boolean visible = true;
    private Map<String, Contact> mapOfContact = new ConcurrentHashMap<>();
    private boolean running;
    private AbstractCrypto crypto = new SimpleCrypto();
    
    private GroupLayout layout;
    private JPanel jPanel = new JPanel();
    private JScrollPane jScrollChatArea;
    private JTextArea jPanelChatArea;
    private JScrollPane jPaneUsers;
    private JList jListUsers;
    private JTextArea jEnterArea;
    private JButton jButtonSend;
    
    public GUIRoom(String roomName, GUIChat parentChat, String ip) {
        this(roomName, parentChat, ip, true);
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
        //Protocol.getProtocolEventHandler().regesterListner(this);
        this.crypto.setPublicKey(this.roomName.hashCode());
        this.sender.setCrypto(crypto);
        this.receiver.setCrypto(crypto);
        this.receiver.addListener(new ProtocolEventListener(){

            @Override
            public void gotEvent(Protocol protocol) {
                protocolEvent(protocol);
            }
            
        });
        if (visiable) {
            this.initTab();
            parentChat.createTab(this);
        }
        this.start();
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
    
    public void protocolEvent(Protocol protocol) {
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
        this.jPanelChatArea.append("Welcome to " + this.roomName + ". Enjoy your stay.");
        this.jScrollChatArea = new JScrollPane(this.jPanelChatArea);
        this.jPanel.add(jScrollChatArea);
        this.jListUsers = new JList(new Vector<>(this.mapOfContact.values()));
        this.jPaneUsers = new JScrollPane(this.jListUsers);
        this.jPanel.add(jPaneUsers);
        this.jEnterArea = new JTextArea();
        this.jEnterArea.addKeyListener(new KeyListener(){

            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    jButtonSend.doClick();
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
                
        });
        this.jPanel.add(jEnterArea);
        this.jButtonSend = new JButton("Send");
        this.jPanel.add(jButtonSend);
        
        this.layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addGroup(
                        layout.createParallelGroup()
                        .addComponent(this.jScrollChatArea)
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
                                .addComponent(this.jScrollChatArea)
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
                sendMessage();
            }
        });
    }
    
    public void removeTab() {
        
    }
    
    public void gotMessage(String sender, String message) {
        this.jPanelChatArea.append("\n" + "[" + sender + "] " + message);
        this.jScrollChatArea.getVerticalScrollBar().validate();
        this.jScrollChatArea.getVerticalScrollBar().setValue(this.jScrollChatArea.getVerticalScrollBar().getMaximum());
        Uhhuh.console.logger.print("[" + this.roomName + "] " + "[" + sender + "] " + message);
    }
    
    public void sendMessage() {
        Protocol pro = new Protocol0();
        pro.setContent(jEnterArea.getText());
        pro.setRecipient("none");
        pro.setSender(Uhhuh.guiChat.userName);
        sender.send(pro.returnByteArray());
        jEnterArea.setText("");
    }

    public void sendMessage(String message) {
        Protocol pro = new Protocol0();
        pro.setContent(message);
        pro.setRecipient("none");
        pro.setSender(Uhhuh.guiChat.userName);
        sender.send(pro.returnByteArray());
        jEnterArea.setText("");
    }

    public void addContact(Contact contact) {
        this.mapOfContact.put(contact.contactName, contact);
        this.refreshContactList();
        contact.start();
    }
    
    public void removeContact(Contact contact) {
        this.mapOfContact.remove(contact.contactName);
        this.refreshContactList();
        contact.stop();
    }
    
    private void refreshContactList(){
        this.jListUsers.setListData(new LinkedList<>(this.mapOfContact.values()).toArray());
    }
    
    public List<String> getContacts(){
        return new LinkedList<String>(this.mapOfContact.keySet());
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
