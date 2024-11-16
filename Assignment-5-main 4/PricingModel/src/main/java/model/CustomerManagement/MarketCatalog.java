/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import model.MarketModel.Market;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class MarketCatalog {

    ArrayList<Market> markets;

    public MarketCatalog() {
        markets = new ArrayList<>();
    }

    public void addMarkettoCatalog(Market m) {
        markets.add(m);
    }

    public int getSize() {
        return markets.size();
    }

    public ArrayList<Market> getMarketList() {
        return markets;
    }
    
}
