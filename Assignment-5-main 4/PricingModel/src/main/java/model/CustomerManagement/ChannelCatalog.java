/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import model.MarketModel.Channel;
import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class ChannelCatalog {
    ArrayList<Channel> channels;

    public ChannelCatalog() {
        channels = new ArrayList<>();
    }

    public Channel newChannel(String t){
        Channel c = new Channel(t);
        channels.add(c);
        return c;
    }

    public ArrayList<Channel> getChannelList() {
        return channels;
    }

    public void printChannelCatalog(){
        for(Channel c: channels){
            c.printChannel();
        }
    }

    
}
