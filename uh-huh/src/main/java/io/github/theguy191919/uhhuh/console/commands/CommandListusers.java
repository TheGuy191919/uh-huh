/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.console.commands;

import io.github.theguy191919.uhhuh.Uhhuh;
import io.github.theguy191919.uhhuh.gui.Contact;
import io.github.theguy191919.uhhuh.gui.LanTab;
import java.util.List;

/**
 * listusers roomname
 * @author evan__000
 */
public class CommandListusers extends Command{

    @Override
    void trigged() {
        if(super.getArgsList().size() < 1){
            Uhhuh.console.logger.log("Error, need args roomname", this);
        } else {
            List<String> listOfNames = ((LanTab)Uhhuh.guiChat.getTab(super.getArgsList().get(0))).getContacts();
            Uhhuh.console.logger.print("---List Of Contacts in room " + super.getArgsList().get(0) + "---");
            for(String name : listOfNames){
                Uhhuh.console.logger.print(name);
            }
            Uhhuh.console.logger.print("-----End of List-----");
        }
    }
    
}
