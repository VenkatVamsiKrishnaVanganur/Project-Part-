<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Request for Quote</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            text-align: center;
        }

        h1.title {
            color: #002f87;
        }

        table {
            width: 80%;
            margin-top: 20px;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: center;
        }

        th {
            background-color: #007BFF;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        caption {
            font-size: 24px;
            margin-bottom: 10px;
        }

        .errormsg {
            color: #FF0000;
        }

        .btns {
            margin-top: 20px;
        }

        .add_tree_button {
            background-color: #007BFF;
            color: white;
            padding: 8px 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            text-decoration: none;
            display: inline-block;
            transition: background-size 0.3s ease-in-out;
            background-size: 100% 100%;
        }

        .add_tree_button:hover {
            background-size: 100% 120%;
        }

        .add_tree_button a {
            color: white;
            text-decoration: none;
        }

        .submit_button {
            background-color: #FF0000;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <center>
        <h1 class="title">Request for Quote</h1>
    </center>

    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of successfully added trees</h2></caption>
            <tr>
                <th>Tree ID</th>
                <th>Size</th>
                <th>Height</th>
                <th>Note</th>
            </tr>
            <c:forEach var="tree" items="${treesAdded}">
                <tr>
                    <td><c:out value="${tree.tree_id}" /></td>
                    <td><c:out value="${tree.size}" /></td>
                    <td><c:out value="${tree.height}" /></td>
                    <td><c:out value="${tree.note}" /></td>
                </tr>
            </c:forEach>
        </table>

        <p class="errormsg">${error}</p>

        <form>
            <div class="btns" align="center">
                <p class="add_tree_button"><a href="tree.jsp" target="_self">Add Tree Details</a></p>
                <p>
                    <a href="#" class="submit_button" onclick="goToPage('clientView.jsp');">Submit</a>
                </p>
            </div>
        </form>
    </div>

    <script>
        function goToPage(url) {
            window.location.href = url;
        }
    </script>
</body>
</html>