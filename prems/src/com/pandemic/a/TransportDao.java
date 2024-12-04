

package com.pandemic.a;

import com.pandemic.b.Transport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TransportDao extends BaseDao{
    public List getAllTransport() throws Exception{
        List transportList = new ArrayList();
        String sql= " select t_id,title,t_status,t_data,t_result" +
                " from transport ";
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            String m_id = resultSet.getString("t_id");
            String r_id = resultSet.getString("title");
            String m_number = resultSet.getString("t_status");
            String m_data = resultSet.getString("t_data");
            String m_result = resultSet.getString("t_result");
            Transport transport= new Transport(m_id,r_id,m_number,m_data,m_result);
            transportList.add(transport);
        }
        return transportList;
    }
    public boolean addTransport(Transport transport) throws Exception{
        String sql = "insert into transport(t_id,title,t_status,t_data,t_result)" +
                " VALUES(?,?,?,?,?) ";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement .setString(1,transport.getM_id());
        preparedStatement .setString(2,transport.getR_id());
        preparedStatement .setString(3,transport.getM_number());
        preparedStatement .setString(4,transport.getM_data());
        preparedStatement .setString(5,transport.getM_result());
        int num = preparedStatement.executeUpdate();//执行sql语句
        if (num==1)
        {
            return true;
        }else {
            return false;
        }
    }
    public String deleteTransport(Transport transport) throws Exception{
        String result=null;
        Connection connection = getConnection();
        String sql = "delete from transport where t_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement .setString(1,transport.getM_id());
        int num = preparedStatement.executeUpdate();//执行删除sql操作
             if(num==1){
            result = "Deletion successful";
        }else {
            result = "Deletion failed";
        }
        return result;//返回操作结果
    }
    public boolean modifyTransport(Transport transport){
        int i =0;
        try{
            Connection connection =getConnection();
            String updatesql = "update transport set title=?,"
                    +"t_status=?,"
                    +"t_data=?,"
                    +"t_result=?"
                    +" where t_id=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(updatesql);
            preparedStatement.setString(1,transport.getR_id());
            preparedStatement.setString(2,transport.getM_number());
            preparedStatement.setString(3,transport.getM_data());
            preparedStatement.setString(4,transport.getM_result());
            preparedStatement.setString(5,transport.getM_id());
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
