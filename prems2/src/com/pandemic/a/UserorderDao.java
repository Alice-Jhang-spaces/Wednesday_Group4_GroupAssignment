

package com.pandemic.a;

import com.pandemic.b.Userorder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserorderDao extends BaseDao{
    public List getAllUserorder() throws Exception{
        List userorderList = new ArrayList();
        String sql= " select o_id,productname,num,o_data,o_result" +
                " from userorder ";
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            String m_id = resultSet.getString("o_id");
            String r_id = resultSet.getString("productname");
            String m_number = resultSet.getString("num");
            String m_data = resultSet.getString("o_data");
            String m_result = resultSet.getString("o_result");
            Userorder userorder= new Userorder(m_id,r_id,m_number,m_data,m_result);
            userorderList.add(userorder);
        }
        return userorderList;
    }
    public boolean addUserorder(Userorder userorder) throws Exception{
        String sql = "insert into userorder(o_id,productname,num,o_data,o_result)" +
                " VALUES(?,?,?,?,?) ";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement .setString(1,userorder.getM_id());
        preparedStatement .setString(2,userorder.getR_id());
        preparedStatement .setString(3,userorder.getM_number());
        preparedStatement .setString(4,userorder.getM_data());
        preparedStatement .setString(5,userorder.getM_result());
        int num = preparedStatement.executeUpdate();//执行sql语句
        if (num==1)
        {
            return true;
        }else {
            return false;
        }
    }
    public String deleteUserorder(Userorder userorder) throws Exception{
        String result=null;
        Connection connection = getConnection();
        String sql = "delete from userorder where o_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement .setString(1,userorder.getM_id());
        int num = preparedStatement.executeUpdate();//执行删除sql操作
        if(num==1){
                   result = "Deletion successful";
               }else {
                   result = "Deletion failed";
               }
               return result;//返回操作结果
        }
    public boolean modifyUserorder(Userorder userorder){
        int i =0;
        try{
            Connection connection =getConnection();
            String updatesql = "update userorder set productname=?,"
                    +"num=?,"
                    +"o_data=?,"
                    +"o_result=?"
                    +" where o_id=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(updatesql);
            preparedStatement.setString(1,userorder.getR_id());
            preparedStatement.setString(2,userorder.getM_number());
            preparedStatement.setString(3,userorder.getM_data());
            preparedStatement.setString(4,userorder.getM_result());
            preparedStatement.setString(5,userorder.getM_id());
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
