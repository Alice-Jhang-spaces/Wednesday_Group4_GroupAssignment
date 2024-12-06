

package com.pandemic.a;
import com.pandemic.b.Mrescription;
import com.pandemic.b.Registration;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class RegistrationDao extends BaseDao{
    public List getAllRegistration() throws Exception{
        List registrationList = new ArrayList();
        String sql= " select r_id,registration.p_id,dd_id,r_data,r_price_id" +
                " from registration ";//inner join patient on patient.p_id = registration.p_id
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            String r_id = resultSet.getString("r_id");
            String p_id = resultSet.getString("p_id");
            String dd_id = resultSet.getString("dd_id");
            String r_data = resultSet.getString("r_data");
            String r_price_id = resultSet.getString("r_price_id");
            Registration registration= new Registration(r_id,p_id,dd_id,r_data,r_price_id);
            registrationList.add(registration);
        }
        return registrationList;
    }
    public boolean addRegistration(Registration registration) throws Exception{
        String sql = "insert into registration(r_id,p_id,dd_id,r_data,r_price_id)" +
                " VALUES(?,?,?,?,?) ";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement .setString(1,registration.getR_id());
        preparedStatement .setString(2,registration.getP_id());
        preparedStatement .setString(3,registration.getDd_id());
        preparedStatement .setString(4,registration.getR_data());
        preparedStatement .setString(5,registration.getR_price_id());
        int num = preparedStatement.executeUpdate();//执行sql语句
        if (num==1)
        {
            return true;
        }else {
            return false;
        }
    }
    public String deleteRegistration(Registration registration) throws Exception{
        String result=null;
        Connection connection = getConnection();
        String sql = "delete from registration where r_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement .setString(1,registration.getR_id());
        int num = preparedStatement.executeUpdate();//执行删除sql操作
        if(num==1){
                   result = "Deletion successful";
               }else {
                   result = "Deletion failed";
               }
               return result;//返回操作结果
        }
    public boolean modifyRegistration(Registration registration){
        int i =0;
        try{
            Connection connection =getConnection();
            String updatesql = "update registration set p_id=?,"
                    +"dd_id=?,"
                    +"r_data=?,"
                    +"r_price_id=?"
                    +" where r_id=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(updatesql);
            preparedStatement.setString(1,registration.getP_id());
            preparedStatement.setString(2,registration.getDd_id());
            preparedStatement.setString(3,registration.getR_data());
            preparedStatement.setString(4,registration.getR_price_id());
            preparedStatement.setString(5,registration.getR_id());
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
