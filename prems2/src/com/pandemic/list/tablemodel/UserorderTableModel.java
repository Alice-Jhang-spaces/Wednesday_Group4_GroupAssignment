package com.pandemic.list.tablemodel;

import com.pandemic.a.UserorderDao;
import com.pandemic.b.Userorder;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * UserorderTableModel is responsible for managing data displayed in the UserorderList table.
 */
public class UserorderTableModel extends DefaultTableModel {

    private final UserorderDao userorderDao = new UserorderDao();
    private final String[] tableHeaders = {"Order ID", "Product Name", "Quantity", "Order Date", "Order Description"};
    private String[][] userorderData;

    public UserorderTableModel() throws Exception {
        List userorderList = userorderDao.getAllUserorder();
        userorderData = new String[userorderList.size()][tableHeaders.length];

        for (int i = 0; i < userorderList.size(); i++) {
            Userorder userorder = (Userorder) userorderList.get(i);
            userorderData[i][0] = userorder.getM_id();
            userorderData[i][1] = userorder.getR_id();
            userorderData[i][2] = userorder.getM_number();
            userorderData[i][3] = userorder.getM_data();
            userorderData[i][4] = userorder.getM_result();
        }

        this.setDataVector(userorderData, tableHeaders);
    }
}
