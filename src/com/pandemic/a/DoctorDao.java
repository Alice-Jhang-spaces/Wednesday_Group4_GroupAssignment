

package com.pandemic.a;
import com.pandemic.b.Doctor;
import com.pandemic.b.Patient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class DoctorDao extends BaseDao{
    public List getAllDoctor() throws Exception{
        List doctorList = new ArrayList();
        String sql= " select dd_id,doctor.a_id,dd_name,dd_sex,dd_age,dd_type,dd_price,dd_type1" +
                " from doctor ";//inner join administrative on doctor.a_id = administrative.a_id
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            String dd_id = resultSet.getString("dd_id");
            String a_id = resultSet.getString("a_id");
            String dd_name = resultSet.getString("dd_name");
            String dd_sex = resultSet.getString("dd_sex");
            int dd_age = Integer.parseInt(String.valueOf(resultSet.getInt("dd_age")));
            String dd_type = resultSet.getString("dd_type");
            String dd_price = resultSet.getString("dd_price");
            String dd_type1 = resultSet.getString("dd_type1");
            Doctor doctor= new Doctor(dd_id,a_id,dd_name,dd_sex,dd_age,dd_type,dd_price,dd_type1);
            doctorList.add(doctor);
        }
        return doctorList;
    }
    public boolean addDoctor(Doctor doctor) throws Exception{
        String sql = "insert into doctor(dd_id,a_id,dd_name,dd_sex,dd_age,dd_type,dd_price,dd_type1)" +
                " VALUES(?,?,?,?,?,?,?,?) ";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement .setString(1,doctor.getD_id());
        preparedStatement .setString(2,doctor.getA_id());
        preparedStatement .setString(3,doctor.getD_name());
        preparedStatement .setString(4,doctor.getD_sex());
        preparedStatement .setInt(5,doctor.getD_age());
        preparedStatement .setString(6,doctor.getD_type());
        preparedStatement .setString(7,doctor.getD_price());
        preparedStatement .setString(8,doctor.getD_type1());
        int num = preparedStatement.executeUpdate();
        if (num==1)
        {
            return true;
        }else {
            return false;
        }
    }
    public String deleteDoctor(Doctor doctor) throws Exception{
        String result=null;
        Connection connection = getConnection();
        String sql = "delete from doctor where dd_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement .setString(1,doctor.getD_id());
        int num = preparedStatement.executeUpdate();
        if(num==1){
            result = "Deletion successful";
        }else {
            result = "Deletion failed";
        }
        return result;
    }
    public boolean modifyDoctor(Doctor doctor){
        int i =0;
        try{
            Connection connection =getConnection();
            String updatesql = "update doctor set a_id=?,"
                    +"dd_name=?,"
                    +"dd_sex=?,"
                    +"dd_age=?,"
                    +"dd_type=?,"
                    +"dd_price=?,"
                    +"dd_type1=?"
                    +" where dd_id=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(updatesql);
            preparedStatement.setString(1,doctor.getA_id());
            preparedStatement.setString(2,doctor.getD_name());
            preparedStatement.setString(3,doctor.getD_sex());
            preparedStatement.setInt(4,doctor.getD_age());
            preparedStatement.setString(5,doctor.getD_type());
            preparedStatement.setString(6,doctor.getD_price());
            preparedStatement.setString(7,doctor.getD_type1());
            preparedStatement.setString(8,doctor.getD_id());
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
