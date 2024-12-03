

package com.pandemic.a;

import com.pandemic.b.Prevention;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PreventionDao extends BaseDao{
    public List getAllPrevention() throws Exception{
        List preventionList = new ArrayList();
        String sql= " select m_id,prevention.r_id,m_number,m_data,m_result" +
                " from prevention ";
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            String m_id = resultSet.getString("m_id");
            String r_id = resultSet.getString("r_id");
            String m_number = resultSet.getString("m_number");
            String m_data = resultSet.getString("m_data");
            String m_result = resultSet.getString("m_result");
            Prevention prevention= new Prevention(m_id,r_id,m_number,m_data,m_result);
            preventionList.add(prevention);
        }
        return preventionList;
    }
    public boolean addPrevention(Prevention prevention) throws Exception{
        String sql = "insert into prevention(m_id,r_id,m_number,m_data,m_result)" +
                " VALUES(?,?,?,?,?) ";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement .setString(1,prevention.getM_id());
        preparedStatement .setString(2,prevention.getR_id());
        preparedStatement .setString(3,prevention.getM_number());
        preparedStatement .setString(4,prevention.getM_data());
        preparedStatement .setString(5,prevention.getM_result());
        int num = preparedStatement.executeUpdate();//执行sql语句
        if (num==1)
        {
            return true;
        }else {
            return false;
        }
    }
    public String deletePrevention(Prevention prevention) throws Exception{
        String result=null;
        Connection connection = getConnection();
        String sql = "delete from prevention where m_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement .setString(1,prevention.getM_id());
        int num = preparedStatement.executeUpdate();//执行删除sql操作
        if(num==1){
                   result = "Deletion successful";
               }else {
                   result = "Deletion failed";
               }
               return result;//返回操作结果
    }
    public boolean modifyMrescpition(Prevention prevention){
        int i =0;
        try{
            Connection connection =getConnection();
            String updatesql = "update prevention set r_id=?,"
                    +"m_number=?,"
                    +"m_data=?,"
                    +"m_result=?"
                    +" where m_id=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(updatesql);
            preparedStatement.setString(1,prevention.getR_id());
            preparedStatement.setString(2,prevention.getM_number());
            preparedStatement.setString(3,prevention.getM_data());
            preparedStatement.setString(4,prevention.getM_result());
            preparedStatement.setString(5,prevention.getM_id());
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
