/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.MarketModel;

import java.util.ArrayList;

public class MasterMCAList {
     ArrayList<MarketChannelAssignment> marketChannelAssignmentList;

    public MasterMCAList() {
        this.marketChannelAssignmentList = new ArrayList<>();
    }

    public void addMarketChannelAssignment(Market market, Channel channel) {
        MarketChannelAssignment mca = new MarketChannelAssignment(market, channel);
        marketChannelAssignmentList.add(mca);
    }

    public ArrayList<MarketChannelAssignment> getMarketChannelAssignmentList() {
        return marketChannelAssignmentList;
    }
    
}
