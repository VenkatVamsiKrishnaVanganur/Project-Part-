<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<div align="center">
    <a href="login.jsp" target="_self" style="background-color: #FF0000; color: #fff; padding: 10px; border: none; border-radius: 5px; text-decoration: none; display: inline-block; margin-bottom: 20px; font-size: 18px;">Logout</a><br><br> 

    <h1 style="background-color: transparent;">List all users</h1>
    <div align="center">
        <table border="1" cellpadding="6" style="background-color: #add8e6; border-radius: 10px; border-collapse: collapse; margin-bottom: 20px;">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>Email</th>
                <th>FirsName</th>
                <th>LastName</th>
                <th>Password</th>
                <th>Role</th>
            </tr>
            <c:forEach var="user" items="${get_user}">
                <tr style="text-align:center">
                    <td>${user.email}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.password}</td>
                    <td>${user.role}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<body>
</body>
</html>