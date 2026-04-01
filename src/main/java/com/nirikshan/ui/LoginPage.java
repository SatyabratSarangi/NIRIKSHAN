package com.nirikshan.ui;

import com.nirikshan.dao.UserDAO;
import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import java.awt.*;

public class LoginPage extends JPanel {
    private MainFrame frame;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginPage(MainFrame frame) {
        this.frame = frame;
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 245, 245)); // Light modern gray

        initUI();
    }

    private void initUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 1. Title
        JLabel lblTitle = new JLabel("NIRIKSHAN", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblTitle.setForeground(new Color(41, 128, 185)); // Brand Blue

        // 2. Subtitle [cite: 1]
        JLabel lblSub = new JLabel("Public Work Transparency System", SwingConstants.CENTER);
        lblSub.setFont(new Font("SansSerif", Font.PLAIN, 14));

        // 3. Input Fields
        txtUsername = new JTextField(20);
        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter Username");

        txtPassword = new JPasswordField(20);
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter Password");

        // 4. Login Button
        btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(41, 128, 185));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Logic for Authentication
        btnLogin.addActionListener(e -> handleLogin());

        // Layout Components
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(lblTitle, gbc);

        gbc.gridy = 1;
        add(lblSub, gbc);

        gbc.gridy = 2; gbc.gridwidth = 1;
        add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        add(txtUsername, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        add(txtPassword, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        add(btnLogin, gbc);
    }

    private void handleLogin() {
        String user = txtUsername.getText();
        String pass = new String(txtPassword.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        UserDAO dao = new UserDAO();
        String role = dao.loginUser(user, pass);

        if (role != null) {
            // Store session info if needed, then route to Dashboard
            frame.showPage("DASHBOARD");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}