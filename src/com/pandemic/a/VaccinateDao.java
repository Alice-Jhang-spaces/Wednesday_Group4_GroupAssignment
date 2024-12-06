

package com.pandemic.a;

import com.pandemic.b.Vaccinate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VaccinateDao extends BaseDao{
    public List getAllVaccinate() throws Exception{
        List vaccinateList = new ArrayList();
        String sql= " select * " +
                " from vaccinate ";
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            String m_id = resultSet.getString("v_id");
            String r_id = resultSet.getString("name");
            String m_number = resultSet.getString("productname");
            String m_data = resultSet.getString("v_data");
            String m_result = resultSet.getString("v_result");
            Vaccinate vaccinate= new Vaccinate(m_id,r_id,m_number,m_data,m_result);
            vaccinateList.add(vaccinate);
        }
        return vaccinateList;
    }
    public boolean addVaccinate(Vaccinate vaccinate) throws Exception{
        String sql = "insert into vaccinate(v_id,name,productname,v_data,v_result)" +
                " VALUES(?,?,?,?,?) ";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement .setString(1,vaccinate.getM_id());
        preparedStatement .setString(2,vaccinate.getR_id());
        preparedStatement .setString(3,vaccinate.getM_number());
        preparedStatement .setString(4,vaccinate.getM_data());
        preparedStatement .setString(5,vaccinate.getM_result());
        int num = preparedStatement.executeUpdate();//执行sql语句
        if (num==1)
        {
            return true;
        }else {
            return false;
        }
    }
    public String deleteVaccinate(Vaccinate vaccinate) throws Exception{
        String result=null;
        Connection connection = getConnection();
        String sql = "delete from vaccinate where v_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement .setString(1,vaccinate.getM_id());
        int num = preparedStatement.executeUpdate();//执行删除sql操作
        if(num==1){
            result = "Deletion successful";
        }else {
            result = "Deletion failed";
        }
        return result;//返回操作结果
    }
    public boolean modifyVaccinate(Vaccinate vaccinate){
        int i =0;
        try{
            Connection connection =getConnection();
            String updatesql = "update vaccinate set name=?,"
                    +"productname=?,"
                    +"v_data=?,"
                    +"v_result=?"
                    +" where v_id=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(updatesql);
            preparedStatement.setString(1,vaccinate.getR_id());
            preparedStatement.setString(2,vaccinate.getM_number());
            preparedStatement.setString(3,vaccinate.getM_data());
            preparedStatement.setString(4,vaccinate.getM_result());
            preparedStatement.setString(5,vaccinate.getM_id());
            i = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i == 1){
            return true;
        }else {
            return false;
        }
    }
}
