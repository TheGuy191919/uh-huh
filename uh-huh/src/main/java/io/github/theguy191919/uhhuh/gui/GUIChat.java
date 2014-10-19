/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.gui;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

/**
 * This is the basic chat gui where rooms can be created.
 * Called by main class
 * @author evan__000
 */
public class GUIChat {
    
    public String userName;
    private GroupLayout layout;
    private JFrame jFrame;
    private JMenuBar jMenuBar;
    private JMenu jMenuAbout;
    private JTabbedPane jTabbedPane;
    private List<GUIPaneTab> listOfTabs = Collections.synchronizedList(new LinkedList<GUIPaneTab>());
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
        jMenuAbout = new JMenu("About");
        jMenuBar.add(jMenuAbout);
        
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
    
    public void createTabs(String name, GUIPaneTab tab){
        this.listOfTabs.add(tab);
        this.jTabbedPane.addTab(name, tab.getTab());
    }
    
    public void removeTabs(GUIPaneTab tab){
        this.jTabbedPane.remove(tab.getTab());
        this.listOfTabs.remove(tab);
    }
    
}
