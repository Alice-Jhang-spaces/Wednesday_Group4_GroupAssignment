package com.pandemic.list.tablemodel;

import com.pandemic.a.PatientDao;
import com.pandemic.b.Patient;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * PatientTableModel is a table model for displaying patient data in a JTable.
 * It retrieves data from the database using PatientDao and formats it for display.
 */
public class PatientTableModel extends DefaultTableModel {

    // DAO for accessing patient data
    private final PatientDao patientDao = new PatientDao();

    // Table headers
    private final String[] tableHeaders = new String[]{
            "Patient ID", 
            "Name", 
            "Gender", 
            "Age"
    };

    // Data array for storing patient details
    private String[][] patientData;

    // Constructor to initialize the table model with patient data
    public PatientTableModel() throws Exception {
        List<Patient> patientList = patientDao.getAllPatient();

        // Initialize data array
        patientData = new String[patientList.size()][tableHeaders.length];

        // Populate data array with patient details
        for (int i = 0; i < patientList.size(); i++) {
            Patient patient = patientList.get(i);

            patientData[i][0] = patient.getP_id();
            patientData[i][1] = patient.getP_name();
            patientData[i][2] = patient.getP_sex();
            patientData[i][3] = String.valueOf(patient.getP_age());
        }

        // Set data and headers for the table model
        this.setDataVector(patientData, tableHeaders);
    }
}
