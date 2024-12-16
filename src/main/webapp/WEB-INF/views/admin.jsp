<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/admin.css">
</head>
<body>
    <div class="container">
        <!-- Sidebar -->
       <%@ include file="sidebar.jsp" %>


        <!-- Content -->
        <div class="content">
            <h1>Admin View</h1>
            <div class="admin-info">
                Admin: ${userInfo.getName()}
            </div>
            <div>
                <h2>Welcome to the Admin Dashboard</h2>
                <p>This is where you can manage teachers, students, and enrollments.</p>
                <p>Use the sidebar to navigate through different sections.</p>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer>
        &copy; 2024 Learning Platform. All Rights Reserved.
    </footer>
</body>
</html>
