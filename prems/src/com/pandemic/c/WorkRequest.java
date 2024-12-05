/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pandemic.c;

import com.pandemic.a.BaseDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author alice
 */
public class WorkRequest extends BaseDao {
    // Attributes matching the database schema
    private int workRequestID;
    private String requestType;
    private String requesterRole;
    private String requesterOrganization;
    private String requesterEnterprise;
    private String receiverRole;
    private String receiverOrganization;
    private String receiverEnterprise;
    private String status;
    private String description;

    // Constructor
    public WorkRequest(int workRequestID, String requestType, String requesterRole, String requesterOrganization,
                       String requesterEnterprise, String receiverRole, String receiverOrganization,
                       String receiverEnterprise, String status, String description) {
        this.workRequestID = workRequestID;
        this.requestType = requestType;
        this.requesterRole = requesterRole;
        this.requesterOrganization = requesterOrganization;
        this.requesterEnterprise = requesterEnterprise;
        this.receiverRole = receiverRole;
        this.receiverOrganization = receiverOrganization;
        this.receiverEnterprise = receiverEnterprise;
        this.status = status;
        this.description = description;
    }

    // Getters and Setters
    public int getWorkRequestID() { return workRequestID; }
    public void setWorkRequestID(int workRequestID) { this.workRequestID = workRequestID; }

    public String getRequestType() { return requestType; }
    public void setRequestType(String requestType) { this.requestType = requestType; }

    public String getRequesterRole() { return requesterRole; }
    public void setRequesterRole(String requesterRole) { this.requesterRole = requesterRole; }

    public String getRequesterOrganization() { return requesterOrganization; }
    public void setRequesterOrganization(String requesterOrganization) { this.requesterOrganization = requesterOrganization; }

    public String getRequesterEnterprise() { return requesterEnterprise; }
    public void setRequesterEnterprise(String requesterEnterprise) { this.requesterEnterprise = requesterEnterprise; }

    public String getReceiverRole() { return receiverRole; }
    public void setReceiverRole(String receiverRole) { this.receiverRole = receiverRole; }

    public String getReceiverOrganization() { return receiverOrganization; }
    public void setReceiverOrganization(String receiverOrganization) { this.receiverOrganization = receiverOrganization; }

    public String getReceiverEnterprise() { return receiverEnterprise; }
    public void setReceiverEnterprise(String receiverEnterprise) { this.receiverEnterprise = receiverEnterprise; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    // Method to save a work request to the database
    public boolean saveToDatabase() throws Exception {
        String sql = "INSERT INTO WorkRequests (RequestType, RequesterRole, RequesterOrganization, " +
                     "RequesterEnterprise, ReceiverRole, ReceiverOrganization, ReceiverEnterprise, Status, Description) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, requestType);
        pstmt.setString(2, requesterRole);
        pstmt.setString(3, requesterOrganization);
        pstmt.setString(4, requesterEnterprise);
        pstmt.setString(5, receiverRole);
        pstmt.setString(6, receiverOrganization);
        pstmt.setString(7, receiverEnterprise);
        pstmt.setString(8, status);
        pstmt.setString(9, description);
        return pstmt.executeUpdate() == 1;
    }

    // Method to fetch all work requests from the database
    public static List<WorkRequest> fetchAllFromDatabase() throws Exception {
        String sql = "SELECT * FROM WorkRequests";
        List<WorkRequest> workRequests = new ArrayList<>();
        Connection connection = new BaseDao().getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            workRequests.add(new WorkRequest(
                rs.getInt("WorkRequestID"),
                rs.getString("RequestType"),
                rs.getString("RequesterRole"),
                rs.getString("RequesterOrganization"),
                rs.getString("RequesterEnterprise"),
                rs.getString("ReceiverRole"),
                rs.getString("ReceiverOrganization"),
                rs.getString("ReceiverEnterprise"),
                rs.getString("Status"),
                rs.getString("Description")
            ));
        }
        return workRequests;
    }

    // Method to update the status of a work request
    public boolean updateStatus(String newStatus) throws Exception {
        String sql = "UPDATE WorkRequests SET Status = ? WHERE WorkRequestID = ?";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, newStatus);
        pstmt.setInt(2, workRequestID);
        int rowsUpdated = pstmt.executeUpdate();
        if (rowsUpdated == 1) {
            this.status = newStatus; // Update the local object
            return true;
        }
        return false;
    }

    // Method to delete a work request by ID
    public static boolean deleteFromDatabase(int workRequestID) throws Exception {
        String sql = "DELETE FROM WorkRequests WHERE WorkRequestID = ?";
        Connection connection = new BaseDao().getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, workRequestID);
        return pstmt.executeUpdate() == 1;
    }
}