package com.pandemic.c;

import com.pandemic.add.*;
import com.pandemic.b.User;
import com.pandemic.list.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main Frame of the Application
 * Implements role-based filtering for different roles with dynamic dropdown lists.
 */
public class MainFram extends JFrame {

    private JComboBox<String> menuDropdown; // Dropdown for main menus
    private JComboBox<String> submenuDropdown; // Dropdown for submenus
    private JPanel panel;

    // Full menu structure
    private String[][] fullMenuStructure = {
            {"Patient Management", "Add Patient", "Manage Patients"},
            {"Doctor Management", "Add Doctor", "Manage Doctors"},
            {"Department Management", "Add Department", "Manage Departments"},
            {"Medicine Management", "Add Medicine", "Manage Medicines"},
            {"Hospital Management", "Add Hospital", "Manage Hospitals"},
            {"Pandemic Management", "Add Pandemic", "Manage Pandemics", "Pandemic Report"},
            {"Vaccine Management", "Add Vaccine", "Manage Vaccines"},
            {"Transport Management", "Add Transport", "Manage Transports"},
            {"Patient Information", "Type information"},
            {"Vaccine Order Management","Add Vaccine order","Manage Vaccines order"}// Specific to Patient role
    };

    private String[][] filteredMenuStructure; // Filtered menu structure for the role

    public MainFram(User user, String name) {
        // Set window title and size
        setTitle("Welcome: " + name);
        setSize(800, 400);
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Filter menu structure based on the role
        filteredMenuStructure = filterMenusForRole(user.getRole(), user.getOrginization(), user.getEnterprise());

        // Initialize the panel and layout
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Main menu dropdown
        JLabel menuLabel = new JLabel("Select Main Menu:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(menuLabel, gbc);

        menuDropdown = new JComboBox<>();
        for (String[] menu : filteredMenuStructure) {
            menuDropdown.addItem(menu[0]);
        }
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(menuDropdown, gbc);

        // Submenu dropdown
        JLabel submenuLabel = new JLabel("Select Submenu:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(submenuLabel, gbc);

        submenuDropdown = new JComboBox<>();
        updateSubmenu(0); // Initialize with the first menu's submenus
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(submenuDropdown, gbc);

        // Action button
        JButton actionButton = new JButton("Go");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(actionButton, gbc);

        // Event listener for main menu dropdown
        menuDropdown.addActionListener(e -> {
            int selectedIndex = menuDropdown.getSelectedIndex();
            updateSubmenu(selectedIndex);
        });

        // Event listener for action button
        actionButton.addActionListener(e -> {
            int menuIndex = menuDropdown.getSelectedIndex();
            int submenuIndex = submenuDropdown.getSelectedIndex();
            performAction(menuIndex, submenuIndex);
        });

        add(panel);
        setVisible(true);
    }

    /**
     * Updates the submenu dropdown based on the selected main menu.
     *
     * @param menuIndex Index of the selected main menu
     */
    private void updateSubmenu(int menuIndex) {
        submenuDropdown.removeAllItems();
        for (int i = 1; i < filteredMenuStructure[menuIndex].length; i++) {
            submenuDropdown.addItem(filteredMenuStructure[menuIndex][i]);
        }
    }

    /**
     * Performs the action corresponding to the selected submenu.
     *
     * @param menuIndex   Index of the main menu
     * @param submenuIndex Index of the submenu
     */
    private void performAction(int menuIndex, int submenuIndex) {
        String mainMenu = filteredMenuStructure[menuIndex][0];
        String submenu = filteredMenuStructure[menuIndex][submenuIndex + 1];

        try {
            switch (mainMenu) {
                case "Patient Management":
                    if (submenu.equals("Add Patient")) {
                        new PatientAdd();
                    } else if (submenu.equals("Manage Patients")) {
                        new PatientList();
                    }
                    break;

                case "Patient Information":
                    if (submenu.equals("Type information")) {
                        new PatientDetails().setVisible(true);
                    }
                    break;

                case "Doctor Management":
                    if (submenu.equals("Add Doctor")) {
                        new DoctorAdd();
                    } else if (submenu.equals("Manage Doctors")) {
                        new DoctorList();
                    }
                    break;

                case "Department Management":
                    if (submenu.equals("Add Department")) {
                        new AdminAdd();
                    } else if (submenu.equals("Manage Departments")) {
                        new AdminList();
                    }
                    break;

                case "Medicine Management":
                    if (submenu.equals("Add Medicine")) {
                        new DssAdd();
                    } else if (submenu.equals("Manage Medicines")) {
                        new DssList();
                    }
                    break;

                case "Hospital Management":
                    if (submenu.equals("Add Hospital")) {
                        new MrescriptionAdd();
                    } else if (submenu.equals("Manage Hospitals")) {
                        new MrescriptionList();
                    }
                    break;

                case "Pandemic Management":
                    if (submenu.equals("Add Pandemic")) {
                        new PreventionAdd();
                    } else if (submenu.equals("Manage Pandemics")) {
                        new PreventionList();
                    } else if (submenu.equals("Pandemic Report")) {
                        SwingUtilities.invokeLater(() -> {
                            try {
                                PreventionPieChart chartExample = new PreventionPieChart();
                                chartExample.setSize(500, 400);
                                chartExample.setLocationRelativeTo(null);
                                chartExample.setVisible(true);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Error opening Pandemic Report: " + ex.getMessage(),
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        });
                    }
                    break;

                case "Vaccine Management":
                    if (submenu.equals("Add Vaccine")) {
                        new SeedlingAdd();
                    } else if (submenu.equals("Manage Vaccines")) {
                        new SeedlingList();
                    }
                    break;
                   
                case "Vaccine Order Management":
                    if (submenu.equals("Add Vaccine order")) {
                        new UserorderAdd();
                    } else if (submenu.equals("Manage Vaccines order")) {
                        new UserorderList();
                    }
                    break;

                case "Transport Management":
                    if (submenu.equals("Add Transport")) {
                        new TransportAdd();
                    } else if (submenu.equals("Manage Transports")) {
                        new TransportList();
                    }
                    break;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Filters the menu structure based on the user's role.
     *
     * @param role User's role
     * @return Filtered menu structure
     */
//    private String[][] filterMenusForRole(String role) {
//        List<String[]> filteredMenus = new ArrayList<>();
//
//        switch (role) {
//                case "Senior Management":
//                case "User":
//                    return fullMenuStructure; // Full access
//
//                case "Patient":
//                    return new String[][]{
//                            {"Patient Information", "Type information"}
//                    };
//
//                case "Doctor":
//                    filteredMenus.add(fullMenuStructure[0]); // Patient Management
//                    filteredMenus.add(fullMenuStructure[3]); // Medicine Management
//                    filteredMenus.add(fullMenuStructure[5]); // Pandemic Management
//                    filteredMenus.add(fullMenuStructure[6]); // Vaccine Management
//                    break;
//
//                case "Nurse":
//                    filteredMenus.add(fullMenuStructure[0]); // Patient Management
//                    filteredMenus.add(fullMenuStructure[3]); // Medicine Management
//                    break;
//
//                case "Pharmacist":
//                    filteredMenus.add(fullMenuStructure[0]); // Patient Management
//                    filteredMenus.add(fullMenuStructure[3]); // Medicine Management
//                    break;
//
//                case "EMS Staff":
//                    filteredMenus.add(fullMenuStructure[2]); // Department Management
//                    filteredMenus.add(fullMenuStructure[4]); // Hospital Management
//                    filteredMenus.add(fullMenuStructure[5]); // Pandemic Management
//                    break;
//
//                case "Transportationompany":
//                    filteredMenus.add(fullMenuStructure[4]); // Hospital Management
//                    filteredMenus.add(fullMenuStructure[6]); // Vaccine Management
//                    filteredMenus.add(fullMenuStructure[7]); // Transport Management
//                    break;
//
//                case "Volunteer":
//                    filteredMenus.add(fullMenuStructure[0]); // Patient Management
//                    filteredMenus.add(fullMenuStructure[3]); // Medicine Management
//                    break;
//
//                case "FDA":
//                    filteredMenus.add(fullMenuStructure[3]); // Medicine Management
//                    filteredMenus.add(fullMenuStructure[4]); // Hospital Management
//                    filteredMenus.add(fullMenuStructure[5]); // Pandemic Management
//                    filteredMenus.add(fullMenuStructure[6]);
//                    filteredMenus.add(fullMenuStructure[8]);
//                    filteredMenus.add(fullMenuStructure[9]);// Vaccine Management
//                    break;
//
//                case "WHO":
//                    filteredMenus.add(fullMenuStructure[5]); // Pandemic Management
//                    break;
//
//                case "HHS":
//                    filteredMenus.add(fullMenuStructure[5]); // Pandemic Management
//                    filteredMenus.add(fullMenuStructure[6]); // Vaccine Management
//                    filteredMenus.add(fullMenuStructure[3]); // Medicine Managemen
//                    break;
//
//                case "CDC":
//                    filteredMenus.add(fullMenuStructure[5]); // Pandemic Management
//                    filteredMenus.add(fullMenuStructure[6]); // Vaccine Management
//                    filteredMenus.add(fullMenuStructure[3]); // Medicine Managemen
//                    break;
//
//                case "Pfizer":
//                    filteredMenus.add(fullMenuStructure[3]); // Medicine Management
//                    filteredMenus.add(fullMenuStructure[6]); // Vaccine Management
//                    filteredMenus.add(fullMenuStructure[7]); // Transport Management
//                    break;
//
//                case "Merck":
//                    filteredMenus.add(fullMenuStructure[3]); // Medicine Management
//                    filteredMenus.add(fullMenuStructure[7]); // Transport Management
//                    break;
//            
//        }
//
//        return filteredMenus.toArray(new String[0][]);
//    }
    private String[][] filterMenusForRole(String role, String orginization, String enterprise) {
        List<String[]> filteredMenus = new ArrayList<>();

        if ("Admin".equals(role)) {
            return fullMenuStructure; // Full access for Admin
        }

        if ("Network Admin".equals(role)) {
            filteredMenus.add(fullMenuStructure[5]); // Pandemic Management
            filteredMenus.add(fullMenuStructure[6]); // Vaccine Management
        } else if ("Enterprise Admin".equals(role)) {
            if ("FDA".equals(enterprise)) {
                filteredMenus.add(fullMenuStructure[3]); // Medicine Management
                filteredMenus.add(fullMenuStructure[5]); // Pandemic Management
            } else if ("WHO".equals(enterprise)) {
                filteredMenus.add(fullMenuStructure[5]); // Pandemic Management
            } else if ("Fedex".equals(enterprise)) {
                filteredMenus.add(fullMenuStructure[7]); // Transport Management
            }
        } else if ("Organization Admin".equals(role)) {
            if ("Logistics".equals(orginization)) {
                filteredMenus.add(fullMenuStructure[4]); // Hospital Management
                filteredMenus.add(fullMenuStructure[7]); // Transport Management
            } else if ("Research & Development".equals(orginization)) {
                filteredMenus.add(fullMenuStructure[3]); // Medicine Management
                filteredMenus.add(fullMenuStructure[8]); // Auditing
            }
        } else if ("Manager".equals(role)) {
            if ("Healthcare Operations".equals(orginization)) {
                filteredMenus.add(fullMenuStructure[4]); // Hospital Management
            }
        } else if ("Employee".equals(role)) {
            if ("Pharmacy".equals(orginization)) {
                filteredMenus.add(fullMenuStructure[3]); // Medicine Management
            } else if ("Transportation".equals(orginization)) {
                filteredMenus.add(fullMenuStructure[7]); // Transport Management
            }
        } else if ("Auditor".equals(role)) {
            if ("Compliance".equals(orginization)) {
                filteredMenus.add(fullMenuStructure[8]); // Auditing
            }
        } else if ("Guest".equals(role)) {
            filteredMenus.add(new String[]{"Welcome", "Limited Access"}); // Minimal access for guests
        } else if ("FDA".equals(enterprise)) {
            filteredMenus.add(fullMenuStructure[3]); // Medicine Management
            filteredMenus.add(fullMenuStructure[4]); // Hospital Management
            filteredMenus.add(fullMenuStructure[5]); // Pandemic Management
            filteredMenus.add(fullMenuStructure[6]); // Vaccine Management
            filteredMenus.add(fullMenuStructure[8]); // Auditing
        } else if ("WHO".equals(enterprise)) {
            filteredMenus.add(fullMenuStructure[5]); // Pandemic Management
            filteredMenus.add(fullMenuStructure[6]); // Vaccine Management
        } else if ("Merck".equals(enterprise)) {
            filteredMenus.add(fullMenuStructure[3]); // Medicine Management
            filteredMenus.add(fullMenuStructure[7]); // Transport Management
        } else if ("Pfizer".equals(enterprise)) {
            filteredMenus.add(fullMenuStructure[3]); // Medicine Management
            filteredMenus.add(fullMenuStructure[6]); // Vaccine Management
            filteredMenus.add(fullMenuStructure[7]); // Transport Management
        }

        return filteredMenus.isEmpty() ? new String[][] {{"No Access", "Contact Admin"}} : filteredMenus.toArray(new String[0][]);
    }


    public static void main(String[] args) {
//        new MainFram("Patient", "user2");
    }
}
