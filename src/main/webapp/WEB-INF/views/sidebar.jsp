<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <style>
        /* Sidebar styles */
        .sidebar {
            width: 250px;
            background-color: #003366; /* Blue sidebar */
            color: white;
            padding-top: 50px;
            position: fixed;
            height: 100%;
            z-index: 1000;
        }

        .sidebar a {
            display: block;
            color: white;
            padding: 20px;
            text-decoration: none;
            font-size: 18px;
            border-bottom: 1px solid #ddd;
            margin-bottom: 15px; /* Space between items */
            transition: background-color 0.3s ease, padding-left 0.3s ease;
        }

        .sidebar a:hover {
            background-color: #1a4d8f; /* Darker blue on hover */
            padding-left: 30px; /* Shift text to the right on hover */
        }

        .sidebar a:last-child {
            border-bottom: none; /* Remove border from the last link */
        }

        .sidebar h2 {
            color: white;
            text-align: center;
        }
        
    </style>
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <div class="sidebar">
        <h2>Admin Panel</h2>
        <a href="showTeacher">Teacher Management</a>
        <a href="showStudent">Student Management</a>
        <a href="showClassroom">Classroom Management</a>
        <a href="#">Enrollment</a>
        <a href="#">Settings</a>
        
        <a href="logout" class="logout-link">
  <i class="fas fa-sign-out-alt"></i> Log Out
</a>
	
    </div>
</body>
</html>
