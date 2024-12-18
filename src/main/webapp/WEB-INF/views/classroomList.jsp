<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>Classroom Management</title>
    <style>
       body {
    font-family: Arial, sans-serif;
    background-color: #f4f7fc;
    color: #003366;
    margin: 0;
    padding: 0;
}

h1 {
    color: #003366;
    text-align: center;
    padding-top: 20px;
}

.container {
    z-index: 1;
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
}

.create-button {
    display: inline-block;
    background-color: #003366;
    color: white;
    padding: 10px 20px;
    font-size: 16px;
    text-align: center;
    text-decoration: none;
    border-radius: 5px;
    margin: 20px 0;
    margin-left: 300px;
    transition: background-color 0.3s ease;
}

.create-button:hover {
    background-color: #1a4d8f;
}

.create-button:active {
    background-color: #002147;
}

/* Responsive Grid Layout */
.classroom-list {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr)); /* Make cards smaller */
    gap: 15px;
    margin-top: 0px;
    margin-left: 200px;
}

/* Classroom Card */
.classroom-card {
    background-color: white;
    padding: 15px; /* Smaller padding for a more compact card */
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    text-align: center;
    overflow: hidden;
    transition: transform 0.3s ease, box-shadow 0.3s ease; /* Add smooth transition for hover */
}

/* Card Hover Effect */
.classroom-card:hover {
    transform: scale(1.05); /* Slight zoom effect on hover */
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2); /* Darker shadow on hover */
}

/* Image Styling */
.classroom-card img {
    width: 70%;
    height: auto;
    max-height: 150px; /* Make image smaller */
    object-fit: cover;
    border-radius: 8px;
}

/* Classroom Title */
.classroom-card h3 {
    color: #003366;
    margin-top: 10px; /* Reduced margin for tighter layout */
    font-size: 1.1rem; /* Slightly smaller title */
}

/* Links Styling */
.classroom-card a {
    display: block;
    margin-top: 8px; /* Reduced margin for better alignment */
    text-decoration: none;
    color: #003366;
    font-size: 14px; /* Slightly smaller font size */
}

.classroom-card a:hover {
    color: #1a4d8f;
}

.classroom-card .action-links a {
    margin: 5px;
    font-size: 14px;
    color: #003366;
}

.classroom-card .action-links a:hover {
    color: #1a4d8f;
}

    </style>
</head>
<body>
<%@ include file="sidebar.jsp" %>

<div class="container">
    <h1>Classroom Management</h1>
    

    <!-- Classroom list -->
    <div class="classroom-list">
        <c:forEach var="classroom" items="${classList}">
            <div class="classroom-card">
                <!-- Display classroom image -->
                <img src="${classroom.imageUrl}" alt="Classroom Image" />
                
                <!-- Classroom Title -->
                <h3>${classroom.title}</h3>
                <!-- View Details and Delete links -->
                <div class="action-links">
                    <a href="viewClassroomDetail?id=${classroom.id}">View Details</a>
                    <a href="deleteClass?id=${classroom.id}" onclick="return confirm('Are you sure you want to delete this classroom?')" style="color: red;">Delete</a>
                </div>
            </div>
        </c:forEach>
       
    </div>
</div>
 <a href="showCreateClass" class="create-button">Create Classroom</a>
</body>
</html>
