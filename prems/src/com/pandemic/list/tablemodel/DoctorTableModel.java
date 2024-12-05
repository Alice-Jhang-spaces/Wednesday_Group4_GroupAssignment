package com.pandemic.list.tablemodel;

import com.pandemic.a.DoctorDao;
import com.pandemic.b.Doctor;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * DoctorTableModel is a table model used for displaying doctor information in a JTable.
 * It retrieves data from the database using DoctorDao and formats it for display.
 */
public class DoctorTableModel extends DefaultTableModel {

    // DAO for accessing doctor data
    private final DoctorDao doctorDao = new DoctorDao();

    // Table headers
    private final String[] tableHeaders = new String[]{
            "Doctor ID", 
            "Department ID", 
            "Doctor Name", 
            "Gender", 
            "Age", 
            "Title", 
            "Graduation School", 
            "Experience (Years)"
    };

    // List to store doctor data
    private List<Doctor> doctorList;

    // 2D array to store doctor data for the table
    private String[][] doctorData;

    /**
     * Constructor to initialize the table model with doctor data.
     * @throws Exception if there is an error while fetching data from the database.
     */
    public DoctorTableModel() throws Exception {
        // Retrieve all doctor data from the database
        doctorList = doctorDao.getAllDoctor();

        // Initialize the data array with the appropriate size
        doctorData = new String[doctorList.size()][tableHeaders.length];

        // Populate the data array with doctor information
        for (int i = 0; i < doctorList.size(); i++) {
            Doctor doctor = doctorList.get(i);

            // Populate each column for a doctor
            doctorData[i][0] = doctor.getD_id();                // Doctor ID
            doctorData[i][1] = doctor.getA_id();                // Department ID
            doctorData[i][2] = doctor.getD_name();              // Doctor Name
            doctorData[i][3] = doctor.getD_sex();               // Gender
            doctorData[i][4] = String.valueOf(doctor.getD_age()); // Age
            doctorData[i][5] = doctor.getD_type();              // Title
            doctorData[i][6] = doctor.getGraduationSchool();    // Graduation School
            doctorData[i][7] = doctor.getExperienceYears();     // Experience (Years)
        }

        // Set the data and headers for the table model
        this.setDataVector(doctorData, tableHeaders);
    }
}
