


package com.pandemic.b;
public class Doctor {
    String d_id; 
    String a_id; 
    String d_name; 
    String d_sex; 
    int d_age; 
    String d_type; 
    String d_price; 
    String d_type1; 
    /**
    
     * @param d_id 
     * @param a_id 
     * @param d_name 
     * @param d_sex 
     * @param d_age 
     * @param d_type 
     * @param d_price 
     * @param d_type1 
     */
    public Doctor(String d_id, String a_id, String d_name, String d_sex, int d_age, String d_type, String d_price, String d_type1) {
        this.d_id = d_id;
        this.a_id = a_id;
        this.d_name = d_name;
        this.d_sex = d_sex;
        this.d_age = d_age;
        this.d_type = d_type;
        this.d_price = d_price;
        this.d_type1 = d_type1;
    }
    /**
     * 
     */
    public Doctor() {
    }
    /**
     * 
     * 
     */
    public String getD_id() {
        return d_id;
    }
  
     
    public void setD_id(String d_id) {
        this.d_id = d_id;
    }
    
    public String getA_id() {
        return a_id;
    }
 
    public void setA_id(String a_id) {
        this.a_id = a_id;
    }
  
    public String getD_name() {
        return d_name;
    }
  
    public void setD_name(String d_name) {
        this.d_name = d_name;
    }
  
    public String getD_sex() {
        return d_sex;
    }
  
    public void setD_sex(String d_sex) {
        this.d_sex = d_sex;
    }
   
    public int getD_age() {
        return d_age;
    }
   
    public void setD_age(int d_age) {
        this.d_age = d_age;
    }
    
   
    public String getD_type() {
        return d_type;
    }
    
    public void setD_tpye(String d_tpye) {
        this.d_type = d_tpye;
    }
   
    public String getD_price() {
        return d_price;
    }
   
    public void setD_price(String d_price) {
        this.d_price = d_price;
    }
    
    public String getD_type1() {
        return d_type1;
    }
    
    public void setD_type1(String d_type1) {
        this.d_type1 = d_type1;
    }

    public String getGraduationSchool() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getExperienceYears() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
