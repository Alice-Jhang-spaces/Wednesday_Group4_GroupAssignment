package com.pandemic.list.tablemodel;

import com.pandemic.a.TransportDao;
import com.pandemic.b.Transport;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * TransportTableModel handles the table data for displaying transportation records.
 */
public class TransportTableModel extends DefaultTableModel {

    private final TransportDao transportDao = new TransportDao();
    private final String[] tableHeaders = {"Transport ID", "Goods", "Status", "Transport Time", "Description"};
    private String[][] transportData;

    public TransportTableModel() throws Exception {
        List transportList = transportDao.getAllTransport();
        transportData = new String[transportList.size()][tableHeaders.length];

        for (int i = 0; i < transportList.size(); i++) {
            Transport transport = (Transport) transportList.get(i);
            transportData[i][0] = transport.getM_id();
            transportData[i][1] = transport.getR_id();
            transportData[i][2] = transport.getM_number();
            transportData[i][3] = transport.getM_data();
            transportData[i][4] = transport.getM_result();
        }

        this.setDataVector(transportData, tableHeaders);
    }
}
