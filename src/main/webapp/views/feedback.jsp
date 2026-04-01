<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Submit Feedback - NIRIKSHAN</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="container">
        <%
            String projectId = request.getParameter("id");
        %>
        <h2>Submit Feedback for Project #<%= projectId %></h2>
        <form action="../SubmitFeedbackServlet" method="post" class="modern-form">
            <input type="hidden" name="projectId" value="<%= projectId %>">

            <label>Rating (1 to 5):</label>
            <select name="rating" required>
                <option value="5">5 - Excellent</option>
                <option value="4">4 - Good</option>
                <option value="3">3 - Average</option>
                <option value="2">2 - Poor</option>
                <option value="1">1 - Corruption Suspected</option>
            </select>

            <label>Comments:</label>
            <textarea name="comment" rows="5" placeholder="Describe the current work status..." required></textarea>

            <button type="submit" class="btn">Submit for Transparency</button>
            <a href="../index.jsp" class="back-link">← Back to Project List</a>
        </form>
    </div>
</body>
</html>