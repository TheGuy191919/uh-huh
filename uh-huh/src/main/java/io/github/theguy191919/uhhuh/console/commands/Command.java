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
    
    private String commandName;
    private List<String> args = new LinkedList();
    
    private static final Map<String, Class<? extends Command>> listOfCommands;
    static{
        listOfCommands = new HashMap<>();
        Command.registerCommand("createroom", CommandCreateroom.class);
        Command.registerCommand("joinroom", CommandJoinroom.class);
        Command.registerCommand("leaveroom", CommandLeaveroom.class);
        Command.registerCommand("listrooms", CommandListrooms.class);
        Command.registerCommand("listusers", CommandListusers.class);
        Command.registerCommand("sendmessage", CommandSendmessage.class);
        Command.registerCommand("setname", CommandSetname.class);
        Command.registerCommand("stop", CommandStop.class);
        Command.registerCommand("test", CommandTest.class);
    }
    
    public static void registerCommand(String name, Class<? extends Command> classThatExtendsCommand){
        listOfCommands.put(name, classThatExtendsCommand);
    }
    
    public static Command getCommand(String command){
        command = command.trim();
        command = " " + command + " ";
        List<Integer> spacepos = new LinkedList();
        for(int a = 0; a < command.length(); a++){
            if(command.charAt(a) == ' '){
                spacepos.add(a);
            }
        }
        Command commandObj = null;
        if(listOfCommands.containsKey(command.substring(spacepos.get(0) + 1, spacepos.get(1)).toLowerCase().trim())){
        try {
            commandObj = listOfCommands.get(command.substring(spacepos.get(0) + 1, spacepos.get(1)).toLowerCase().trim()).newInstance();
            //commandObj.setCommandName(command.substring(spacepos.get(0) + 1, spacepos.get(1)).trim());
            for(int a = 1; a < spacepos.size() - 1; a++){
                commandObj.getArgsList().add(command.substring(spacepos.get(a) + 1, spacepos.get(a + 1)).trim());
            }
            
            commandObj.trigged();
            
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        return commandObj;
    }
    
    public String getCommandName(){
        return this.commandName;
    }
    
    public void setCommandName(String name){
        this.commandName = name;
    }
    
    public List<String> getArgsList(){
        return this.args;
    }
    
    abstract void trigged();
}
