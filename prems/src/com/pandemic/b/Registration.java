

package com.pandemic.b;
public class Registration {
    String r_id;
    String p_id;
    String dd_id;
    String r_data;
    String r_price_id;
    public Registration(String r_id, String p_id, String dd_id, String r_data, String r_price_id) {
        this.r_id = r_id;
        this.p_id = p_id;
        this.dd_id = dd_id;
        this.r_data = r_data;
        this.r_price_id = r_price_id;
    }
    public Registration() {
    }
    public String getR_id() {
        return r_id;
    }
    public void setR_id(String r_id) {
        this.r_id = r_id;
    }
    public String getP_id() {
        return p_id;
    }
    public void setP_id(String p_id) {
        this.p_id = p_id;
    }
    public String getDd_id() {
        return dd_id;
    }
    public void setDd_id(String dd_id) {
        this.dd_id = dd_id;
    }
    public String getR_data() {
        return r_data;
    }
    public void setR_data(String r_data) {
        this.r_data = r_data;
    }
    public String getR_price_id() {
        return r_price_id;
    }
    public void setR_price_id(String r_price_id) {
        this.r_price_id = r_price_id;
    }
}
