

package com.pandemic.a;
import com.pandemic.b.Patient;//引入Patient类
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class PatientDao extends BaseDao{
    public List getAllPatient() throws Exception{
        List patientList = new ArrayList();
        String sql= " select p_id,p_name,p_sex,p_age " +
                " from patient ";
        Connection connection = getConnection();//获取连接
        Statement statement = connection.createStatement();//创建Statement对象
        ResultSet resultSet = statement.executeQuery(sql);//执行查询语句
        while (resultSet.next()){
            String p_id = resultSet.getString("p_id");
            String p_name = resultSet.getString("p_name");
            String p_sex = resultSet.getString("p_sex");
            String p_age = String.valueOf(resultSet.getInt("p_age"));
            Patient patient= new Patient(p_id,p_name,p_sex,p_age);//构造Patient对象
            patientList.add(patient);//将Patient对象添加到patientList中
        }
        return patientList;//返回Patient列表
    }
    public boolean addPatient(Patient patient) throws Exception{
        String sql = "insert into patient(p_id,p_name,p_sex,p_age)" +
                " VALUES(?,?,?,?) ";
        Connection connection = getConnection();//获取连接
        PreparedStatement preparedStatement = connection.prepareStatement(sql);//创建PreparedStatement对象
        preparedStatement .setString(1,patient.getP_id());//向sql语句中的第1个参数赋值
        preparedStatement .setString(2,patient.getP_name());//向sql语句中的第2个参数赋值
        preparedStatement .setString(3,patient.getP_sex());//向sql语句中的第3个参数赋值
        preparedStatement .setString(4,patient.getP_age());//向sql语句中的第4个参数赋值
        int num = preparedStatement.executeUpdate();//执行sql语句
        if (num==1)
        {
            return true;//插入成功
        }else {
            return false;//插入失败
        }
    }
    public String deletePatient(Patient patient) throws Exception{
        String result=null;
        Connection connection = getConnection();//获取连接
        String sql = "delete from patient where p_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);//创建PreparedStatement对象
        preparedStatement .setString(1,patient.getP_id());//向sql语句中的第1个参数赋值
        int num = preparedStatement.executeUpdate();//执行删除sql操作
        if(num==1){
            result = "Deletion successful";
        }else {
            result = "Deletion failed";
        }
        return result;//返回操作结果
    }
    public boolean modifyPatient(Patient patient){
        int i =0;
        try{
            Connection connection =getConnection();//获取连接
            String updatesql = "update patient set p_name=?,"
                    +"p_sex=?,"
                    +"p_age=?"
                    +" where p_id=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(updatesql);//创建PreparedStatement对象
            preparedStatement.setString(1,patient.getP_name());//向sql语句中的第1个参数赋值
            preparedStatement.setString(2,patient.getP_sex());//向sql语句中的第2个参数赋值
            preparedStatement.setString(3,patient.getP_age());//向sql语句中的第3个参数赋值
            preparedStatement.setString(4,patient.getP_id());//向sql语句中的第4个参数赋值
            i = preparedStatement.executeUpdate();//执行更新sql操作
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i == 1){
            return true;//更新成功
        }else {
            return false;//更新失败
        }
    }
}
