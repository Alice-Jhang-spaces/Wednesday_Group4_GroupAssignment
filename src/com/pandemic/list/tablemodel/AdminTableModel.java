package com.pandemic.list.tablemodel;

import com.pandemic.a.AdminDao;
import com.pandemic.b.Admin;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * AdminTableModel is a table model for displaying department data in a JTable.
 * It retrieves data from the database using AdminDao and structures it for display.
 */


public class AdminTableModel extends DefaultTableModel {

    // DAO for accessing department data
    private final AdminDao adminDao = new AdminDao();

    // Table headers
    private final String[] tableHeaders = new String[]{"Department ID", "Department Name"};

    // List to store department data
    private List<Admin> adminList = null;

    // 2D array to store department data for the table
    private String[][] adminData = null;

    // Constructor to initialize the table model with department data
    public AdminTableModel() throws Exception {
        // Retrieve all department data from the database
        adminList = adminDao.getAllAdmin();

        // Initialize data array with appropriate size
        adminData = new String[adminList.size()][tableHeaders.length];

        // Populate the data array with department information
        for (int i = 0; i < adminList.size(); i++) {
            Admin admin = adminList.get(i);
            adminData[i][0] = admin.getA_id();     // Set department ID
            adminData[i][1] = admin.getA_name();   // Set department name
        }

        // Set the data and headers for the table model
        this.setDataVector(adminData, tableHeaders);
    }
}








