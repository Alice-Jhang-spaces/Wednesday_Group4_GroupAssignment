package com.pandemic.list.tablemodel;

import com.pandemic.a.RegistrationDao;
import com.pandemic.b.Registration;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * RegistrationTableModel handles the table data for displaying registrations.
 */
public class RegistrationTableModel extends DefaultTableModel {

    private final RegistrationDao registrationDao = new RegistrationDao();
    private final String[] tableHeaders = {"Registration ID", "Patient ID", "Medicine ID", "Distribution Date", "Description"};
    private String[][] registrationData;

    public RegistrationTableModel() throws Exception {
        List registrationList = registrationDao.getAllRegistration();
        registrationData = new String[registrationList.size()][tableHeaders.length];

        for (int i = 0; i < registrationList.size(); i++) {
            Registration registration = (Registration) registrationList.get(i);
            registrationData[i][0] = registration.getR_id();
            registrationData[i][1] = registration.getP_id();
            registrationData[i][2] = registration.getDd_id();
            registrationData[i][3] = registration.getR_data();
            registrationData[i][4] = registration.getR_price_id();
        }

        this.setDataVector(registrationData, tableHeaders);
    }
}
