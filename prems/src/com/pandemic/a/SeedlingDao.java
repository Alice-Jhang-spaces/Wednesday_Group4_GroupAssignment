<<<<<<< HEAD

package com.pandemic.a;

import com.pandemic.b.Seedling;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SeedlingDao extends BaseDao{

    public DefaultComboBoxModel<String> getComboBoxModel(){
       
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        try {
            List allSeedling = getAllSeedling();
            for(Object obj:allSeedling){
                Seedling seedling=(Seedling)obj;
                model.addElement(seedling.getR_id());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return model;
    }

    public List getAllSeedling() throws Exception{
        List seedlingList = new ArrayList();
        String sql= " select m_id,seedling.r_id,m_number,m_data,m_result" +
                " from seedling ";
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            String m_id = resultSet.getString("m_id");
            String r_id = resultSet.getString("r_id");
            String m_number = resultSet.getString("m_number");
            String m_data = resultSet.getString("m_data");
            String m_result = resultSet.getString("m_result");
            Seedling seedling= new Seedling(m_id,r_id,m_number,m_data,m_result);
            seedlingList.add(seedling);
        }
        return seedlingList;
    }
    public boolean addSeedling(Seedling seedling) throws Exception{
        String sql = "insert into seedling(m_id,r_id,m_number,m_data,m_result)" +
                " VALUES(?,?,?,?,?) ";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement .setString(1,seedling.getM_id());
        preparedStatement .setString(2,seedling.getR_id());
        preparedStatement .setString(3,seedling.getM_number());
        preparedStatement .setString(4,seedling.getM_data());
        preparedStatement .setString(5,seedling.getM_result());
        int num = preparedStatement.executeUpdate();//执行sql语句
        if (num==1)
        {
            return true;
        }else {
            return false;
        }
    }
    public String deleteSeedling(Seedling seedling) throws Exception{
        String result=null;
        Connection connection = getConnection();
        String sql = "delete from seedling where m_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement .setString(1,seedling.getM_id());
        int num = preparedStatement.executeUpdate();
         if(num==1){
            result = "Deletion successful";
        }else {
            result = "Deletion failed";
        }
        return result;
    }
    public boolean modifySeedling(Seedling seedling){
        int i =0;
        try{
            Connection connection =getConnection();
            String updatesql = "update seedling set r_id=?,"
                    +"m_number=?,"
                    +"m_data=?,"
                    +"m_result=?"
                    +" where m_id=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(updatesql);
            preparedStatement.setString(1,seedling.getR_id());
            preparedStatement.setString(2,seedling.getM_number());
            preparedStatement.setString(3,seedling.getM_data());
            preparedStatement.setString(4,seedling.getM_result());
            preparedStatement.setString(5,seedling.getM_id());
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
=======

package com.pandemic.a;

import com.pandemic.b.Seedling;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SeedlingDao extends BaseDao{

    public DefaultComboBoxModel<String> getComboBoxModel(){
       
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        try {
            List allSeedling = getAllSeedling();
            for(Object obj:allSeedling){
                Seedling seedling=(Seedling)obj;
                model.addElement(seedling.getR_id());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return model;
    }

    public List getAllSeedling() throws Exception{
        List seedlingList = new ArrayList();
        String sql= " select m_id,seedling.r_id,m_number,m_data,m_result" +
                " from seedling ";
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            String m_id = resultSet.getString("m_id");
            String r_id = resultSet.getString("r_id");
            String m_number = resultSet.getString("m_number");
            String m_data = resultSet.getString("m_data");
            String m_result = resultSet.getString("m_result");
            Seedling seedling= new Seedling(m_id,r_id,m_number,m_data,m_result);
            seedlingList.add(seedling);
        }
        return seedlingList;
    }
    public boolean addSeedling(Seedling seedling) throws Exception{
        String sql = "insert into seedling(m_id,r_id,m_number,m_data,m_result)" +
                " VALUES(?,?,?,?,?) ";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement .setString(1,seedling.getM_id());
        preparedStatement .setString(2,seedling.getR_id());
        preparedStatement .setString(3,seedling.getM_number());
        preparedStatement .setString(4,seedling.getM_data());
        preparedStatement .setString(5,seedling.getM_result());
        int num = preparedStatement.executeUpdate();//执行sql语句
        if (num==1)
        {
            return true;
        }else {
            return false;
        }
    }
    public String deleteSeedling(Seedling seedling) throws Exception{
        String result=null;
        Connection connection = getConnection();
        String sql = "delete from seedling where m_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement .setString(1,seedling.getM_id());
        int num = preparedStatement.executeUpdate();
         if(num==1){
            result = "Deletion successful";
        }else {
            result = "Deletion failed";
        }
        return result;
    }
    public boolean modifySeedling(Seedling seedling){
        int i =0;
        try{
            Connection connection =getConnection();
            String updatesql = "update seedling set r_id=?,"
                    +"m_number=?,"
                    +"m_data=?,"
                    +"m_result=?"
                    +" where m_id=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(updatesql);
            preparedStatement.setString(1,seedling.getR_id());
            preparedStatement.setString(2,seedling.getM_number());
            preparedStatement.setString(3,seedling.getM_data());
            preparedStatement.setString(4,seedling.getM_result());
            preparedStatement.setString(5,seedling.getM_id());
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
>>>>>>> c5ff5cb2b8f6e43d690bbe43b4cad48f7889cae4
