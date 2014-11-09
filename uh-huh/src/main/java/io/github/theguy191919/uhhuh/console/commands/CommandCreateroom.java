/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.console.commands;

import io.github.theguy191919.uhhuh.Uhhuh;
import io.github.theguy191919.uhhuh.gui.PaneTab;
import io.github.theguy191919.uhhuh.gui.LanTab;

/**
 * createroom name visiable(optional)
 * @author evan__000
 */
public class CommandCreateroom extends Command{

    @Override
    void trigged() {
        if(super.getArgsList().size() < 1){
            Uhhuh.console.logger.log("Error, need args name", this);
        } else {
            String ip = "234.235.236.";
//            if(super.getArgsList().size() == 2){
//                ip = super.getArgsList().get(1);
//            }
            String roomName = super.getArgsList().get(0);
            int ipSubAddress = 0;
            for(byte number : roomName.getBytes()){
                ipSubAddress = ipSubAddress + number;
            }
            ipSubAddress = ipSubAddress / roomName.getBytes().length;
            ipSubAddress = Math.abs(ipSubAddress);
            ip = ip + ipSubAddress;
            Uhhuh.console.logger.print("Creating room " + super.getArgsList().get(0) + " with ip " + ip);
            PaneTab tab = new LanTab(super.getArgsList().get(0), Uhhuh.guiChat, ip);
            //Uhhuh.guiChat.createTab(tab);
        }
    }
    
}
