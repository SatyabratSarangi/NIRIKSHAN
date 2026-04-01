package com.nirikshan.web;

import com.nirikshan.dao.FeedbackDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SubmitFeedbackServlet")
public class SubmitFeedbackServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Capture data from JSP form
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");

        // For this simple version, we use a dummy User ID (e.g., 1)
        // In a full system, this would come from a web session
        int userId = 1;

        // 2. Save to Database using DAO
        FeedbackDAO dao = new FeedbackDAO();
        dao.addFeedback(projectId, userId, rating, comment);

        // 3. Redirect back to home with success message
        response.sendRedirect("index.jsp?msg=Feedback Submitted Successfully");
    }
}