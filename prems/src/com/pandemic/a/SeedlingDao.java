/*
针对 `SeedlingDao.java` 文件的代码功能解释如下：

这是一个数据访问对象（DAO）类，用于操作 `Seedling` 数据模型在数据库中的相关记录。该类继承了 `BaseDao` 类，并实现了对医疗处方（Seedling）的增删改查操作。

1. `getAllSeedling()` 方法：


	* 功能：从数据库中查询所有医疗处方记录。
	* 实现：通过SQL查询语句从 `seedling` 表和 `registration` 表进行内连接查询，获取所有医疗处方信息，并将每条记录封装成一个 `Seedling` 对象，添加到列表中返回。
2. `addSeedling(Seedling seedling)` 方法：


	* 功能：向数据库中添加一个新的医疗处方记录。
	* 实现：使用预编译SQL语句（PreparedStatement）插入新的医疗处方信息到数据库中。如果插入成功，返回true；否则返回false。
3. `deleteSeedling(Seedling seedling)` 方法：


	* 功能：根据提供的医疗处方ID从数据库中删除对应的记录。
	* 实现：使用预编译SQL语句删除指定ID的医疗处方记录。删除成功后返回“删除成功”，否则返回“删除失败”。
4. `modifySeedling(Seedling seedling)` 方法：


	* 功能：更新数据库中指定医疗处方的信息。
	* 实现：使用预编译SQL语句更新医疗处方的信息。如果更新成功，返回true；否则返回false。注意，这个方法名似乎有个小错误，应该是 `modifySeedling` 而不是 `modifySeedling`。

注意：这个类中的方法都可能会抛出异常（Exception），因此在调用这些方法时需要进行异常处理。
*/

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
        // 创建 DefaultComboBoxModel 并使用 ArrayList 填充数据
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
        int num = preparedStatement.executeUpdate();//执行删除sql操作
         if(num==1){
            result = "Deletion successful";
        }else {
            result = "Deletion failed";
        }
        return result;//返回操作结果
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
