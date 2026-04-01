package com.nirikshan.dao;

import com.nirikshan.db.DBConnection;
import com.nirikshan.model.Project;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ProjectDAO {
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT * FROM projects";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Project p = new Project();
                p.setId(rs.getInt("project_id"));
                p.setTitle(rs.getString("title"));
                p.setDescription(rs.getString("description"));
                p.setBudget(rs.getDouble("budget"));
                p.setStatus(rs.getString("status"));
                projects.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }
}