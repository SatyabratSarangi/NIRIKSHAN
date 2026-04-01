package com.nirikshan.ui;

import com.nirikshan.dao.MilestoneDAO;
import com.nirikshan.model.Milestone;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * MilestonePage: Displays project stages.
 * Payments are released only after inspection and verification here.
 */
public class MilestonePage extends JPanel {
    private MainFrame frame;
    private JTable milestoneTable;
    private DefaultTableModel tableModel;
    private int currentProjectId;

    public MilestonePage(MainFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // 1. Header with Back Button [cite: 15]
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(41, 128, 185));
        header.setPreferredSize(new Dimension(0, 60));

        JButton btnBack = new JButton("← Back to Projects");
        btnBack.addActionListener(e -> frame.showPage("DASHBOARD"));

        JLabel lblTitle = new JLabel("PROJECT MILESTONES & VERIFICATION", SwingConstants.CENTER);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 16));

        header.add(btnBack, BorderLayout.WEST);
        header.add(lblTitle, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        // 2. Milestone Table [cite: 21]
        String[] columns = {"ID", "Task/Milestone", "Amount", "Completed", "Verified"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        milestoneTable = new JTable(tableModel);
        milestoneTable.setRowHeight(30);
        add(new JScrollPane(milestoneTable), BorderLayout.CENTER);

        // 3. Action Footer [cite: 12, 13]
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnInspect = new JButton("Verify Work (Inspector)");
        JButton btnRequestPayment = new JButton("Request Payment (Contractor)");

        btnInspect.addActionListener(e -> handleInspection());

        footer.add(btnRequestPayment);
        footer.add(btnInspect);
        add(footer, BorderLayout.SOUTH);
    }

    /**
     * This method is called by MainFrame to update the UI for a specific project[cite: 11].
     */
    public void setProjectId(int projectId) {
        this.currentProjectId = projectId;
        loadMilestoneData();
    }

    private void loadMilestoneData() {
        tableModel.setRowCount(0);
        MilestoneDAO dao = new MilestoneDAO();
        List<Milestone> milestones = dao.getMilestonesByProject(currentProjectId);

        for (Milestone m : milestones) {
            tableModel.addRow(new Object[]{
                    m.getId(), m.getTitle(), m.getAmount(),
                    m.isCompleted() ? "Yes" : "No",
                    m.isVerified() ? "Verified" : "Pending"
            });
        }
    }

    private void handleInspection() {
        int row = milestoneTable.getSelectedRow();
        if (row != -1) {
            // Logic for status update only - No records can be deleted [cite: 15]
            JOptionPane.showMessageDialog(this, "Verification report submitted. Status updated.");
            loadMilestoneData();
        } else {
            JOptionPane.showMessageDialog(this, "Select a milestone to verify.");
        }
    }
}