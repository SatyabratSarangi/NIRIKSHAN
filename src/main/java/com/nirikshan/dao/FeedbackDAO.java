package com.nirikshan.dao;

import com.nirikshan.db.DBConnection;
import java.sql.*;

public class FeedbackDAO {
    public void addFeedback(int projectId, int userId, int rating, String comment) {
        String sql = "INSERT INTO public_feedback (project_id, user_id, rating, comment) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, projectId);
            pstmt.setInt(2, userId);
            pstmt.setInt(3, rating);
            pstmt.setString(4, comment);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}