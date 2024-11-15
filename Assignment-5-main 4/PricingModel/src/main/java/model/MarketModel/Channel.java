/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

import java.util.ArrayList;

import model.CustomerManagement.CustomerProfile;
import model.CustomerManagement.MarketCatalog;

/**
 *
 * @author kal bugrara
 */
public class Channel {
    String channelType; // tv, radio, internet, etc
    int price; // price per ad
    String unitofmeasure; // per minute, length
    ArrayList<Market> markets;
    MarketCatalog marketCatalog;
    private Double advertisingCost = 1000.0; 
    private Double conversionRate = 0.5; 

    public Channel(String t) {
        channelType = t;
        markets = new ArrayList<>();
        marketCatalog = new MarketCatalog();
    }

    public String getChannelType() {
        return channelType;
    }

    public void printChannel() {
        System.out.println(channelType);
    }
    
        public Double getAdvertisingCost() {
        return advertisingCost;
    }

    public void setAdvertisingCost(Double advertisingCost) {
        this.advertisingCost = advertisingCost;
    }

    public Double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public ArrayList<Market> getMarketList() {
        return markets;
    }

    public double calculateRevenueByChannel() {
        double sum = 0;

        for (Market m : getMarketList()) {
            if (m.getChannelType().equals(channelType)) {
                for (CustomerProfile cp : m.getCustomerProfileInMarket()) {

                    sum += cp.calculateCPConsumption();
                }
            }
        }

        return sum;
    }

    public void addMarketChannelAssignment(Market m) {
        markets.add(m);
    }
    
}
