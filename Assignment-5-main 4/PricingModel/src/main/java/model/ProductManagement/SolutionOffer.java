/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;

import model.MarketModel.MarketChannelAssignment;
import model.Supplier.Supplier;

/**
 *
 * @author kal bugrara
 */
public class SolutionOffer {
    ArrayList<Product> products;
    int price;// floor, ceiling, and target ideas
    MarketChannelAssignment marketChannelComb;
    Supplier supplier;

    public SolutionOffer(MarketChannelAssignment m, Supplier s) {
        marketChannelComb = m;
        products = new ArrayList<Product>();
        supplier = s;
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public void setPrice(int p) {
        price = p;

    }

    public Supplier getSupplier() {
        return supplier;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
    
}
