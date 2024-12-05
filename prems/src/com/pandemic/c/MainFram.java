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
            {"Work Requests", "Add Work Request", "Manage Work Requests"},
            {"Patient Information", "Type information"},
            {"Vaccine Order Management", "Add Vaccine order", "Manage Vaccine orders"}
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
     * Filters the menu structure based on the user's role.
     *
     * @param role        User's role
     * @param organization User's organization
     * @param enterprise   User's enterprise
     * @return Filtered menu structure
     */
    private String[][] filterMenusForRole(String role, String organization, String enterprise) {
        List<String[]> roleBasedMenus = new ArrayList<>();

        switch (role) {
            case "Admin":
                return fullMenuStructure; // Admin has full access.

            case "Network Admin":
                roleBasedMenus.add(fullMenuStructure[5]); // Pandemic Management
                roleBasedMenus.add(fullMenuStructure[6]); // Vaccine Management
                break;

            case "Enterprise Admin":
                switch (enterprise) {
                    case "FDA":
                        roleBasedMenus.add(fullMenuStructure[3]); // Medicine Management
                        roleBasedMenus.add(fullMenuStructure[5]); // Pandemic Management
                        break;
                    case "WHO":
                        roleBasedMenus.add(fullMenuStructure[5]); // Pandemic Management
                        break;
                    case "Fedex":
                        roleBasedMenus.add(fullMenuStructure[7]); // Transport Management
                        break;
                    case "Merck":
                        roleBasedMenus.add(fullMenuStructure[3]); // Medicine Management
                        break;
                    case "Pfizer":
                        roleBasedMenus.add(fullMenuStructure[6]); // Vaccine Management
                        break;
                }
                break;

            case "Organization Admin":
                switch (organization) {
                    case "Logistics":
                        roleBasedMenus.add(fullMenuStructure[4]); // Hospital Management
                        roleBasedMenus.add(fullMenuStructure[7]); // Transport Management
                        break;
                    case "Research & Development":
                        roleBasedMenus.add(fullMenuStructure[3]); // Medicine Management
                        roleBasedMenus.add(fullMenuStructure[8]); // Work Requests
                        break;
                    case "Compliance":
                        roleBasedMenus.add(fullMenuStructure[8]); // Work Requests
                        break;
                }
                break;

            case "Manager":
                if ("Healthcare Operations".equals(organization)) {
                    roleBasedMenus.add(fullMenuStructure[4]); // Hospital Management
                }
                break;

            case "Employee":
                switch (organization) {
                    case "Pharmacy":
                        roleBasedMenus.add(fullMenuStructure[3]); // Medicine Management
                        roleBasedMenus.add(fullMenuStructure[8]); // Work Requests
                        break;
                    case "Transportation":
                        roleBasedMenus.add(fullMenuStructure[7]); // Transport Management
                        roleBasedMenus.add(fullMenuStructure[8]); // Work Requests
                        break;
                }
                break;
            case "Auditor":
                // Auditor
                if ("Compliance".equals(organization)) {
                roleBasedMenus.add(fullMenuStructure[8]); // Work Requests
                }
                break;

            default:
                roleBasedMenus.add(new String[]{"No Access", "Contact Admin"});
        }

        return roleBasedMenus.isEmpty() ? new String[][]{{"No Access", "Contact Admin"}} : roleBasedMenus.toArray(new String[0][]);
    }

    
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
                    } else if (submenu.equals("Manage Vaccine orders")) {
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

                case "Work Requests":
                    if (submenu.equals("Add Work Request")) {
                        new WorkRequestAdd();
                    } else if (submenu.equals("Manage Work Requests")) {
                        new WorkRequestList();
                    }
                    break;
                
                case "Patient Information":
                    if (submenu.equals("Type information")) {
                        new PatientDetails().setVisible(true);
                    }
                    break;



                default:
                    JOptionPane.showMessageDialog(this, "Unknown menu action!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        // Example usage
        User testUser = new User(1, "Admin", "password", "Admin", "Headquarters", "FDA", "GlobalHealthNet");
        new MainFram(testUser, testUser.getName());
    }


}