package com.nirikshan.dao;

import com.nirikshan.db.DBConnection;
import java.sql.*;

public class UserDAO {
    public String loginUser(String username, String password) {
        String query = "SELECT role FROM users WHERE username = ? AND password = ?";

        // try-with-resources will now correctly close the fresh connection provided by the fixed DBConnection
        try (Connection conn = DBConnection.getConnection()) {

            if (conn == null) {
                System.out.println("Login Error: Could not establish database connection.");
                return null;
            }

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, username);
                pstmt.setString(2, password);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String role = rs.getString("role");
                        System.out.println("Login Success: User '" + username + "' logged in as " + role);
                        return role;
                    } else {
                        System.out.println("Login Failed: Invalid username or password for '" + username + "'.");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error during login process:");
            e.printStackTrace();
        }
        return null; // Login failed
    }
}