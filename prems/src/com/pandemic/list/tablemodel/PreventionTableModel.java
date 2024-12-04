package com.pandemic.list.tablemodel;

import com.pandemic.a.PreventionDao;
import com.pandemic.b.Prevention;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * PreventionTableModel is a table model for displaying pandemic data in a JTable.
 * It retrieves data from the database using PreventionDao and formats it for display.
 */
public class PreventionTableModel extends DefaultTableModel {

    // DAO for accessing pandemic data
    private final PreventionDao preventionDao = new PreventionDao();

    // Table headers
    private final String[] tableHeaders = new String[]{
            "Pandemic ID", 
            "Title", 
            "Statistics", 
            "Entry Date", 
            "Description"
    };

    // Data array for storing pandemic details
    private String[][] preventionData;

    // Constructor to initialize the table model with pandemic data
    public PreventionTableModel() throws Exception {
        List<Prevention> preventionList = preventionDao.getAllPrevention();

        // Initialize data array
        preventionData = new String[preventionList.size()][tableHeaders.length];

        // Populate data array with pandemic details
        for (int i = 0; i < preventionList.size(); i++) {
            Prevention prevention = preventionList.get(i);

            preventionData[i][0] = prevention.getM_id();
            preventionData[i][1] = prevention.getR_id();
            preventionData[i][2] = prevention.getM_number();
            preventionData[i][3] = prevention.getM_data();
            preventionData[i][4] = prevention.getM_result();
        }

        // Set data and headers for the table model
        this.setDataVector(preventionData, tableHeaders);
    }
}
