<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/adminstyle.css?v=1.0"> <!-- External CSS Link -->
<style>
body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fc; /* Light background color */
            color: #003366; /* Dark blue text color */
            margin: 0;
            padding: 0;
        }

        h1 {
            color: #003366;
            text-align: center;
            margin-top: 40px;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: white; /* White background for the form */
            border-radius: 8px; /* Rounded corners for the form */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Soft shadow around the form */
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

        input[type="text"], input[type="email"], textarea {
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

        input[type="radio"] {
            margin: 0 5px;
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
            width: 100%; /* Make the button stretch across the form */
        }

        button[type="submit"]:hover {
            background-color: #1a4d8f; /* Darker blue on hover */
        }

        button[type="submit"]:active {
            background-color: #002147; /* Even darker blue on click */
        }

        /* Form Error Handling (Optional) */
        .error {
            color: red;
            font-size: 14px;
        }
        /* Link Styling for Cancel */
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
<%@ include file="sidebar.jsp" %>
<div class="container">
        <h1>Editing Tr.${teacher.name} Form</h1>
        <form action="EditTeacherServlet" method="POST">
        	<input type="hidden" name="trId" value="${teacher.id}">
        
            <label for="full_name">Full Name</label>
            <input type="text" id="full_name" name="full_name" required="required" value="${teacher.name}">

            <label for="email">Email Address</label>
            <input type="email" id="email" name="email" required="required" value="${teacher.email}">

            <label for="qualification">Highest Qualification</label>
            <input type="text" id="qualification" name="qualification" required="required" value="${teacher.qualification}">

            <label for="address">Address</label>
            <textarea id="address" name="address" rows="4" required="required">${teacher.address}</textarea>


           
            

            <button type="submit" value="Create">Update Teacher</button>
            <a href="ShowTeacherServlet" class="cancel-link">Cancel</a>

        </form>
    </div>
</body>
</html>