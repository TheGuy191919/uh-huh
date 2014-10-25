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
 *
 * @author evan__000
 */
public class CommandJoinroom extends Command{

    @Override
    void trigged() {
        if(super.getArgsList().size() < 1){
            Uhhuh.console.logger.log("Error, need args name", this);
        } else {
            String ip = "234.235.236.237";
            if(super.getArgsList().get(1) != null){
                ip = super.getArgsList().get(1);
            }
            GUIPaneTab tab = new GUIRoom(super.getArgsList().get(0), Uhhuh.guiChat, ip);
            Uhhuh.guiChat.createTab(tab);
        }
    }
    
}
