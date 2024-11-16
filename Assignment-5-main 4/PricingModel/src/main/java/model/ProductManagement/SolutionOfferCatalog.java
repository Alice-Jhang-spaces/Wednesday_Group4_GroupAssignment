/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;

import model.MarketModel.MarketChannelAssignment;
import model.Supplier.Supplier;
import java.util.Random;

/**
 *
 * @author kal bugrara
 */
public class SolutionOfferCatalog {
    ArrayList<SolutionOffer> solutionoffers;

    public SolutionOfferCatalog() {
        solutionoffers = new ArrayList<SolutionOffer>();
    }

    public SolutionOffer newSolutionOffer(MarketChannelAssignment mca, Supplier s) {
        SolutionOffer so = new SolutionOffer(mca,s);
        solutionoffers.add(so);
        return so;
    }

    public void addSolutionOffer(SolutionOffer so) {
        if (!solutionoffers.contains(so)) {
            solutionoffers.add(so);
        }
    }

    public ArrayList<SolutionOffer> getSolutionOfferList(){
        return solutionoffers;
    }

    public SolutionOffer pickRandomSolutionOffer(){
        if (solutionoffers.size() == 0) {
            return null;
        }
        Random r = new Random();
        int randomIndex = r.nextInt(solutionoffers.size());
        return solutionoffers.get(randomIndex);
    }
    
}
