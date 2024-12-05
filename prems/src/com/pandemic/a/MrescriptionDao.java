

package com.pandemic.a;

import com.pandemic.b.Mrescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class MrescriptionDao extends BaseDao {
    public List getAllMrescription() throws Exception{
        List mrescriptionList = new ArrayList();
        String sql= " select m_id,mrescription.r_id,m_number,m_data,m_result" +
                " from mrescription ";//inner join registration on mrescription.r_id = registration.r_id
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            String m_id = resultSet.getString("m_id");
            String r_id = resultSet.getString("r_id");
            String m_number = resultSet.getString("m_number");
            String m_data = resultSet.getString("m_data");
            String m_result = resultSet.getString("m_result");
            Mrescription mrescription= new Mrescription(m_id,r_id,m_number,m_data,m_result);
            mrescriptionList.add(mrescription);
        }
        return mrescriptionList;
    }
    public boolean addMrescription(Mrescription mrescription) throws Exception{
        String sql = "insert into mrescription(m_id,r_id,m_number,m_data,m_result)" +
                " VALUES(?,?,?,?,?) ";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement .setString(1,mrescription.getM_id());
        preparedStatement .setString(2,mrescription.getR_id());
        preparedStatement .setString(3,mrescription.getM_number());
        preparedStatement .setString(4,mrescription.getM_data());
        preparedStatement .setString(5,mrescription.getM_result());
        int num = preparedStatement.executeUpdate();//执行sql语句
        if (num==1)
        {
            return true;
        }else {
            return false;
        }
    }
    public String deleteMrescription(Mrescription mrescription) throws Exception{
        String result=null;
        Connection connection = getConnection();
        String sql = "delete from mrescription where m_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement .setString(1,mrescription.getM_id());
        int num = preparedStatement.executeUpdate();//执行删除sql操作
        if(num==1){
            result = "Deletion successful";
        }else {
            result = "Deletion failed";
        }
        return result;
    }
    public boolean modifyMrescpition(Mrescription mrescription){
        int i =0;
        try{
            Connection connection =getConnection();
            String updatesql = "update mrescription set r_id=?,"
                    +"m_number=?,"
                    +"m_data=?,"
                    +"m_result=?"
                    +" where m_id=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(updatesql);
            preparedStatement.setString(1,mrescription.getR_id());
            preparedStatement.setString(2,mrescription.getM_number());
            preparedStatement.setString(3,mrescription.getM_data());
            preparedStatement.setString(4,mrescription.getM_result());
            preparedStatement.setString(5,mrescription.getM_id());
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
