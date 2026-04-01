package com.nirikshan.ui;

import com.nirikshan.dao.ProjectDAO;
import com.nirikshan.model.Project;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * DashboardPage: Central hub to view all public construction projects.
 * No records can be deleted; only status updates are allowed.
 */
public class DashboardPage extends JPanel {
    private MainFrame frame;
    private JTable projectTable;
    private DefaultTableModel tableModel;

    public DashboardPage(MainFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());

        // 1. Header Section with Brand Color and Logout
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(41, 128, 185));
        header.setPreferredSize(new Dimension(0, 60));

        JLabel lblTitle = new JLabel("  NIRIKSHAN: PROJECT DASHBOARD", SwingConstants.LEFT);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 18));

        JButton btnLogout = new JButton("Logout ");
        btnLogout.setFocusPainted(false);
        // Routed back option to return to Login
        btnLogout.addActionListener(e -> frame.showPage("LOGIN"));

        header.add(lblTitle, BorderLayout.WEST);
        header.add(btnLogout, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        // 2. Center Section: Project Table
        String[] columns = {"Project ID", "Title", "Budget", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        projectTable = new JTable(tableModel);
        projectTable.setRowHeight(30);
        projectTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        projectTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(projectTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        // 3. Footer Section: Action Buttons
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.setBackground(new Color(240, 240, 240));

        JButton btnRefresh = new JButton("Refresh Data");
        JButton btnViewMilestones = new JButton("View Milestones");

        // Button style
        btnViewMilestones.setBackground(new Color(41, 128, 185));
        btnViewMilestones.setForeground(Color.WHITE);

        // Logic to route to the Milestone Page
        btnViewMilestones.addActionListener(e -> {
            int selectedRow = projectTable.getSelectedRow();
            if (selectedRow != -1) {
                // Capture the Project ID from the first column
                int projectId = (int) tableModel.getValueAt(selectedRow, 0);

                // Trigger the routing method in MainFrame
                frame.showProjectDetails(projectId);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a project from the table first.",
                        "Selection Required", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnRefresh.addActionListener(e -> loadProjectData());

        actionPanel.add(btnRefresh);
        actionPanel.add(btnViewMilestones);
        add(actionPanel, BorderLayout.SOUTH);

        // Initial load of data from PostgreSQL
        loadProjectData();
    }

    /**
     * Fetches real-time project data from the database.
     */
    public void loadProjectData() {
        tableModel.setRowCount(0);
        ProjectDAO dao = new ProjectDAO();
        try {
            List<Project> projects = dao.getAllProjects();
            for (Project p : projects) {
                tableModel.addRow(new Object[]{
                        p.getId(),
                        p.getTitle(),
                        p.getBudget(),
                        p.getStatus()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error connecting to Database: " + e.getMessage());
        }
    }
}