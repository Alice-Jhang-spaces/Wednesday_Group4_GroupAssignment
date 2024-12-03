package com.pandemic.a;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao {

    private Connection connection; // Declare connection object

    /**
     * Get database connection
     * 
     * @return Connection object
     * @throws Exception If connection fails
     */
    public Connection getConnection() throws Exception {
        // Use the latest MySQL driver class
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/db_prems?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=CONVERT_TO_NULL&rewriteBatchedStatements=true&serverTimezone=UTC";
        String user = "root";
        String password = "Heidy0657009@";

        // Load the driver class
        Class.forName(driver);

        // Establish the connection
        connection = DriverManager.getConnection(url, user, password);

        return connection;
    }

    /**
     * Test the connection
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        try {
            BaseDao baseDao = new BaseDao(); // Create BaseDao object
            Connection connection = baseDao.getConnection(); // Get connection object
            if (connection != null) {
                System.out.println("Database connection successful!"); // Output success message
            }
        } catch (SQLException e) {
            System.err.println("Database connection failed! SQL Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Database connection failed! Error: " + e.getMessage());
        }
    }
}
