/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import java.util.ArrayList;
import java.util.Random;

import model.Business.Business;
import model.MarketModel.Market;
import model.Personnel.Person;

/**
 *
 * @author kal bugrara
 */
public class CustomerDirectory {

    Business business;
    ArrayList<CustomerProfile> customerlist;

    public CustomerDirectory(Business d) {

        business = d;
        customerlist = new ArrayList<CustomerProfile>();
    }

    public CustomerProfile newCustomerProfile(Person p) {

        CustomerProfile sp = new CustomerProfile(p);
        customerlist.add(sp);
        return sp;
    }

    public CustomerProfile findCustomer(String id) {

        for (CustomerProfile sp : customerlist) {

            if (sp.isMatch(id)) {
                return sp;
            }
        }
            return null; //not found after going through the whole list
         }
        public CustomersReport generatCustomerPerformanceReport(){
        CustomersReport customersreport = new CustomersReport();
    
        for(CustomerProfile cp: customerlist){
            
            CustomerSummary cs = new CustomerSummary(cp);
            customersreport.addCustomerSummary(cs);
        }
        return customersreport; 
    }

    public CustomerProfile pickRandomCustomer(){
        if (customerlist.size() == 0) return null;
        Random r = new Random();
        int randomIndex = r.nextInt(customerlist.size());
        return customerlist.get(randomIndex);
    }

    public ArrayList<CustomerProfile> getCustomerList() {
        return customerlist;
    }


//    public void printShortInfo(){
//        // System.out.println("Checking what's inside the Customer directory.");
//        // System.out.println("There are " + customerlist.size() + " customers.");
//        System.out.println("Check the market of customers");
//        for (CustomerProfile cp : customerlist) {
//            System.out.println("Customer Name: " + cp.getPerson().getPersonId() + "   Customer Market: " + cp.getMarket().getCharacteristics());
//        }
//    }
}
