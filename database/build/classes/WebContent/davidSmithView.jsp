
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>David Smith View</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 10px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    th, td {
        border: 1px solid #ddd;
        padding: 10px;
        text-align: center;
    }

    th {
        background-color: #007BFF; /* Blue background color */
        color: white;
        font-size: 16px;
    }

    tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    form {
        display: inline;
    }

    input[type="submit"] {
        padding: 12px 16px;
        border: 1px solid #007BFF;
        border-radius: 4px;
        cursor: pointer;
        background-color: #007BFF;
        color: white;
    }

    input[type="submit"]:hover {
        background-color: #0056b3;
    }

    .logout, .quoteHistory , .ViewOrders{
        display: inline-block;
        padding: 10px 14px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        color: white;
    }

    .logout {
        background-color: #FF3333;
    }

    .quoteHistory {
        background-color: #0088FF;
    }
    .ViewOrders {
        background-color: #0088FF;
    }
</style>
</head>
<body>

<div align="center">
    <form action="login.jsp" method="get">
        <button class="logout" type="submit">Logout</button>
    </form>
    <br><br>
    <form action="quoteHistory" method="get">
        <button class="quoteHistory" type="submit">Quote History</button>
    </form>
    <br><br>
    <form action="ViewOrders" method="get">
        <button class="ViewOrders" type="submit">View Orders</button>
    </form>
     <br><br>
    
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Quotes</h2></caption>
            <tr>
                <th>Client Id</th>
                <th>Quote Id</th>
                <th>Request Date</th>
                <th>Price</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Client Status</th>
                <th>Status</th>
                <th>Note</th>
                <th>Update</th>
                <th>View Tree Details</th>
            </tr>
            <c:forEach var="quote" items="${listQuotes}">
                <tr>
                    <form action="updateQuote" method="post">
                        <input type="hidden" name="quote_id" value="${quote.quote_id}" />
                        <input type="hidden" name="client_id" value="${quote.client_id}" />     
                        <td><input type="number" value="${quote.client_id}" disabled /></td>
                        <td><input type="number" value="${quote.quote_id}" disabled /></td>
                        <td><input type="date" name="propose_date" value="${quote.propose_date}" disabled /></td>
                        <td><input type="number" name="price" value="${quote.price}" /></td>
                        <td><input type="date" name="start_date" value="${quote.start_date}" /></td>
                       <td><input type="date" name="end_date" value="${quote.end_date}" /></td>
                        <td><input type="text" name="status" value="${quote.status}" /></td>
                        <td>
                            <select name="status">
                                <option value="Requested" ${quote.status eq 'Requested' ? 'selected' : ''}>Requested</option>
                                <option value="Reject" ${quote.status eq 'Reject' ? 'selected' : ''}>Reject</option>
                                <option value="Generate" ${quote.status eq 'Generate' ? 'selected' : ''}>Generate</option>
                            </select>
                        </td>
                        <td><input type="text" name="note" value="${quote.note}" /></td>
                        <td><input type="submit" value="Update" /></td>
                    </form>
                    <form action="treeDetails" method="post">
                        <td>
                            <input type="hidden" name="quote_id" value="${quote.quote_id}" />
                            <input type="submit" value="View Tree Details" />
                        </td>
                    </form>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>