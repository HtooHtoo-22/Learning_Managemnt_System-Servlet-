<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Teacher List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/adminstyle.css?v=1.0"> <!-- External CSS Link -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">	
    <style>
        /* Table Styling */
        table {
            width: 70%;
            border-collapse: collapse;
            margin-top: 20px;
            margin-left: 300px;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #003366;
            color: white;
        }

        tr:hover {
            background-color: #f1f1f1; /* Hover effect for rows */
        }

        /* General Page Styling */
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
    z-index: 1; /* Ensure the container is above the sidebar */
}


        /* Button Styling (Link styled as a button) */
        .create-button {
            display: inline-block;
            background-color: #003366;
            color: white;
            padding: 10px 20px;
            font-size: 16px;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
            transition: background-color 0.3s ease;
            margin-left: 300px;
        }

        .create-button:hover {
            background-color: #1a4d8f; /* Darker blue on hover */
        }

        .create-button:active {
            background-color: #002147; /* Even darker blue on click */
        }
        .cancel-link {
    display: inline-block;
    background-color: #f44336; /* Red color for the cancel link */
    color: white;
    padding: 10px 20px;
    font-size: 16px;
    text-align: center;
    text-decoration: none; /* Remove underline */
    border-radius: 5px;
    transition: background-color 0.3s ease;
    margin-top: 60px; /* Add some space from surrounding elements */
    margin-left: 300px;
}

.cancel-link:hover {
    background-color: #d32f2f; /* Darker red on hover */
}

.cancel-link:active {
    background-color: #b71c1c; /* Even darker red on click */
}
    </style>
</head>
<body>

    <!-- Include Sidebar -->
    <%@ include file="sidebar.jsp" %>

    <div class="container">
        <h1>Deleted Teacher List</h1>

        <!-- Teacher List Table -->
        <table>
        
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Gender</th>
                    <th>Address</th>
                    <th>Qualification</th>
                    <th>Generated Password</th>
                    <th>Created Admin Name</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <!-- Loop through teacherList and display each teacher in a table row -->
                <c:forEach items="${teacherList}" var="teacher">
                    <tr>
                        <td>${teacher.name}</td>
                        <td>${teacher.email}</td>
                        <td>${teacher.gender}</td>
                        <td>${teacher.address}</td>
                        <td>${teacher.qualification}</td>
                        <c:if test="${not empty teacher.generate_password}">
                        <td>${teacher.generate_password}</td>
                        </c:if>
                        <c:if test="${empty teacher.generate_password}">
                        <td>Logged In!</td>
                        </c:if>
                        
                        <td>${teacher.adminName}</td>
                        <td>
                        <form action="restoreTeacher" method="post">
                        	<input type="hidden" name="teacherId" value="${teacher.id}">
                        	<button type="submit" class="recycle-btn">
        						<i class="fas fa-recycle"></i> 
    						</button>
                        </form>
                        
                        </td>
                    </tr>
                </c:forEach>
                
            </tbody>
            
        </table>
	
        <!-- Create New Teacher Button (Styled link) -->
        <a href="showTeacher" class="cancel-link">Cancel</a>
    </div>
    
</body>
</html>
