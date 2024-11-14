/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

import java.util.ArrayList;

import model.CustomerManagement.CustomerProfile;
import model.Personnel.Person;
import model.ProductManagement.SolutionOffer;
import model.Supplier.Supplier;

/**
 *
 * @author kal bugrara
 */
public class Market {
    // String name;
    ArrayList<SolutionOffer> so;
    ArrayList<MarketChannelAssignment> channels;
    ArrayList<String> characteristics;
    ArrayList<Market> submarkets;
    int size;
    ArrayList<CustomerProfile> customerlistinMarket;

    public Market(String s) {
        characteristics = new ArrayList<String>();
        characteristics.add(s);
        so = new ArrayList<SolutionOffer>();
        channels = new ArrayList<MarketChannelAssignment>();
        submarkets = new ArrayList<Market>();
        customerlistinMarket = new ArrayList<CustomerProfile>();
    }

    public Market newSubMarket(String s) {
        Market m = new Market(s);

        for (String c : characteristics) {
            m.addCharacteristic(c);
        }

        submarkets.add(m);
        return m;
    }


    public void addCustomerProfile(CustomerProfile cp) {
        customerlistinMarket.add(cp);
    } 
    
    

    public MarketChannelAssignment newMarketChannelAssignment(Channel c) {
        MarketChannelAssignment mca = new MarketChannelAssignment(this, c);
        channels.add(mca);
        return mca;
    }

    public void addchannel(MarketChannelAssignment mca) {
        channels.add(mca);
    }

    public SolutionOffer newSolutionOffer(MarketChannelAssignment mca, Supplier supplier) {
        SolutionOffer s = new SolutionOffer(mca,supplier);
        so.add(s);
        return s;
    }

    public void addCharacteristic(String s) {
        characteristics.add(s);
    }

    public boolean isMatch(int i) {
        for (String c : characteristics) {
            if (c.equals(i)) {
                return true;
            }
        }
        return false;
    }

    public boolean isMatchcharacteristics(String s) {
        for (String c : characteristics) {
            if (c.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Market> getSubMarkets() {
        return submarkets;
    }

    // public Market getSubMarkets() {
    // for (Market m : submarkets) {
    // return m;
    // }
    // return null; // Add a default return value if no submarkets are found
    // }

    public ArrayList<String> getCharacteristics() {
        return characteristics;
    }

    public ArrayList<MarketChannelAssignment> getChannels() {
        return channels;
    }

    public ArrayList<CustomerProfile> getCustomerProfileInMarket() {
        return customerlistinMarket;
    }

    // public String getChannels(){
    // for (MarketChannelAssignment mca : channels){
    // return mca.getChannel().getChannelType();
    // }
    // return null;
    // }

    public String getChannelType() {
        for (MarketChannelAssignment mca : channels) {
            return mca.getChannel().getChannelType();
        }
        return null;
    }

    public double calculateMarketRevenueAfterDiscountByChannel(){
        double sum = 0;

        for (CustomerProfile cp : customerlistinMarket){
            if(cp.getMarket().getChannelType().equals("Internet"))
                sum = sum + cp.calculateCPConsumption() * 1.2;
            else if(cp.getMarket().getChannelType().equals("Radio"))
                sum = sum + cp.calculateCPConsumption() * 0.8;
            else if(cp.getMarket().getChannelType().equals("TV"))
                sum = sum + cp.calculateCPConsumption();
        }
        return sum;
    }

    public double calculateTargetMarketRevenue() {
        double sum = 0.0f;

        return sum;
    }
    
}
