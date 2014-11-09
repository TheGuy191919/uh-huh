/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.gui;

import javax.swing.*;

/**
 *
 * @author evan__000
 */
public class StartTab implements PaneTab{
    
    private Chat parentChat;
    private JPanel tabbedPanel;
    
    public StartTab(Chat parent){
        this.parentChat = parent;
        tabbedPanel = new JPanel();
//        this.tabbedPanel.add(new JLabel("Testing in progress."));
//        button = new JButton("Stop");
//        this.tabbedPanel.add(button);
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e){
//                //Uhhuh.console.logger.log("this button is working", this);
//            }
//        });
        parentChat.createTab(this);
    }

    @Override
    public JPanel getTab() {
        return this.tabbedPanel;
    }
    
    @Override
    public String getName(){
        return "Start";
    }

    @Override
    public void tabAdded() {
        
    }

    @Override
    public void tabRemoved() {
        
    }
}
