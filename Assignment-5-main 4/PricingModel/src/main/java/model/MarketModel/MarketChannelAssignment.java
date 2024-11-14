/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;
import java.util.ArrayList;
import model.Supplier.Supplier;
import model.CustomerManagement.CustomerProfile;
import model.ProductManagement.SolutionOffer;
/**
 *
 * @author kal bugrara
 */
public class MarketChannelAssignment {
    
    Market market;
    Channel channel;
    int adbudget;
    double targetrevenue;
    double actualrevenue;
    ArrayList<SolutionOffer> solutionOffers;

    
    public MarketChannelAssignment(Market m, Channel c){
        
        market = m;
        channel = c;
        solutionOffers = new ArrayList<>();
        actualrevenue = m.calculateMarketRevenueAfterDiscountByChannel();     
    }
    
    public void configureChannelSettings(double advertisingCost, double conversionRate) {
        channel.setAdvertisingCost(advertisingCost);
        channel.setConversionRate(conversionRate);
    }

    public SolutionOffer generateSolutionOffer(Supplier s){
        SolutionOffer newSolutionOffer = new SolutionOffer(this,s);

        return newSolutionOffer;
    }

    public Channel getChannel() {
        return channel;
    }

    public double getMarketActualrevenue() {
        return actualrevenue;
    }
    
    public double calculateProfit() {
        double totalCost = this.channel.getAdvertisingCost();
        double conversionRate = this.channel.getConversionRate();

        double profit = this.getMarketActualrevenue() - (totalCost * conversionRate);
        return profit;
    }
    
    
    
}
