package com.nirikshan.dao;

import com.nirikshan.db.DBConnection;
import com.nirikshan.model.Milestone;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class MilestoneDAO {
    public List<Milestone> getMilestonesByProject(int projectId) {
        List<Milestone> list = new ArrayList<>();
        String query = "SELECT * FROM milestones WHERE project_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, projectId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Milestone m = new Milestone();
                m.setId(rs.getInt("milestone_id"));
                m.setTitle(rs.getString("title"));
                m.setAmount(rs.getDouble("amount_to_release"));
                m.setCompleted(rs.getBoolean("is_completed"));
                m.setVerified(rs.getBoolean("is_verified"));
                list.add(m);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}