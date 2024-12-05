

package com.pandemic.b;
public class Patient {
    private  String p_id;     
    private  String p_name;   
    private  String p_sex;    
    private  String p_age;   
    public Patient() {
    }
    public Patient(String p_id, String p_name, String p_sex, String p_age) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_sex = p_sex;
        this.p_age = p_age;
    }
    public String getP_id() {
        return p_id;
    }
    public void setP_id(String p_id) {
        this.p_id = p_id;
    }
    public String getP_name() {
        return p_name;
    }
    public void setP_name(String p_name) {
        this.p_name = p_name;
    }
    public String getP_sex() {
        return p_sex;
    }
    public void setP_sex(String p_sex) {
        this.p_sex = p_sex;
    }
    public String getP_age() {
        return p_age;
    }
    public void setP_age(String p_age) {
        this.p_age = p_age;
    }
}
