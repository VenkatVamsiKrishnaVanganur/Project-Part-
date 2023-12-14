<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Root Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            height: 100vh;
        }

        h1 {
            text-align: center;
            color: #000;
            font-size: 32px;
            padding: 20px 0;
        }

        center {
            text-align: center;
        }

        .button-container {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .initialize-button, .viewReports-button{
            background-color: #007BFF;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            margin-top: 10px;
            cursor: pointer;
            font-size: 18px;
            display: block;
            margin: 0 auto;
        }

        .logout-link {
            display: block;
            margin-top: 10px;
            text-decoration: none;
            background-color: #FF0000;
            color: #fff;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            display: inline-block;
        }

        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }

        table th, table td {
            border: 1px solid #ddd;
            padding: 15px;
            text-align: center;
        }

        table th {
            background-color: #007BFF;
            color: #fff;
        }
    </style>
</head>
<body>
    <center>
        <form action="initialize">
           <input type="submit" value="Initialize the Database" class="initialize-button" />
        </form>
        <form action="viewReports">
           <input type="submit" value="View Reports" class="viewReports-button" />
        </form>
        <a href="login.jsp" target="_self" class="logout-link">Logout</a><br><br>

        <div align="center">
            <table border="1" cellpadding="6">
                <caption><h2>List of Users</h2></caption>
                <tr>
                    <th>Email</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Password</th>
                    <th>Role</th>
                </tr>
                <c:forEach var="users" items="${listUser}">
                    <tr style="text-align:center">
                        <td><c:out value="${users.email}" /></td>
                        <td><c:out value="${users.firstName}" /></td>
                        <td><c:out value="${users.lastName}" /></td>
                        <td><c:out value="${users.password}" /></td>
                        <td><c:out value="${users.role}" /></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </center>
</body>
</html>