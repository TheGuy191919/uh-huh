/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.console.commands;

import io.github.theguy191919.uhhuh.Uhhuh;
import io.github.theguy191919.uhhuh.gui.GUIPaneTab;
import java.util.List;

/**
 * 
 * @author evan__000
 */
public class CommandListrooms extends Command{

    @Override
    void trigged() {
        List<GUIPaneTab> listOfTabs = Uhhuh.guiChat.getListOfTabs();
        for(GUIPaneTab tab : listOfTabs){
            Uhhuh.console.logger.log(tab.getName(), this);
        }
    }
    
}
