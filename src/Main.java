package com.pandemic.a;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        try {
            BaseDao baseDao = new BaseDao(); // Create BaseDao object
            Connection connection = baseDao.getConnection(); // Get connection object

            if (connection != null) {
                System.out.println("Database connection successful!");

                // Create a Statement object to execute queries
                Statement statement = connection.createStatement();
                
                // Example query: Retrieve MySQL version
                String query = "SELECT VERSION();";
                ResultSet resultSet = statement.executeQuery(query);

                // Process the result set
                if (resultSet.next()) {
                    System.out.println("MySQL Version: " + resultSet.getString(1));
                }

                // Close the resources
                resultSet.close();
                statement.close();
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Database connection failed! SQL Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Database connection failed! Error: " + e.getMessage());
        }
    }
}
