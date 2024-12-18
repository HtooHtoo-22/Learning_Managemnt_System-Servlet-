<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Form</title>
    <style>
        /* General Page Styling */
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

        input[type="text"], input[type="email"], input[type="password"], textarea, select {
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

        .gender-label {
            display: inline-block;
            margin-right: 15px;
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

        .error {
            color: red;
            font-size: 14px;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>Student Registration</h1>
        <div class="error">
            <h3>${error}</h3> <!-- Display error message if any -->
        </div>
       
<form:form action="registerStudent" method="post" modelAttribute="studentObj">
    <!-- Full Name Field -->
    <label for="name">Full Name</label>
    <form:input path="name" id="name" required="true" />
    
    <!-- Email Field with email format validation -->
    <label for="email">Email Address</label>
    <form:input path="email" id="email" required="true" 
        pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"
        title="Please enter a valid email address (e.g., example@domain.com)" />

    <!-- Password Field with minimum length -->
    <label for="password">Password</label>
    <form:password path="password" id="password"  required="true" minlength="8" 
        title="Password must be at least 8 characters long" />

    <!-- Confirm Password Field -->
    <label for="confirm_password">Confirm Password</label>
     <input type="password" id="confirm_password" name="confirm_password" required
                minlength="8" title="Password must be at least 8 characters long">

    <!-- City Dropdown -->
    <label for="city">City</label>
    <form:select path="city" id="city" required="true">
        <form:option value="Yangon" label="Yangon" />
        <form:option value="Mandalay" label="Mandalay" />
        <form:option value="Naypyidaw" label="Naypyidaw" />
        <form:option value="Mawlamyine" label="Mawlamyine" />
        <form:option value="Pathein" label="Pathein" />
        <form:option value="Bago" label="Bago" />
        <form:option value="Taunggyi" label="Taunggyi" />
        <form:option value="Myitkyina" label="Myitkyina" />
        <form:option value="Hpa-An" label="Hpa-An" />
    </form:select>

    <!-- Address Field -->
  
    <!-- Gender Field -->
    <label>Gender</label>
    <div>
        <label class="gender-label">
            <form:radiobutton path="gender" value="Male" required="true" /> Male
        </label>
        <label class="gender-label">
            <form:radiobutton path="gender" value="Female" required="true" /> Female
        </label>
    </div>

    <!-- Submit Button -->
    <button type="submit">Submit</button>
</form:form>

        
      
    </div>

</body>
</html>
 