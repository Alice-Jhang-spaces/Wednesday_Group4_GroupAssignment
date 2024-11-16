/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Personnel;

import model.MarketModel.Market;

/**
 *
 * @author kal bugrara
 */
public class Person {

    String id;
    String continents;
    String gender;
    String ageGroup;
    Market market;


    public Person(String id,String continents, String gender, String ageGroupString) {

        this.id = id;
        this.continents = continents;
        this.gender = gender;
        this.ageGroup = ageGroupString;
    }

    public String getPersonId() {
        return id;
    }

    public boolean isMatch(String id) {
        if (getPersonId().equals(id)) {
            return true;
        }
        return false;
    }

    public String getContinents() {
        return continents;
    }

    public String getGender(){
        return gender;
    }

    public String getAgeGroup(){
        return ageGroup;
    }

    public Market getMarket() {
        return market;
    }



    @Override
    public String toString() {
        return getPersonId();
    }
}
