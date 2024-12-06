


package com.pandemic.b;
public class Doctor {
    String d_id; //医生ID
    String a_id; //医院ID
    String d_name; //医生姓名
    String d_sex; //医生性别
    int d_age; //医生年龄
    String d_type; //医生所属科室
    String d_price; //医生挂号价格
    String d_type1; //医生职称
    /**
     * 构造方法，初始化医生信息
     * @param d_id 医生ID
     * @param a_id 医院ID
     * @param d_name 医生姓名
     * @param d_sex 医生性别
     * @param d_age 医生年龄
     * @param d_type 医生所属科室
     * @param d_price 医生挂号价格
     * @param d_type1 医生职称
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
     * 无参构造方法
     */
    public Doctor() {
    }
    /**
     * 获取医生ID
     * @return 医生ID
     */
    public String getD_id() {
        return d_id;
    }
    /**
     * 设置医生ID
     * @param d_id 医生ID
     */
    public void setD_id(String d_id) {
        this.d_id = d_id;
    }
    /**
     * 获取医院ID
     * @return 医院ID
     */
    public String getA_id() {
        return a_id;
    }
    /**
     * 设置医院ID
     * @param a_id 医院ID
     */
    public void setA_id(String a_id) {
        this.a_id = a_id;
    }
    /**
     * 获取医生姓名
     * @return 医生姓名
     */
    public String getD_name() {
        return d_name;
    }
    /**
     * 设置医生姓名
     * @param d_name 医生姓名
     */
    public void setD_name(String d_name) {
        this.d_name = d_name;
    }
    /**
     * 获取医生性别
     * @return 医生性别
     */
    public String getD_sex() {
        return d_sex;
    }
    /**
     * 设置医生性别
     * @param d_sex 医生性别
     */
    public void setD_sex(String d_sex) {
        this.d_sex = d_sex;
    }
    /**
     * 获取医生年龄
     * @return 医生年龄
     */
    public int getD_age() {
        return d_age;
    }
    /**
     * 设置医生年龄
     * @param d_age 医生年龄
     */
    public void setD_age(int d_age) {
        this.d_age = d_age;
    }
    /**
     * 获取医生所属科室
     * @return 医生所属科室
     */
    public String getD_type() {
        return d_type;
    }
    /**
     * 设置医生所属科室
     * @param d_tpye 医生所属科室
     */
    public void setD_tpye(String d_tpye) {
        this.d_type = d_tpye;
    }
    /**
     * 获取医生挂号价格
     * @return 医生挂号价格
     */
    public String getD_price() {
        return d_price;
    }
    /**
     * 设置医生挂号价格
     * @param d_price 医生挂号价格
     */
    public void setD_price(String d_price) {
        this.d_price = d_price;
    }
    /**
     * 获取医生职称
     * @return 医生职称
     */
    public String getD_type1() {
        return d_type1;
    }
    /**
     * 设置医生职称
     * @param d_type1 医生职称
     */
    public void setD_type1(String d_type1) {
        this.d_type1 = d_type1;
    }
}
