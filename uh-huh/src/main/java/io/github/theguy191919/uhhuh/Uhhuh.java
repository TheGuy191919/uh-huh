/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.theguy191919.uhhuh;

import io.github.theguy191919.uhhuh.console.Console;
import io.github.theguy191919.uhhuh.gui.GUIChat;

/**
 *
 * @author Yiwen Dong
 */
public class Uhhuh {
    
    public static Console console = new Console();
    public static GUIChat guiChat = new GUIChat();
    
    public static void main(String[] args){
        console.start();
        java.awt.EventQueue.invokeLater(guiChat);
    }
}
