package com.nirikshan.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection {
    // Removed the static connection variable to prevent "Closed Connection" errors

    public static Connection getConnection() {
        try {
            Properties props = new Properties();
            // Using a try-with-resources for the InputStream to ensure it closes properly
            try (InputStream is = DBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
                if (is == null) {
                    System.err.println("Error: db.properties not found in resources!");
                    return null;
                }
                props.load(is);
            }

            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(
                    props.getProperty("db.url"),
                    props.getProperty("db.user"),
                    props.getProperty("db.password")
            );

            System.out.println("Database Connected Successfully!");
            return conn;
        } catch (Exception e) {
            System.err.println("Database Connection Failed!");
            e.printStackTrace();
            return null;
        }
    }
}