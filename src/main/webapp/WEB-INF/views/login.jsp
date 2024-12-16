<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login</title>
   	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/student.css">
</head>
<body>
    <h1>Login</h1>
    <div class="error-message">
        ${error} 
    </div>
    <div class="form-container">    
        <form:form action="doLogin" method="post" modelAttribute="loginObj">
        <label for="userEmail">Email</label><br>
        <form:input path="email" id="userEmail"/>
        <label for="userPassword">Password</label><br>
        <form:input path="password" id="userPassword" type="password"/>
        <input type="submit" value="Login">
        </form:form>
        <a href="StudentRegisterServlet">Create Account</a><br>
        <a href="student.jsp">Home</a>
    </div>
</body>
</html>
