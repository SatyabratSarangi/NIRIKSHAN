package com.nirikshan.model;

import java.sql.Timestamp;

public class Feedback {
    private int id;
    private int projectId;
    private String comment;
    private int rating;
    private Timestamp createdAt;

    // Getters and Setters for all fields

    public int getId() {
        return id;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}