/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.console.commands;

import io.github.theguy191919.uhhuh.Uhhuh;
import io.github.theguy191919.uhhuh.gui.GUIPaneTab;

/**
 * Name is stop
 * @author evan__000
 */
public class CommandStop extends Command{
//    static{
//        Command.registerCommand("stop", CommandStop.class);
//    }
    
    public CommandStop(){
        this.setCommandName("stop");
    }

    @Override
    void trigged() {
        for(GUIPaneTab tab : Uhhuh.guiChat.getListOfTabs()){
            Uhhuh.guiChat.removeTab(tab);
        }
        Uhhuh.guiChat.stop();
        Uhhuh.console.stop();
        Uhhuh.options.closeProperties();
    }
}
