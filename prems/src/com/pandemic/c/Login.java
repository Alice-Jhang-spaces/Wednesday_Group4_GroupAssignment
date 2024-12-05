package com.pandemic.c;

import com.pandemic.a.UserDao;
import com.pandemic.b.LocalUserMap;
import com.pandemic.b.User;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

    private JPanel contentPane;
    private JTextField userNameTextField;
    private JPasswordField passwordTextField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public Login() {
        setTitle("Login Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 713, 496);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Pandemic Reporting and Emergency Management System");
        label.setIcon(new ImageIcon(Login.class.getResource("/images/logo.png")));
        label.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));

        JLabel label_1 = new JLabel("Username:");
        label_1.setIcon(new ImageIcon(Login.class.getResource("/images/password.png")));
        label_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));

        userNameTextField = new JTextField();
        userNameTextField.setFont(new Font("SimSun", Font.PLAIN, 20));
        userNameTextField.setColumns(10);

        JLabel label_2 = new JLabel("Password:");
        label_2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
        label_2.setIcon(new ImageIcon(Login.class.getResource("/images/username.png")));

        passwordTextField = new JPasswordField();
        passwordTextField.setFont(new Font("SimSun", Font.PLAIN, 20));
        passwordTextField.setColumns(10);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this::loginAct);
        loginButton.setIcon(new ImageIcon(Login.class.getResource("/images/confirm.png")));
        loginButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18));

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(this::resetValues);
        resetButton.setIcon(new ImageIcon(Login.class.getResource("/images/reset.png")));
        resetButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18));

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(40)
                    .addComponent(label)
                    .addContainerGap(40, Short.MAX_VALUE))
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(30)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addComponent(loginButton)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addComponent(label_1)
                                .addComponent(label_2))
                            .addGap(6)))
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addComponent(userNameTextField, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                                .addComponent(passwordTextField, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))
                            .addGap(60)))
                        .addGroup(gl_contentPane.createSequentialGroup()
//                            .addGap(60)
//                            .addComponent(resetButton)
//                            .addContainerGap())))
                              .addGap(200) // Center horizontally
                              .addComponent(loginButton)
                              .addPreferredGap(ComponentPlacement.UNRELATED, 20, Short.MAX_VALUE) // Add spacing between buttons
                              .addComponent(resetButton)
                              .addContainerGap(200, Short.MAX_VALUE)))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(26)
                    .addComponent(label)
                    .addGap(33)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(label_1)
                        .addComponent(userNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(label_2)
                        .addComponent(passwordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(45)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(loginButton)
                        .addComponent(resetButton))
                    .addGap(44))
        );
        contentPane.setLayout(gl_contentPane);
    }

    /**
     * Handle the login action.
     */
    protected void loginAct(ActionEvent ae) {
        String userName = userNameTextField.getText().trim();
        String password = new String(passwordTextField.getPassword());

        if (userName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username cannot be empty!");
            return;
        }
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password cannot be empty!");
            return;
        }

        UserDao userDao = new UserDao();

        try {
            boolean isLogin = userDao.login(userName, password);
            if (!isLogin) {
                JOptionPane.showMessageDialog(this, "Incorrect username or password!");
                return;
            }
            User user = userDao.getUserByName(userName);
            LocalUserMap.local_Login_user.put(userName, user);
            LocalUserMap.username = userName;
            LocalUserMap.role = user.getRole();
            JOptionPane.showMessageDialog(null, "Login successful!");
            dispose();
            MainFram mainFrame = new MainFram(user, userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle the reset action.
     */
    protected void resetValues(ActionEvent ae) {
        userNameTextField.setText("");
        passwordTextField.setText("");
    }
}
