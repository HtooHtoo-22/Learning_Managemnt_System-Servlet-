<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
.content {
            display: flex;
            flex-direction: column;
            justify-content: center;
            padding: 20px;
            margin-left: 20px;
            flex-grow: 1;
        }

        .classroom-header {
            display: flex;
            align-items: center;
            margin-bottom: 30px;
            margin-left: 170px;
        }

        .classroom-header img {
            width: 200px;
            height: auto;
            border-radius: 8px;
            margin-right: 20px;
        }

        .classroom-header .info {
            display: flex;
            flex-direction: column;
        }

        .classroom-header .info h2 {
            color: #003366;
            font-size: 2rem;
            margin-bottom: 10px;
        }

        .classroom-header .info p {
            color: #555;
            font-size: 1rem;
            margin-bottom: 5px;
        }

        .action-buttons {
            display: flex;
            gap: 10px;
            margin-top: 20px;
            justify-content: center;
        }

        .action-buttons a {
            text-decoration: none;
            color: white;
            background-color: #003366;
            padding: 10px 20px;
            border-radius: 5px;
        }

        .action-buttons a:hover {
            background-color: #1a4d8f;
        }

    
        .link-section {
            display: flex;
            gap: 15px;
            margin-top: 20px;
        }

        .link {
            text-decoration: none;
            color: #003366;
            font-size: 1rem;
            padding: 8px 15px;
            background-color: #f4f7fc;
            border-radius: 5px;
            border: 1px solid #003366;
            transition: background-color 0.3s, color 0.3s;
        }

        .link:hover {
            background-color: #003366;
            color: white;
        }
		#info {
    display: block;  /* Show the 'Info' section by default */
}
		
        /* Hide sections by default */
        .section-content {
            display: none;
            margin-top: 20px;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            margin-left: 170px;
        }
        .description-input {
    width: 90%; /* Full width of the container */
    height: 140px; /* Moderate height, not too big, but enough for comfortable reading */
    padding: 12px 15px; /* More padding to ensure the text doesn't feel cramped */
    border-radius: 10px; /* Rounded corners for a softer look */
    border: 2px solid #d1d8e0; /* Lighter border with a soft color */
    background-color: #4c9aff; /* Very light background color */
    color: #4f4f4f; /* Dark gray text for better readability */
    font-size: 1rem; /* Standard readable font size */
    font-family: 'Helvetica Neue', sans-serif; /* Modern font */
    line-height: 1.5; /* Better spacing between lines of text */
    resize: vertical; /* Allow vertical resizing */
    transition: all 0.3s ease; /* Smooth transition for interactive states */
}

.description-input:focus {
    border-color: #4c9aff; /* Soft blue border on focus */
    background-color: #ffffff; /* White background when focused */
    box-shadow: 0px 2px 8px rgba(0, 120, 255, 0.2); /* Light blue shadow on focus */
    outline: none; /* Remove the default outline */
}

textarea[readonly] {
    background-color: #e9ecef; /* Light grey background for readonly */
    color: #6c757d; /* Slightly lighter gray text for readability */
    border: 2px solid #d1d8e0; /* Subtle border */
    cursor: not-allowed; /* Show a "not-allowed" cursor when editing is disabled */
}
    table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            margin-left: 0px;
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
         .create-button {
            display: inline-block;
            background-color: grey;
            color: white;
            padding: 10px 20px;
            font-size: 16px;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
            transition: background-color 0.3s ease;
            margin-left:0px;
        }

        .create-button:hover {
            background-color: #1a4d8f; /* Darker blue on hover */
        }

        .create-button:active {
            background-color: #002147; /* Even darker blue on click */
        }    
        a i {
    margin-right: 8px; /* Space between the icon and the text */
}
  /* Modal Styles */
/* Modal Styles */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1000; /* On top of other content */
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.4); /* Semi-transparent background */
}

.modal:target {
    display: block; /* Display when targeted */
}

.modal-content {
    background-color: #fff;
    padding: 20px;
    border-radius: 5px;
    width: 350px;
    margin: 15% auto; /* Center the modal */
    text-align: center;
}

.modal h2 {
    margin-top: 0;
}

.modal p {
    font-size: 16px;
    margin-bottom: 20px;
}

/* Passcode input field */
.passcode-input {
    width: 200px;
    height: 50px;
    text-align: center;
    font-size: 32px;
    letter-spacing: 20px; /* Space between characters */
    border: 2px solid #ccc;
    border-radius: 5px;
    padding: 0;
    margin: 0;
}

.passcode-input:focus {
    border-color: #005c99; /* Highlight border on focus */
    outline: none;
}

.passcode-input::placeholder {
    color: transparent; /* Hide placeholder */
}

/* Button Styles */
button {
    padding: 10px 20px;
    margin: 10px;
    cursor: pointer;
}

/* Button Styles */
button {
    padding: 12px 25px;
    margin: 10px;
    cursor: pointer;
    font-size: 16px;
    border: none;
    border-radius: 5px;
    transition: all 0.3s ease-in-out; /* Smooth transition on hover */
    width: 150px; /* Make buttons a fixed width */
}

/* Close button */
#modalCloseBtn {
    background-color: #dc3545; /* Red for Close button */
    color: white;
}

#modalCloseBtn:hover {
    background-color: #c82333; /* Darker red on hover */
    transform: scale(1.05); /* Slightly enlarge the button on hover */
}

/* Join button */
#joinClassBtn {
    background-color: grey; /* Green for Join button */
    color: white;
}

#joinClassBtn:hover {
    background-color: #218838; /* Darker green on hover */
    transform: scale(1.05); /* Slightly enlarge the button on hover */
}

/* Button Focus (optional for accessibility) */
button:focus {
    outline: none;
    box-shadow: 0 0 0 4px rgba(0, 123, 255, 0.25); /* Blue outline on focus */
}
/* Styling for the error message */

.error-message.fade-out {
    opacity: 0; /* Fade out the error message */
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
</style>
</head>
<body>
<!-- Modal Structure -->
<div id="joinModal" class="modal">
    <div class="modal-content">
        <h2>Join The Class</h2>
        <p>Enter the class passcode:</p>
		<form action="joinClass" method="post">
		<input type="text" id="passcodeInput" class="passcode-input" name="passcode" maxlength="5" required="required"/>
		<button id="joinClassBtn">Yes, Join</button>
		<input type="hidden" name="classroomId" value="${classroom.id}">
		
		</form>
        <!-- Single input styled to look like 5 separate slots -->
        
		<button id="modalCloseBtn">Close</button>
        <!-- Buttons -->
        
        
    </div>
</div>

<div class="container">
<%@ include file="navigation.jsp" %>

<c:if test="${not empty error}">
         <div class="error-message">
            ${error}
        </div>
        </c:if>
        <!-- Classroom Header Section -->
        <div class="classroom-header">
            <img src="${classroom.imageUrl}" alt="Classroom Image"/>
            <div class="information">
                <h2>Title : ${classroom.title}</h2>
                <c:if test="${not empty userInfo}">
                <p><a href="javascript:void(0);" id="joinClassLink"><i class="fas fa-user-plus"></i> 
                <strong>Enroll The Class</strong></a></p>
                </c:if>
                
				 <c:if test="${ empty userInfo}">
                <p style="color:red">You need to log in first before joining the class.</p>
                </c:if>



                <div class="link-section">
                    <!-- Links to toggle different sections -->
                    <a href="javascript:void(0);" class="link" onclick="toggleSection('info')">Info</a>
                    <a href="javascript:void(0);" class="link" onclick="toggleSection('teacher')">Teacher</a>
                    
                </div>
            </div>
        </div>
 <div id="info" class="section-content">
    <h3>Classroom Info</h3>
    
    <!-- Display Description in a Read-Only Textarea -->
    <label for="description"><strong>Description:</strong></label><br>
    <textarea id="description" name="description" class="description-input" rows="4" readonly>${classroom.description}</textarea><br>

    <!-- Created Date (Formatted) -->
    <p><strong>Created On:</strong> 
        ${classroom.createdDate}
    </p>

    <!-- Created Admin Name -->
    <p><strong>Created By:</strong>Admin ${classroom.adminName}</p>
</div>
<div id="teacher" class="section-content">
            <h3>Teacher Info</h3>
             <table>
       
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Gender</th>
                    <th>Address</th>
                    <th>Qualification</th>
                    <th>Joined Date</th>
                    
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
                        
                        <td>${teacher.enrollmentDate}</td>
                        
                    </tr>
                </c:forEach>
                
            </tbody>
             
        </table>
        
        </div>
        

</div>
<script>
function toggleSection(sectionId) {
    // Hide all sections
    var sections = document.querySelectorAll('.section-content');
    sections.forEach(function(section) {
        section.style.display = 'none';
    });

    // Show the selected section
    var section = document.getElementById(sectionId);
    if (section) {
        section.style.display = 'block';
    }
}
var modal = document.getElementById('joinModal');
var joinClassLink = document.getElementById('joinClassLink');
var modalCloseBtn = document.getElementById('modalCloseBtn');
var joinClassBtn = document.getElementById('joinClassBtn');

// When the user clicks the "Join The Class" link, open the modal
joinClassLink.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks the "Close" button, close the modal
modalCloseBtn.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks the "Yes, Join" button, close the modal
joinClassBtn.onclick = function() {
    // Add functionality to join the class (e.g., submit a form or make an API call)
    
    modal.style.display = "none"; // Close the modal
}

// When the user clicks anywhere outside the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
const passcodeInput = document.getElementById("passcodeInput");

// Automatically move focus when the user types
passcodeInput.addEventListener("input", function () {
    const value = passcodeInput.value;
    
    // If 5 characters are entered, stop typing
    if (value.length === 5) {
        passcodeInput.blur(); // Optional: remove focus after entering 5 digits
    }
});

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