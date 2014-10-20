/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.gui;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.*;

/**
 * This is the basic chat gui where rooms can be created.
 * Called by main class
 * @author evan__000
 */
public class GUIChat implements Runnable{
    
    public String userName;
    private GroupLayout layout;
    private JFrame jFrame;
    private JMenuBar jMenuBar;
    private JMenu jMenuUhhuh;
    private JMenuItem jMenuUhuhAbout;
    private JMenu jMenuExit;
    private JMenuItem jMenuExitChat;
    private JMenuItem jMenuExitProgram;
    private JTabbedPane jTabbedPane;
    private Map<JPanel, GUIPaneTab> mapOfTabs = new ConcurrentHashMap();
    private GUIStart GUIStart;
    
    public GUIChat(){
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
        jMenuBar.add(jMenuUhhuh);
        
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
        
        GUIStart = new GUIStart(this);
        
        jFrame.setVisible(true);
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
        return null;
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
        
    }
    
}
