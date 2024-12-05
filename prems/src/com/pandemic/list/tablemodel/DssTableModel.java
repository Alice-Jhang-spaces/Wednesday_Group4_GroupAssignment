package com.pandemic.list.tablemodel;

import com.pandemic.a.DssDao;
import com.pandemic.b.Dss;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * DssTableModel is a table model used for displaying drug information in a JTable.
 * It retrieves data from the database using DssDao and formats it for display.
 */
public class DssTableModel extends DefaultTableModel {

    // DAO for accessing drug data
    private final DssDao dssDao = new DssDao();

    // Table headers
    private final String[] tableHeaders = new String[]{
            "Drug ID", 
            "Drug Name", 
            "Drug Company", 
            "Quantity", 
            "Shelf ID", 
            "Specification", 
            "Unit", 
            "Price"
    };

    // Data array for storing drug details
    private String[][] dssData;

    // Constructor to initialize the table model with drug data
    public DssTableModel() throws Exception {
        List<Dss> dssList = dssDao.getAllDSS();

        // Initialize data array
        dssData = new String[dssList.size()][tableHeaders.length];

        // Populate data array with drug details
        for (int i = 0; i < dssList.size(); i++) {
            Dss drug = dssList.get(i);

            dssData[i][0] = drug.getD_id();
            dssData[i][1] = drug.getD_name();
            dssData[i][2] = drug.getD_type();
            dssData[i][3] = String.valueOf(drug.getD_number());
            dssData[i][4] = drug.getD_id_id();
            dssData[i][5] = drug.getD_unit();
            dssData[i][6] = drug.getD_spec();
            dssData[i][7] = drug.getD_price();
        }

        // Set data and headers for the table model
        this.setDataVector(dssData, tableHeaders);
    }
}
