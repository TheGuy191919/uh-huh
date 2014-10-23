/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.console.commands;

import io.github.theguy191919.uhhuh.Uhhuh;
import io.github.theguy191919.uhhuh.gui.GUIPaneTab;
import io.github.theguy191919.uhhuh.gui.GUIRoom;

/**
 * createroom name
 * @author evan__000
 */
public class CommandCreateroom extends Command{

    @Override
    void trigged() {
        if(super.getArgsList().size() < 1){
            Uhhuh.console.logger.log("Error, need args name", this);
        } else {
            GUIPaneTab tab = new GUIRoom(super.getArgsList().get(0), Uhhuh.guiChat, "234.235.236.237");
            Uhhuh.guiChat.createTab(tab);
        }
    }
    
}
