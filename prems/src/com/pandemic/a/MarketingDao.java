

package com.pandemic.a;

import com.pandemic.b.Marketing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MarketingDao extends BaseDao{
    public List getAllMarketing() throws Exception{
        List marketingList = new ArrayList();
        String sql= " select * " +
                " from marketing ";
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            String m_id = resultSet.getString("m_id");
            String r_id = resultSet.getString("title");
            String m_number = resultSet.getString("productname");
            String m_data = resultSet.getString("m_data");
            String m_result = resultSet.getString("m_result");
            Marketing marketing= new Marketing(m_id,r_id,m_number,m_data,m_result);
            marketingList.add(marketing);
        }
        return marketingList;
    }
    public boolean addMarketing(Marketing marketing) throws Exception{
        String sql = "insert into marketing(m_id,title,productname,m_data,m_result)" +
                " VALUES(?,?,?,?,?) ";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement .setString(1,marketing.getM_id());
        preparedStatement .setString(2,marketing.getR_id());
        preparedStatement .setString(3,marketing.getM_number());
        preparedStatement .setString(4,marketing.getM_data());
        preparedStatement .setString(5,marketing.getM_result());
        int num = preparedStatement.executeUpdate();//执行sql语句
        if (num==1)
        {
            return true;
        }else {
            return false;
        }
    }
    public String deleteMarketing(Marketing marketing) throws Exception{
        String result=null;
        Connection connection = getConnection();
        String sql = "delete from marketing where m_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement .setString(1,marketing.getM_id());
        int num = preparedStatement.executeUpdate();//执行删除sql操作
        if(num==1){
            result = "Deletion successful";
        }else {
            result = "Deletion failed";
        }
        return result;
    }
    public boolean modifyMarketing(Marketing marketing){
        int i =0;
        try{
            Connection connection =getConnection();
            String updatesql = "update marketing set title=?,"
                    +"productname=?,"
                    +"m_data=?,"
                    +"m_result=?"
                    +" where m_id=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(updatesql);
            preparedStatement.setString(1,marketing.getR_id());
            preparedStatement.setString(2,marketing.getM_number());
            preparedStatement.setString(3,marketing.getM_data());
            preparedStatement.setString(4,marketing.getM_result());
            preparedStatement.setString(5,marketing.getM_id());
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
