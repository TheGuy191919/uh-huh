/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.gui;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.*;

/**
 * This is the basic chat gui where rooms can be created.
 * Called by main class
 * @author evan__000
 */
public class GUIChat implements Runnable{
    
    public String userName = String.valueOf((int)(Math.random() * 10000));
    private boolean visiable = true;
    
    private GroupLayout layout;
    private JFrame jFrame;
    private JMenuBar jMenuBar;
    private JMenu jMenuUhhuh;
    private JMenuItem jMenuUhhuhAbout;
    private JMenuItem jMenuUhhuhCreateroom;
    private JMenuItem jMenuUhhuhJoinroom;
    private JMenuItem jMenuUhhuhOptions;
    private JMenu jMenuExit;
    private JMenuItem jMenuExitChat;
    private JMenuItem jMenuExitProgram;
    private JTabbedPane jTabbedPane;
    private Map<JPanel, GUIPaneTab> mapOfTabs = new ConcurrentHashMap();
    private GUIStart start;
    
    public GUIChat(){
        
        this.initFrame();
        
        GUIRoom room = new GUIRoom("Death", this, "234.235.236.237");
        //jFrame.setVisible(true);
    }
    
    public void initFrame(){
        jFrame = new JFrame("Uh Huh");
        jFrame.setSize(800, 600);
        layout = new GroupLayout(jFrame.getContentPane());
        jFrame.getContentPane().setLayout(layout);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        layout.setAutoCreateContainerGaps(false);
        layout.setAutoCreateGaps(true);
        
        jMenuBar = new JMenuBar();
        jFrame.add(jMenuBar);
        //jMenuBar.setBounds(0, jFrame.getHeight(), jFrame.getWidth(), 100);
        jMenuUhhuh = new JMenu("Uh Huh");
        jMenuUhhuhAbout = new JMenuItem("About");
        jMenuUhhuhCreateroom = new JMenuItem("Create Room");
        jMenuUhhuhJoinroom = new JMenuItem("Join Room");
        jMenuUhhuhOptions = new JMenuItem("Options");
        jMenuUhhuh.add(jMenuUhhuhAbout);
        jMenuUhhuh.addSeparator();
        jMenuUhhuh.add(jMenuUhhuhCreateroom);
        jMenuUhhuh.add(jMenuUhhuhJoinroom);
        jMenuUhhuh.addSeparator();
        jMenuUhhuh.add(jMenuUhhuhOptions);
        jMenuBar.add(jMenuUhhuh);
        jMenuExit = new JMenu("Exit");
        jMenuExitChat = new JMenuItem("Exit This Chat");
        jMenuExit.add(jMenuExitChat);
        jMenuExitProgram = new JMenuItem("Exit Uh huh");
        jMenuExit.add(jMenuExitProgram);
        jMenuBar.add(jMenuExit);
        
        jTabbedPane = new JTabbedPane();
        jFrame.add(jTabbedPane);
        //jTabbedPane.setBounds(0, jFrame.getHeight() - 100, jFrame.getWidth(), jFrame.getHeight() - 100);
        
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jMenuBar, 0, jFrame.getWidth(), Short.MAX_VALUE)
                        .addComponent(jTabbedPane, 0,jFrame.getWidth(), Short.MAX_VALUE)
                )
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addComponent(jMenuBar, 30, 30, 30)
                .addComponent(jTabbedPane, 0, jFrame.getHeight() - 80, Short.MAX_VALUE)
        );
        
        start = new GUIStart(this);
    }
    
    public JFrame getFrame(){
        return this.jFrame;
    }
    
    public void createTab(GUIPaneTab tab){
        this.mapOfTabs.put(tab.getTab(), tab);
        this.jTabbedPane.addTab(tab.getName(), tab.getTab());
        tab.tabAdded();
    }
    
    public GUIPaneTab getTab(String name){
        return this.mapOfTabs.get((JPanel)this.jTabbedPane.getComponentAt(this.jTabbedPane.indexOfTab(name)));
    }
    
    public List<GUIPaneTab> getListOfTabs(){
        List<GUIPaneTab> listOfTab = new LinkedList<>(this.mapOfTabs.values());
        return listOfTab;
    }
    
    public void removeCurrentTab(){
        GUIPaneTab tab = this.mapOfTabs.get((JPanel)this.jTabbedPane.getComponentAt(this.jTabbedPane.getSelectedIndex()));
        tab.tabRemoved();
    }
    
    public void removeTab(GUIPaneTab tab){
        this.jTabbedPane.remove(tab.getTab());
        this.mapOfTabs.remove(tab.getTab());
        tab.tabRemoved();
    }
    
    public void removeTab(String name){
        GUIPaneTab tab = this.mapOfTabs.remove((JPanel)this.jTabbedPane.getComponentAt(this.jTabbedPane.indexOfTab(name)));
        tab.tabRemoved();
    }

    @Override
    public void run() {
        this.jFrame.setVisible(true);
    }
    
}
