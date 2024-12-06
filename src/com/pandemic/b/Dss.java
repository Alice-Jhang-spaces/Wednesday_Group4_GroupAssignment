

package com.pandemic.b;
public class Dss {
    String D_id; // 医疗器械编号
    String D_name; // 医疗器械名称
    String D_type; // 医疗器械类型
    int D_number; // 医疗器械数量
    String D_id_id; // 医疗器械ID
    String D_unit; // 医疗器械单位
    String D_spec; // 医疗器械规格
    String D_price; // 医疗器械价格
    public Dss(String d_id, String d_name, String d_type, int d_number, String d_id_id, String d_unit, String d_spec, String d_price) {
        D_id = d_id;
        D_name = d_name;
        D_type = d_type;
        D_number = Integer.parseInt(String.valueOf(d_number)); // 将传入的参数转换为整型
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
