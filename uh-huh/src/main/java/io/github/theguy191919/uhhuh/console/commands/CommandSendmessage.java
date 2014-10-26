/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.console.commands;

import io.github.theguy191919.uhhuh.Uhhuh;
import io.github.theguy191919.uhhuh.gui.GUIRoom;

/**
 * sendmessage roomname message
 * @author evan__000
 */
public class CommandSendmessage extends Command{

    @Override
    void trigged() {
        if(super.getArgsList().size() < 2){
            Uhhuh.console.logger.log("Error, need args roomname message", this);
        } else {
            ((GUIRoom)Uhhuh.guiChat.getTab(super.getArgsList().get(0))).sendMessage(super.getArgsList().get(1));
        }
    }
    
}
