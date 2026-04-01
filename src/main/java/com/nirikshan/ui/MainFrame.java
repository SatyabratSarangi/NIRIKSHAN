package com.nirikshan.ui;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // 1. Declare MilestonePage as a class variable so other methods can access it
    private MilestonePage milestonePage;

    public MainFrame() {
        // Setup Modern Look and Feel (FlatLaf)
        FlatLightLaf.setup();

        // Window Properties [cite: 1]
        setTitle("NIRIKSHAN: Public Work Transparency & Payment Control");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize Layout Router
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // 2. Initialize and Register Pages
        milestonePage = new MilestonePage(this);

        mainPanel.add(new LoginPage(this), "LOGIN");
        mainPanel.add(new DashboardPage(this), "DASHBOARD");
        mainPanel.add(milestonePage, "MILESTONES");

        add(mainPanel);

        // Set Initial Page
        cardLayout.show(mainPanel, "LOGIN");
    }

    /**
     * Standard navigation for pages without data transfer.
     */
    public void showPage(String pageName) {
        cardLayout.show(mainPanel, pageName);
    }

    /**
     * 3. THE MISSING METHOD: Handles transition from Dashboard to Milestones.
     * This takes the Project ID from the table and gives it to the MilestonePage.
     */
    public void showProjectDetails(int projectId) {
        milestonePage.setProjectId(projectId); // Update milestone view with specific project ID
        cardLayout.show(mainPanel, "MILESTONES"); // Switch the view
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}