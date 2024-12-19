<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Change Password</title>

<!-- Style for the White and Blue UI -->
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f7fc; /* Light background for a clean look */
        color: #003366; /* Dark blue text for contrast */
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        flex-direction: column;
    }

    h1 {
        color: #003366;
        margin-bottom: 30px;
    }

    /* Form container styles */
    .form-container {
        background-color: white;
        padding: 30px;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        width: 100%;
        max-width: 400px;
        text-align: center;
    }

    .form-container input[type="text"],
    .form-container input[type="password"] {
        width: 100%;
        padding: 10px;
        margin: 10px 0;
        border: 1px solid #ddd;
        border-radius: 5px;
        font-size: 16px;
    }

    .form-container input[type="submit"] {
        background-color: #003366;
        color: white;
        border: none;
        padding: 15px;
        font-size: 16px;
        border-radius: 5px;
        cursor: pointer;
        width: 100%;
        transition: background-color 0.3s ease;
    }

    .form-container input[type="submit"]:hover {
        background-color: #1a4d8f; /* Darker blue on hover */
    }

    .form-container a {
        display: inline-block;
        margin-top: 20px;
        color: #003366;
        text-decoration: none;
        font-size: 16px;
    }

    .form-container a:hover {
        text-decoration: underline;
    }

    .error-message {
        color: red;
        font-size: 14px;
        margin-top: 10px;
    }
</style>

<!-- JavaScript for password validation -->
<script>
    function validatePasswords() {
        var password = document.getElementById("newPassword").value;
        var confirmPassword = document.getElementById("confirmPassword").value;

        if (password !== confirmPassword) {
            document.getElementById("error-message").innerText = "Passwords do not match!";
            return false; // Prevent form submission
        } else {
            document.getElementById("error-message").innerText = "";
            return true; // Allow form submission
        }
    }
</script>

</head>
<body>

<h1>Create Your Password</h1>

<div class="form-container">
    <form action="changeTeacherPassword" method="post" onsubmit="return validatePasswords()">
        <label for="newPassword">New Password:</label>
        <input type="password" name="newPassword" id="newPassword" required>
        
        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" name="confirmPassword" id="confirmPassword" required>
        
        <input type="submit" value="Change Password">
        
        <!-- Error message for password mismatch -->
        <div id="error-message" class="error-message"></div>
        
        <a href="login.jsp">Back to Login</a> <!-- Optional link to login page -->
        <input type="hidden" name="teacherId" value="${teacherId}">
    </form>
</div>

</body>
</html>
