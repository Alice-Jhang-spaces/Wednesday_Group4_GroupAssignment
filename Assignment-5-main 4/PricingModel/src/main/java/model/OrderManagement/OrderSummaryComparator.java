/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.OrderManagement;

import java.util.Comparator;

public class OrderSummaryComparator implements Comparator<OrderSummary> {

    @Override
    public int compare(OrderSummary o1, OrderSummary o2) {
        
        if (Integer.compare(o2.numberOfItems, o1.numberOfItems) != 0) return Integer.compare(o2.numberOfItems, o1.numberOfItems);

        return Integer.compare(o2.salesvolume, o1.salesvolume);
    }
    
}

