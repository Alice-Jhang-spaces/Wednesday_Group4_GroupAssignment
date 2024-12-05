

package com.pandemic.b;
public class Dss {
    String D_id; 
    String D_name; 
    String D_type;
    int D_number; 
    String D_id_id; 
    String D_unit; 
    String D_spec; 
    String D_price; 
    public Dss(String d_id, String d_name, String d_type, int d_number, String d_id_id, String d_unit, String d_spec, String d_price) {
        D_id = d_id;
        D_name = d_name;
        D_type = d_type;
        D_number = Integer.parseInt(String.valueOf(d_number)); 
        D_id_id = d_id_id;
        D_unit = d_unit;
        D_spec = d_spec;
        D_price = d_price;
    }
    public Dss() {
    }
    public String getD_id() {
        return D_id;
    }
    public void setD_id(String d_id) {
        D_id = d_id;
    }
    public String getD_name() {
        return D_name;
    }
    public void setD_name(String d_name) {
        D_name = d_name;
    }
    public String getD_type() {
        return D_type;
    }
    public void setD_type(String d_type) {
        D_type = d_type;
    }
    public int getD_number() {
        return D_number;
    }
    public void setD_number(int d_number) {
        D_number = d_number;
    }
    public String getD_id_id() {
        return D_id_id;
    }
    public void setD_id_id(String d_id_id) {
        D_id_id = d_id_id;
    }
    public String getD_unit() {
        return D_unit;
    }
    public void setD_unit(String d_unit) {
        D_unit = d_unit;
    }
    public String getD_spec() {
        return D_spec;
    }
    public void setD_spec(String d_spec) {
        D_spec = d_spec;
    }
    public String getD_price() {
        return D_price;
    }
    public void setD_price(String d_price) {
        D_price = d_price;
    }
}
