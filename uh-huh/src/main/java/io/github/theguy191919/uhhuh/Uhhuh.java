/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.theguy191919.uhhuh;

import io.github.theguy191919.uhhuh.console.Console;
import io.github.theguy191919.uhhuh.gui.Chat;

/**
 *
 * @author Yiwen Dong
 */
public class Uhhuh {
    
    private static String cfgLocation = System.getProperty("user.dir");
    public static Options options = new Options();
    public static Console console = new Console();
    public static Chat guiChat = new Chat();
    
    public static String getcfgLocation(){
        return cfgLocation;
    }
    
    public static void setcfgLocation(String location){
        cfgLocation = location;
    }
    
    public static void main(String[] args){
        console.start();
        java.awt.EventQueue.invokeLater(guiChat);
    }
}
