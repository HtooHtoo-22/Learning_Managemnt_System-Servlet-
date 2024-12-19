<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>Navigation Bar</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <style>
        /* General navbar styling */
        /* General navbar styling */
.navbar {
    background-color: #003366; /* Dark blue background */
    overflow: hidden;
    padding: 10px;
    position: fixed; /* Make the navbar fixed */
    top: 0; /* Stick to the top of the page */
    left: 0; /* Align to the left edge */
    width: 100%; /* Make the navbar span the full width of the page */
    z-index: 1000; /* Ensure it stays above other content */
}

.navbar a {
    float: left;
    display: block;
    color: white;
    text-align: center;
    padding: 14px 20px;
    text-decoration: none;
    font-size: 18px;
}

.navbar a:hover {
    background-color: #ddd; /* Light gray on hover */
    color: black;
}

.navbar a.active {
    background-color: #005c99; /* A lighter blue for the active link */
}

/* Additional styling for the body to prevent content from hiding behind the navbar */
body {
    font-family: Arial, sans-serif;
    background-color: #ffffff;
    margin: 0;
    padding: 0;
    padding-top: 60px; /* Add padding to the top to avoid content being hidden under the navbar */
}

/* Styling for other content */
h1 {
    color: #003366;
    text-align: center;
    margin-top: 50px;
}

.container {
    width: 100%;
    margin-top: 30px;
}

    </style>
</head>
<body>
<!-- Navigation Bar -->
<div class="navbar">
    <a href="teacher.jsp" >Home</a>
    <a href="viewMyClassroomTeacher">My Classroom</a>
    <a href="/dashboard">Dashboard</a>
   <c:if test="${not empty userInfo}">
            <!-- If userInfo exists, show Profile link -->
            <a href=""><i class="fas fa-user"></i>Tr. ${userInfo.name}</a>
             <a href="logout" class="logout-link">
  <i class="fas fa-sign-out-alt"></i> Log Out
</a>
        </c:if>
    <c:if test="${empty userInfo}">
    <a href="login"><i class="fas fa-sign-in-alt"></i>Log In</a>
    </c:if>
    
   
</div>
</body>
</html>
