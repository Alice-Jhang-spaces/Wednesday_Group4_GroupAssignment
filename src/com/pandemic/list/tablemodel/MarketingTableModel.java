package com.pandemic.list.tablemodel;

import com.pandemic.a.MarketingDao;
import com.pandemic.b.Marketing;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * MarketingTableModel is a table model for displaying marketing data in a JTable.
 * It retrieves data from the database using MarketingDao and formats it for display.
 */
public class MarketingTableModel extends DefaultTableModel {

    // DAO for accessing marketing data
    private final MarketingDao marketingDao = new MarketingDao();

    // Table headers
    private final String[] tableHeaders = new String[]{
            "Marketing ID", 
            "Marketing Person", 
            "Vaccine", 
            "Marketing Date", 
            "Description"
    };

    // Data array for storing marketing details
    private String[][] marketingData;

    // Constructor to initialize the table model with marketing data
    public MarketingTableModel() throws Exception {
        List<Marketing> marketingList = marketingDao.getAllMarketing();

        // Initialize data array
        marketingData = new String[marketingList.size()][tableHeaders.length];

        // Populate data array with marketing details
        for (int i = 0; i < marketingList.size(); i++) {
            Marketing marketing = marketingList.get(i);

            marketingData[i][0] = marketing.getM_id();
            marketingData[i][1] = marketing.getR_id();
            marketingData[i][2] = marketing.getM_number();
            marketingData[i][3] = marketing.getM_data();
            marketingData[i][4] = marketing.getM_result();
        }

        // Set data and headers for the table model
        this.setDataVector(marketingData, tableHeaders);
    }
}
