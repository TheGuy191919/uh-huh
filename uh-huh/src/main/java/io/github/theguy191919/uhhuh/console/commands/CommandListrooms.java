/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.console.commands;

import io.github.theguy191919.uhhuh.Uhhuh;
import io.github.theguy191919.uhhuh.gui.PaneTab;
import java.util.List;

/**
 * listrooms
 * @author evan__000
 */
public class CommandListrooms extends Command{

    @Override
    void trigged() {
        Uhhuh.console.logger.print("---List Of Rooms---");
        List<PaneTab> listOfTabs = Uhhuh.guiChat.getListOfTabs();
        for(PaneTab tab : listOfTabs){
            Uhhuh.console.logger.print(tab.getName());
        }
        Uhhuh.console.logger.print("---End Of List---");
    }
    
}
