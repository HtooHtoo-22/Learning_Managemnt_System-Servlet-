<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>Teacher Page</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
    <style>
        /* General Body and Layout Styling */
       

        .container {
            width: 100%;
            margin: 0 auto;
            
        }

        h1 {
            text-align: center;
            color: #003366;
            margin-bottom: 30px;
        }

        /* Navigation Bar styling (included from navigation.jsp) */
        
        /* Classroom List Layout */
        .classroom-list {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 20px;
            padding-top: 20px;
        }

        /* Classroom Card Styling */
        .classroom-card {
            background-color: #fff;
            border-radius: 50px;
            overflow: hidden;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            padding: 40px;
            text-align: center;
        }

        .classroom-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
        }

        /* Classroom Image Styling */
        .classroom-card img {
            max-width: 100%;
            height: 200px;
            object-fit: cover;
            border-radius: 8px;
        }

        /* Classroom Title Styling */
        .classroom-card h3 {
            font-size: 20px;
            color: #333;
            margin-top: 15px;
            margin-bottom: 15px;
        }

        /* Action Links Styling */
        .action-links a {
            display: inline-block;
            padding: 10px 20px;
            background-color: #003366;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .action-links a:hover {
            background-color: #005c99;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            .classroom-list {
                grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
            }

           
        }
		.error-message {
    background-color: #f8d7da; /* Light red background */
    color: #721c24; /* Dark red text */
    border: 1px solid #f5c6cb; /* Red border */
    padding: 15px;
    margin: 20px 0;
    border-radius: 5px;
    font-size: 16px;
    text-align: center;
    opacity: 1; /* Initially fully visible */
    transition: opacity 1s ease-out; /* Smooth fade-out transition */
}

/* This class will be added by JavaScript to fade out the error message */
.error-message.fade-out {
    opacity: 0; /* Fade out the error message */
}
		
    </style>
</head>
<body>
    <div class="container">
        <%@ include file="trnavigation.jsp" %>
        <c:if test="${not empty error}">
         <div class="error-message">
            ${error}
        </div>
        </c:if>
       
        <div class="classroom-list">
            <c:forEach var="classroom" items="${classList}">
                <div class="classroom-card">
                    <!-- Display classroom image -->
                    <img src="${classroom.imageUrl}" alt="Classroom Image" />
                    <!-- Classroom Title -->
                    <h3>${classroom.title}</h3>
                    <!-- View Details and Delete links -->
                    <div class="action-links">
                        <a href="viewTeacherClassDetail?id=${classroom.id}">Watch</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <script>
    window.addEventListener("DOMContentLoaded", function() {
    // Get the error message element
    var errorMessage = document.querySelector(".error-message");

    // If the error message exists, remove it after 3 seconds
    if (errorMessage) {
        setTimeout(function() {
            // Add the fade-out class to trigger the fade-out effect
            errorMessage.classList.add("fade-out");

            // After the fade-out transition is complete, remove the element
            setTimeout(function() {
                errorMessage.remove();
            }, 1000); // Wait 1 second for the fade-out effect to finish
        }, 3000); // 3 seconds before starting the fade-out
    }
});
    </script>
</body>
</html>
