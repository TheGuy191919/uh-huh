/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.console.commands;

import io.github.theguy191919.uhhuh.Uhhuh;

/**
 * leaveroom roomname
 * @author evan__000
 */
public class CommandLeaveroom extends Command{

    @Override
    void trigged() {
        if(super.getArgsList().size() < 1){
            Uhhuh.console.logger.log("Plese have args roomname", this);
        } else {
            Uhhuh.guiChat.removeTab(super.getArgsList().get(0));
            Uhhuh.console.logger.print("Room " + super.getArgsList().get(0) + " removed");
        }
    }
    
}
