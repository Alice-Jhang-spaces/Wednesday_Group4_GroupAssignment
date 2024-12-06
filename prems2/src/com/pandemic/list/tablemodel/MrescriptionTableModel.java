package com.pandemic.list.tablemodel;

import com.pandemic.a.MrescriptionDao;
import com.pandemic.b.Mrescription;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * MrescriptionTableModel is a table model for displaying hospital data in a JTable.
 * It retrieves data from the database using MrescriptionDao and formats it for display.
 */
public class MrescriptionTableModel extends DefaultTableModel {

    // DAO for accessing hospital data
    private final MrescriptionDao hospitalDao = new MrescriptionDao();

    // Table headers
    private final String[] tableHeaders = new String[]{
            "Hospital ID", 
            "Name", 
            "Address", 
            "Established Date", 
            "Description"
    };

    // Data array for storing hospital details
    private String[][] hospitalData;

    // Constructor to initialize the table model with hospital data
    public MrescriptionTableModel() throws Exception {
        List<Mrescription> hospitalList = hospitalDao.getAllMrescription();

        // Initialize data array
        hospitalData = new String[hospitalList.size()][tableHeaders.length];

        // Populate data array with hospital details
        for (int i = 0; i < hospitalList.size(); i++) {
            Mrescription hospital = hospitalList.get(i);

            hospitalData[i][0] = hospital.getM_id();
            hospitalData[i][1] = hospital.getR_id();
            hospitalData[i][2] = hospital.getM_number();
            hospitalData[i][3] = hospital.getM_data();
            hospitalData[i][4] = hospital.getM_result();
        }

        // Set data and headers for the table model
        this.setDataVector(hospitalData, tableHeaders);
    }
}
