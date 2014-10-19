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
public class GUIStart implements GUIPaneTab{
    
    private GUIChat parentChat;
    private JPanel tabbedPanel;
    
    public GUIStart(GUIChat parent){
        this.parentChat = parent;
        tabbedPanel = new JPanel();
        this.tabbedPanel.add(new JLabel("Testing in progress."));
        
        parentChat.createTabs("Start", this);
    }

    @Override
    public JPanel getTab() {
        return this.tabbedPanel;
    }
}
