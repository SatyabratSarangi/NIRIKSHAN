<%@ page import="com.nirikshan.dao.ProjectDAO, com.nirikshan.model.Project, java.util.List" %>
<html>
<head>
    <title>NIRIKSHAN - Public Portal</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="header">
        <h1>NIRIKSHAN: Public Work Transparency</h1>
    </div>

    <div class="container">
        <h2>Active Government Projects</h2>
        <table border="1">
            <tr>
                <th>Project ID</th>
                <th>Title</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <%
                ProjectDAO dao = new ProjectDAO();
                List<Project> projects = dao.getAllProjects();
                for(Project p : projects) {
            %>
            <tr>
                <td><%= p.getId() %></td>
                <td><%= p.getTitle() %></td>
                <td><%= p.getStatus() %></td>
                <td><a href="views/feedback.jsp?id=<%= p.getId() %>">Give Feedback</a></td>
            </tr>
            <% } %>
        </table>
    </div>
</body>
</html>