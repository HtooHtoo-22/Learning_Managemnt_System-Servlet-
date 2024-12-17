<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Classroom</title>
    <style>
        /* General Page Styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fc;
            color: #003366;
            margin: 0;
            padding: 0;
            display: flex; /* Flexbox layout for sidebar and content */
        }

      
        .container {
            max-width: 600px;
            margin: 40px auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            flex: 1; /* Allow content area to take up remaining space */
        }

        h1 {
            color: #003366;
            text-align: center;
            margin-top: 40px;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        label {
            font-size: 16px;
            margin-bottom: 5px;
            color: #003366;
        }

        input[type="text"], input[type="file"], input[type="date"], textarea {
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 5px;
            width: 100%;
            box-sizing: border-box;
        }

        textarea {
            resize: vertical;
        }

        button[type="submit"] {
            background-color: #003366;
            color: white;
            padding: 12px 20px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            width: 100%;
        }

        button[type="submit"]:hover {
            background-color: #1a4d8f;
        }

        button[type="submit"]:active {
            background-color: #002147;
        }

        .error {
            color: red;
            font-size: 14px;
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
    margin-top: 20px; /* Add some space from surrounding elements */
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
    <%@ include file="sidebar.jsp" %> <!-- This will include the sidebar content from sidebar.jsp -->

    <div class="container">
        <h1>Create Classroom</h1>

        <!-- Display Error Message (Optional) -->
        <div class="error">
            <h3>${error}</h3>
        </div>
	<form:form action="createClass" method="post" modelAttribute="classObj" enctype="multipart/form-data">
     <label for="image">Classroom Image</label>
     <form:input path="imageFile" type="file" required="true"/>
     <label for="title">Classroom Title</label>
     <form:input path="title" id="title" required="true"/>
     <label for="description">Classroom Description</label>
     <form:textarea path="description" id="description" rows="4" required="true"/>
     <label for="created_date">Created Date</label>
     <input type="date" id="created_date" name="created_date" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>" readonly>
	 <label for="teacher">Select Teacher</label>
	 <form:select path="teachers" id="teacher" multiple="multiple" required="true" style="width: 100%; height: 150px; font-size: 16px;">
        <form:option value="">-- Select Teachers --</form:option>
        <c:forEach var="teacher" items="${teacherList}">
            <form:option value="${teacher.id}">${teacher.name}</form:option>
        </c:forEach>
    </form:select>	
     <label for="created_admin_name">Created By (Admin Name)</label>
     <input type="text" id="created_admin_name" name="created_admin_name" value="${adminName}" readonly> <!-- Replace with actual admin name -->
    <button type="submit">Submit</button>
    <a href="showClassroom" class="cancel-link">Cancel</a>
	</form:form>

        <!-- Classroom Creation Form -->
   
    </div>

</body>
</html>
