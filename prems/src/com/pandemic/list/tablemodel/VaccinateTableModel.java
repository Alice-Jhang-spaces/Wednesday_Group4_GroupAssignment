package com.pandemic.list.tablemodel;

import com.pandemic.a.VaccinateDao;
import com.pandemic.b.Vaccinate;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * VaccinateTableModel manages the data displayed in the VaccinateList table.
 */
public class VaccinateTableModel extends DefaultTableModel {

    private final VaccinateDao vaccinateDao = new VaccinateDao();
    private final String[] tableHeaders = {"Vaccination ID", "Vaccinated Person", "Vaccine", "Vaccination Date", "Description"};
    private String[][] vaccinateData;

    public VaccinateTableModel() throws Exception {
        List vaccinateList = vaccinateDao.getAllVaccinate();
        vaccinateData = new String[vaccinateList.size()][tableHeaders.length];

        for (int i = 0; i < vaccinateList.size(); i++) {
            Vaccinate vaccinate = (Vaccinate) vaccinateList.get(i);
            vaccinateData[i][0] = vaccinate.getM_id();
            vaccinateData[i][1] = vaccinate.getR_id();
            vaccinateData[i][2] = vaccinate.getM_number();
            vaccinateData[i][3] = vaccinate.getM_data();
            vaccinateData[i][4] = vaccinate.getM_result();
        }

        this.setDataVector(vaccinateData, tableHeaders);
    }
}
