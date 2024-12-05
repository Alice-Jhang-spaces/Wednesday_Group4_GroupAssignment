
package com.pandemic.b;
public class Admin {
    String a_id;
    String a_name;
    public Admin(String a_id, String a_name) {
        this.a_id = a_id;
        this.a_name = a_name;
    }
    public Admin() { }
    public String getA_id() {
        return a_id;
    }
    public void setA_id(String a_id) {
        this.a_id = a_id;
    }
    public String getA_name() {
        return a_name;
    }
    public void setA_name(String a_name) {
        this.a_name = a_name;
    }
}
