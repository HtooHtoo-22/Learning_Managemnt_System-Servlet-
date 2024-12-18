<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>Classroom Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fc;
            margin: 0;
            padding: 0;
        }
        h1 {
            color: #003366;
            text-align: center;
            margin-top: 20px;
        }
        .container {
            display: flex;
            margin: 0 auto;
            padding: 20px;
            max-width: 1200px;
        }

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

        .passcode {
            font-weight: bold;
            color: #FF5733;
            background-color: #f0f0f0;
            padding: 5px 10px;
            border-radius: 5px;
            font-size: 1.2rem;
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
        .material-box {
    border: 1px solid #ccc;  /* Border around the box */
    border-radius: 8px;  /* Rounded corners */
    padding: 15px;  /* Padding inside the box */
    margin-bottom: 15px;  /* Space between material boxes */
    margin-top: 14px;
    background-color: #f9f9f9;  /* Light background color */
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);  /* Subtle shadow for 3D effect */
}

.material-item {
    display: flex;
    justify-content: space-between;  /* Ensure title is left, actions on the right */
    align-items: center;
    padding: 10px;
    border-bottom: 1px solid #ddd;  /* Optional: for separating items visually */
    width: 100%;  /* Ensure it stretches across the available width */
}

.material-title {
    flex: 1;  /* Ensure title takes available space */
    font-weight: bold;
    margin-left: 10px; /* Space between the icon and title */
    overflow: hidden;  /* Prevents long titles from overflowing */
    text-overflow: ellipsis;  /* Adds ellipsis (...) if title is too long */
    white-space: nowrap;  /* Prevents the title from wrapping onto a new line */
}

.material-icon {
    font-size: 20px;  /* Adjust the size of the icon */
    margin-right: 10px; /* Space between the icon and the title */
    color: #007BFF;  /* You can adjust the color */
}

.action-link {
    display: inline-flex;
    align-items: center;
    margin-left: 10px; /* Spacing between the action buttons */
    text-decoration: none;
    color: green;  /* Action link color */
}

.action-link i {
    font-size: 18px; /* Icon size */
}

/* Hover effect for action links */
.action-link:hover {
    color: #0056b3;
}

.material-icon {
    font-size: 40px;  /* Adjust the size of the icon */
    margin-right: 10px;  /* Space between the icon and the title */
    color: #007BFF;  /* You can adjust the color */
}
        
    </style>
</head>
<body>
<%@ include file="sidebar.jsp" %>

<div class="container">
    <!-- Sidebar (Already included with sidebar.jsp) -->

    <!-- Main Content Section -->
    <div class="content">
        <h1>Classroom Details</h1>

        <!-- Classroom Header Section -->
        <div class="classroom-header">
            <img src="${classroom.imageUrl}" alt="Classroom Image"/>
            <div class="information">
                <h2>Title : ${classroom.title}</h2>
                <p><strong>Passcode:</strong> <span class="passcode">${classroom.passcode}</span></p>
                <div class="link-section">
                    <!-- Links to toggle different sections -->
                    <a href="javascript:void(0);" class="link" onclick="toggleSection('info')">Info</a>
                    <a href="javascript:void(0);" class="link" onclick="toggleSection('teacher')">Teacher</a>
                    <a href="javascript:void(0);" class="link" onclick="toggleSection('student')">Student</a>
                    <a href="javascript:void(0);" class="link" onclick="toggleSection('enrollment')">Enrollment</a>
                    <a href="javascript:void(0);" class="link" onclick="toggleSection('material')">Material</a>
                </div>
            </div>
        </div>

        <!-- Dynamic Content Sections -->
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
                    
                    <th>Enrollment Date</th>
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
                        <td>${teacher.enrollmentDate}</td>
                        <td>
                        <a href="DeleteTeacherEnrollmentServlet?id=${teacher.id}"> <i class="fas fa-trash-alt"></i>  </a>
                        </td>
                    </tr>
                </c:forEach>
                
            </tbody>
             
        </table>
        
        </div>

        <div id="student" class="section-content">
            <h3>Student Info</h3>
            <table>
       
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Gender</th>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
                    <th>City</th>  
                    <th>Enrollment Date</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <!-- Loop through teacherList and display each teacher in a table row -->
                <c:forEach items="${studentList}" var="student">
                    <tr>
                        <td>${student.name}</td>
                        <td>${student.email}</td>
                        <td>${student.gender}</td>
                        <td>${student.city}</td>
                      <%--   <td><fmt:formatDate value="${student.enrollmentDate}" pattern="yyyy-MM-dd" /></td> --%>
                        <td>
                        <a href="DeleteStudentEnrollmentServlet?studentId=${student.id}"> <i class="fas fa-trash-alt"></i>  </a>
                        </td>
                    </tr>
                </c:forEach>
                
            </tbody>
             
        </table>
        </div>

        <div id="enrollment" class="section-content">
            <h3>Enrollment Info</h3>
            <p>This section contains information about the enrollments.</p>
        </div>

        <div id="material" class="section-content">
    <h3>Classroom Material</h3>
    
    <!-- Link to Create Material -->
   
    <!-- Loop through material list and display each material with an icon -->
   <c:forEach items="${materialList}" var="material">
    <div class="material-box">
        <div class="material-item">
        	<span class="material-icon"><i class="fa fa-file-alt"></i></span>
        	
            <span class="material-title">${material.title}</span>
            <!-- View Action with Icon -->
            <a href="ViewMaterialServlet?materialId=${material.id}" class="action-link">
                <i class="fa fa-eye"></i>
            </a>
            <!-- Delete Action with Icon -->
            <a href="DeleteMaterialServlet?materialId=${material.id}&classID=${classroom.id}" class="action-link" style="color:red">
                <i class="fa fa-trash"></i>
            </a>
        </div>
    </div>
   </c:forEach>
</div>

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
</script>

</body>
</html>
