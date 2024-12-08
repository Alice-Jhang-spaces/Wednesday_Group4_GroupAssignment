

package com.pandemic.b;
public class Transport {
    String m_id;
    String r_id;
    String m_number;
    String m_data;
    String m_result;
    public Transport(String m_id, String r_id, String m_number, String m_data, String m_result) {
        this.m_id = m_id;
        this.r_id = r_id;
        this.m_number = m_number;
        this.m_data = m_data;
        this.m_result = m_result;
    }
    public Transport() {
    }
    public String getM_id() {
        return m_id;
    }
    public void setM_id(String m_id) {
        this.m_id = m_id;
    }
    public String getR_id() {
        return r_id;
    }
    public void setR_id(String r_id) {
        this.r_id = r_id;
    }
    public String getM_number() {
        return m_number;
    }
    public void setM_number(String m_number) {
        this.m_number = m_number;
    }
    public String getM_data() {
        return m_data;
    }
    public void setM_data(String m_data) {
        this.m_data = m_data;
    }
    public String getM_result() {
        return m_result;
    }
    public void setM_result(String m_result) {
        this.m_result = m_result;
    }
    @Override
    public String toString() {
        return "mrescription{" +
                "m_id='" + m_id + '\'' +
                ", r_id='" + r_id + '\'' +
                ", m_number='" + m_number + '\'' +
                ", m_data='" + m_data + '\'' +
                ", m_result='" + m_result + '\'' +
                '}';
    }
}
