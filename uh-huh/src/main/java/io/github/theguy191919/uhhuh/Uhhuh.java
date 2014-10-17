/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.github.theguy191919.uhhuh;

import io.github.theguy191919.udpft.net.ByteReceiver;
import io.github.theguy191919.udpft.net.ByteSender;
import io.github.theguy191919.udpft.protocol.Protocol;
import io.github.theguy191919.udpft.protocol.Protocol0;
import io.github.theguy191919.udpft.protocol.ProtocolEventListener;
import io.github.theguy191919.uhhuh.console.Console;

/**
 *
 * @author Yiwen Dong
 */
public class Uhhuh implements ProtocolEventListener{
    
    public static Console console = new Console();
    
    public void methods(){
        ByteSender sender = new ByteSender();
        sender.joinGroup();
        Protocol protocol = new Protocol0();
        protocol.setContent("thisd might work");
        protocol.setSender("sender");
        protocol.setRecipient("receiver");
        protocol.sendMessage(sender);
        Protocol.getProtocolEventHandler().regesterListner(this);
        ByteReceiver receiver = new ByteReceiver();
        receiver.startReceiver();
    }
    
    public static void main(String[] args){
        Uhhuh self = new Uhhuh();
        self.methods();
    }

    @Override
    public void gotEvent(Protocol protocol) {
        console.logger.log(protocol.getContent(), this, 3);
        System.out.println(protocol.getContent());
    }
}
