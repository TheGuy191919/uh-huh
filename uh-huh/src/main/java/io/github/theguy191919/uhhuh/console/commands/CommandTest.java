/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.console.commands;

import io.github.theguy191919.uhhuh.Uhhuh;

/**
 *
 * @author evan__000
 */
public class CommandTest extends Command{
    
//    static{
//        Command.registerCommand("test", CommandStop.class);
//    }
    
    public CommandTest(){
        this.setCommandName("test");
    }

    @Override
    void trigged() {
        Uhhuh.console.logger.log("Testing" + super.getArgsList().get(0), this, 4);
    }
}
