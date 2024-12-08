package com.pandemic.list.tablemodel;

import com.pandemic.a.SeedlingDao;
import com.pandemic.b.Seedling;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * SeedlingTableModel handles the table data for displaying vaccine information.
 */
public class SeedlingTableModel extends DefaultTableModel {

    private final SeedlingDao seedlingDao = new SeedlingDao();
    private final String[] tableHeaders = {"Vaccine ID", "Name", "Place of Production:", "Manufacturing Date:", "Description"};
    private String[][] seedlingData;

    public SeedlingTableModel() throws Exception {
        List seedlingList = seedlingDao.getAllSeedling();
        seedlingData = new String[seedlingList.size()][tableHeaders.length];

        for (int i = 0; i < seedlingList.size(); i++) {
            Seedling seedling = (Seedling) seedlingList.get(i);
            seedlingData[i][0] = seedling.getM_id();
            seedlingData[i][1] = seedling.getR_id();
            seedlingData[i][2] = seedling.getM_number();
            seedlingData[i][3] = seedling.getM_data();
            seedlingData[i][4] = seedling.getM_result();
        }

        this.setDataVector(seedlingData, tableHeaders);
    }
}
