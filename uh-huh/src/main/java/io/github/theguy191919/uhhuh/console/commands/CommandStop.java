/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.console.commands;

/**
 * Name is stop
 * @author evan__000
 */
public class CommandStop extends Command{
    static{
        Command.registerCommand("stop", CommandStop.class);
    }
    
    public CommandStop(){
        this.setCommandName("stop");
    }

    @Override
    void trigged() {
        
    }
}
