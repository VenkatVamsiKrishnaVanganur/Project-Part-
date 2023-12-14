<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tree Details</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        height: 100vh;
        background-color: #f4f4f4;
    }

    .logout {
        background-color: #FF0000;
        color: #fff;
        padding: 10px;
        border: none;
        border-radius: 5px;
        text-decoration: none;
        display: inline-block;
        margin-bottom: 20px;
        font-size: 18px;
    }

    table {
        width: 80%;
        margin: 0 auto;
        background-color: rgba(173, 216, 230, 0.7);
        border-radius: 10px;
        border-collapse: collapse;
        margin-bottom: 20px;
    }

    table caption {
        text-align: center;
        font-size: 24px;
        color: #000;
    }

    table th, table td {
        border: 1px solid #ddd;
        padding: 15px;
        text-align: center;
    }

    table th {
        background-color: #add8e6;
        color: #000;
    }

    .returntologin {
        text-align: center;
    }

    .returntologin a {
        background-color: #FF0000;
        color: #fff;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 18px;
        text-decoration: none;
    }
</style>
</head>
<body>
    <div align="center">
        <a class="logout" href="login.jsp" target="_self">Logout</a><br><br>
        <div align="center">
            <table border="1" cellpadding="6">
                <caption><h2>List of Trees</h2></caption>
                <tr>
                    <th>Tree Id</th>
                    <th>Quote Id</th>
                    <th>Size</th>
                    <th>Height</th>
                    <th>Location</th>
                    <th>Note</th>
                </tr>
                <c:forEach var="tree" items="${listOfTrees}">
                    <tr style="text-align:center">
                        <input type="hidden" name="quote_id" value="${tree.quote_id}" />
                        <td><c:out value="${tree.tree_id}" /></td>
                        <td><c:out value="${tree.quote_id}" /></td>
                        <td><c:out value="${tree.size}" /></td>
                        <td><c:out value="${tree.height}" /></td>
                        <td><c:out value="${tree.location}" /></td>
                        <td><c:out value="${tree.note}" /></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <p class="returntologin">
            <a href="#" onclick="goBack();">BACK</a>
        </p>
    </div>
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</body>
</html>