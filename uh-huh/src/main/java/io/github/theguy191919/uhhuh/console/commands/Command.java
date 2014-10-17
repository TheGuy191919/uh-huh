/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.console.commands;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author evan__000
 */
public abstract class Command {
    public String name;
    public List<String> args = new LinkedList();
    private static Map<String, Class<? extends Command>> listOfCommands = new HashMap();
    
    public static Command getCommand(String command){
        command.trim();
        command = " " + command + " ";
        List<Integer> spacepos = new LinkedList();
        for(int a = 0; a < command.length(); a++){
            if(command.charAt(a) == ' '){
                spacepos.add(a);
            }
        }
        Command commandObj = null;
        try {
            commandObj = listOfCommands.get(command.substring(spacepos.get(0) + 1, spacepos.get(1)).trim()).newInstance();
            commandObj.name = command.substring(spacepos.get(0) + 1, spacepos.get(1)).trim();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int a = 1; a < spacepos.size() - 1; a++){
            commandObj.args.add(command.substring(spacepos.get(a) + 1, spacepos.get(a + 1)).trim());
        }
        return commandObj;
    }
}
