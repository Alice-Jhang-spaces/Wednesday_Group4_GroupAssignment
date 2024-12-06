
package com.pandemic.a;

import com.pandemic.b.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends BaseDao {

    public boolean login(String userName, String password) throws Exception {
//        String sql = "SELECT id, name, password, role, orginization, enterprise, network " +
//                     "FROM user WHERE name = ? AND password = ? AND role = ? ";
        String sql = "SELECT id, name, password, role, orginization, enterprise, network " +
                     "FROM user WHERE name = ? AND password = ?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, password);
//        preparedStatement.setString(3, user.getRole());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    public List<User> getAllUser() throws Exception {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT id, name, password, role, orginization, enterprise, network FROM user";
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");
            String orginization = resultSet.getString("orginization");
            String enterprise = resultSet.getString("enterprise");
            String network = resultSet.getString("network");
            User user = new User(id, name, password, role, orginization, enterprise, network);
            userList.add(user);
        }
        return userList;
    }
    
    public User getUserByName(String username) throws Exception {
        User user = null;
        String sql = "SELECT id, name, password, role, orginization, enterprise, network " +
                     "FROM user WHERE name = ?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");
            String orginization = resultSet.getString("orginization");
            String enterprise = resultSet.getString("enterprise");
            String network = resultSet.getString("network");
            user = new User(id, name, password, role, orginization, enterprise, network);
        }
        return user;
    }


    public boolean addUser(User user) throws Exception {
        String sql = "INSERT INTO user (id, name, password, role, orginization, enterprise, network) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user.getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getRole());
        preparedStatement.setString(5, user.getOrginization());
        preparedStatement.setString(6, user.getEnterprise());
        preparedStatement.setString(7, user.getNetwork());
        int num = preparedStatement.executeUpdate();
        return num == 1;
    }

    public String deleteUser(User user) throws Exception {
        String result;
        String sql = "DELETE FROM user WHERE id = ?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user.getId());
        int num = preparedStatement.executeUpdate();
        if (num == 1) {
            result = "Deletion successful";
        } else {
            result = "Deletion failed";
        }
        return result;
    }

    public boolean modifyUser(User user) {
        int i = 0;
        try {
            Connection connection = getConnection();
            String updatesql = "UPDATE user SET name = ?, password = ?, role = ?, " +
                               "orginization = ?, enterprise = ?, network = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updatesql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setString(4, user.getOrginization());
            preparedStatement.setString(5, user.getEnterprise());
            preparedStatement.setString(6, user.getNetwork());
            preparedStatement.setInt(7, user.getId());
            i = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i == 1;
    }
}
