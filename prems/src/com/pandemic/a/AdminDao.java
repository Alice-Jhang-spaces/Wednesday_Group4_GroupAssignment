package com.pandemic.a;

import com.pandemic.b.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminDao extends BaseDao {

    /**
     * Retrieves all administrators from the database.
     *
     * @return A list of Admin objects representing all administrators.
     * @throws Exception If an error occurs during the database operation.
     */
    public List<Admin> getAllAdmin() throws Exception {
        List<Admin> adminList = new ArrayList<>();
        String sql = "SELECT a_id, a_name FROM administrative";
        Connection connection = getConnection(); // Get database connection
        Statement statement = connection.createStatement(); // Create a Statement object to execute SQL
        ResultSet resultSet = statement.executeQuery(sql); // Execute SQL and retrieve the result set

        while (resultSet.next()) {
            String a_id = resultSet.getString("a_id"); // Get admin ID
            String a_name = resultSet.getString("a_name"); // Get admin name
            Admin admin = new Admin(a_id, a_name); // Create an Admin object
            adminList.add(admin); // Add the Admin object to the list
        }
        return adminList; // Return the list of administrators
    }

    /**
     * Adds a new administrator to the database.
     *
     * @param admin The Admin object containing the new administrator's details.
     * @return true if the operation was successful, false otherwise.
     * @throws Exception If an error occurs during the database operation.
     */
    public boolean addAdmin(Admin admin) throws Exception {
        String sql = "INSERT INTO administrative(a_id, a_name) VALUES(?, ?)";
        Connection connection = getConnection(); // Get database connection
        PreparedStatement preparedStatement = connection.prepareStatement(sql); // Create a PreparedStatement to execute SQL
        preparedStatement.setString(1, admin.getA_id()); // Set the admin ID placeholder value
        preparedStatement.setString(2, admin.getA_name()); // Set the admin name placeholder value
        int num = preparedStatement.executeUpdate(); // Execute the SQL and get the number of affected rows

        return num == 1; // Return true if one row was affected, false otherwise
    }

    /**
     * Deletes an administrator from the database.
     *
     * @param admin The Admin object representing the administrator to be deleted.
     * @return A string message indicating success or failure.
     * @throws Exception If an error occurs during the database operation.
     */
    public String deleteAdmin(Admin admin) throws Exception {
        String result = null;
        Connection connection = getConnection(); // Get database connection
        String sql = "DELETE FROM administrative WHERE a_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql); // Create a PreparedStatement to execute SQL
        preparedStatement.setString(1, admin.getA_id()); // Set the admin ID placeholder value
        int num = preparedStatement.executeUpdate(); // Execute the SQL and get the number of affected rows

        if (num == 1) {
            result = "Deletion successful"; // Deletion was successful
        } else {
            result = "Deletion failed"; // Deletion failed
        }
        return result; // Return the result message
    }

    /**
     * Modifies the details of an existing administrator.
     *
     * @param admin The Admin object containing the updated details.
     * @return true if the operation was successful, false otherwise.
     */
    public boolean modifyAdmin(Admin admin) {
        int rowsAffected = 0;
        try {
            Connection connection = getConnection(); // Get database connection
            String updatesql = "UPDATE administrative SET a_name=? WHERE a_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updatesql); // Create a PreparedStatement to execute SQL
            preparedStatement.setString(1, admin.getA_name()); // Set the updated admin name placeholder value
            preparedStatement.setString(2, admin.getA_id()); // Set the admin ID placeholder value
            rowsAffected = preparedStatement.executeUpdate(); // Execute the SQL and get the number of affected rows
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected == 1; // Return true if one row was affected, false otherwise
    }
}
