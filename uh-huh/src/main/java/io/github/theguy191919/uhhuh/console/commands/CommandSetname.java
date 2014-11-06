/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.console.commands;

import io.github.theguy191919.uhhuh.Uhhuh;

/**
 * setname name
 * @author evan__000
 */
public class CommandSetname extends Command{

    @Override
    void trigged() {
        if(super.getArgsList().size() < 1){
            Uhhuh.console.logger.log("Error, need args name", this);
        } else {
            Uhhuh.guiChat.setUserName(super.getArgsList().get(0));
            Uhhuh.console.logger.print("Name set to: " + super.getArgsList().get(0));
        }
    }
    
}
