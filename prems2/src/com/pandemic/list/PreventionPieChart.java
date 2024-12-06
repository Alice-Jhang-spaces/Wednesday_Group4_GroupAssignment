package com.pandemic.list;

import com.pandemic.a.PreventionDao;
import com.pandemic.b.Prevention;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * PreventionPieChart is a JFrame that displays a pie chart for pandemic statistics
 * using data from the PreventionDao class.
 */
public class PreventionPieChart extends JFrame {

    public PreventionPieChart() {
        try {
            // Create a chart theme
            StandardChartTheme chartTheme = new StandardChartTheme("EN");
            
            // Set title font
            chartTheme.setExtraLargeFont(new Font("Serif", Font.BOLD, 20));
            
            // Set legend font
            chartTheme.setRegularFont(new Font("SansSerif", Font.PLAIN, 15));
            
            // Set axis font
            chartTheme.setLargeFont(new Font("SansSerif", Font.PLAIN, 15));
            
            // Apply the chart theme
            ChartFactory.setChartTheme(chartTheme);

            // Create a dataset and populate it with data from the database
            DefaultPieDataset dataset = new DefaultPieDataset();
            PreventionDao preventionDao = new PreventionDao();
            List preventionList = preventionDao.getAllPrevention();

            for (Object obj : preventionList) {
                Prevention prevention = (Prevention) obj;
                dataset.setValue(prevention.getR_id(), Double.valueOf(prevention.getM_number()));
            }

            // Create a pie chart using the dataset
            JFreeChart chart = ChartFactory.createPieChart(
                    "Pandemic Statistics", // Chart title
                    dataset, // Dataset
                    true, // Show legend
                    true, // Use tooltips
                    false // Generate URLs
            );

            // Add the chart to a panel
            ChartPanel chartPanel = new ChartPanel(chart);
            setContentPane(chartPanel);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Create and display the window
        PreventionPieChart chartExample = new PreventionPieChart();
        chartExample.setSize(500, 400); // Set window size
        chartExample.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set close operation
        chartExample.setVisible(true); // Display the window
    }
}
