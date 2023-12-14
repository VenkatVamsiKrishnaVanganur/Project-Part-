<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>View Page</title>

    <style>
        .back {
            background-color: #007BFF;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 18px;
            text-decoration: none;
            display: inline-block;
            margin-bottom: 20px;
        }

        table {
            width: 80%;
            margin: 0 auto;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #add8e6; /* Added background color for the table */
            border-radius: 10px;
        }

        table caption {
            text-align: center;
            font-size: 24px;
            padding: 10px;
            color: #000;
            border-top-left-radius: 10px;
            border-top-right-radius: 10px;
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

        .action-buttons {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .negotiate-button, .accept-button {
            background-color: #28a745;
            color: #fff;
            padding: 8px 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
            margin: 5px;
        }
    </style>
</head>
<body>

<div align="center">
    <a class="back" onclick="goBack();" target ="_self" >Back</a><br><br>

    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Quotes</h2></caption>
            <tr>
                <th>Quote Id</th>
                <th>Request Date</th>
                <th>Price</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Status</th>
                <th>Note</th>
                <th>Action</th>
            </tr>
            <c:forEach var="quote" items="${quotes}">
                <tr style="text-align:center">
                <form action="negotiation" method="post">
                    <td><c:out value="${quote.quote_id}" /></td>
                    <td><c:out value="${quote.propose_date}" /></td>
                    <td><c:out value="${quote.price}" /></td>
                    <td><c:out value="${quote.start_date}" /></td>
                    <td><c:out value="${quote.end_date}" /></td>
                    <td><c:out value="${quote.status}" /></td>
                    <input type="text" name="quote_id" value="${quote.quote_id}" hidden />
                     <input type="hidden" name="client_id" value="${quote.client_id}" />
                     
                     <input type="Date" name="start_date" value="${quote.start_date}" hidden />
                     <input type="Date" name="end_date" value="${quote.end_date}" hidden />
                     <input type="text" name="price" value="${quote.price}" hidden />
                     <td> <input type="text" value="${quote.note}" name="note" /></td>
                     <td><button type="submit" class="negotiate-button">Negotiate</button></td>
                     </form>
                     
                        <form action="acceptQuote" method="post">
                            <input type="hidden" name="quote_id" value="${quote.quote_id}" />
                            <input type="hidden" name="client_id" value="${quote.client_id}" />
                     
                     <input type="Date" name="start_date" value="${quote.start_date}" hidden />
                     <input type="Date" name="end_date" value="${quote.end_date}" hidden />
                     <input type="text" name="price" value="${quote.price}" hidden />
                            <td><button type="submit" class="accept-button">Accept</button></td>
                        </form>
                   
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<script>
    function goBack() {
        window.history.back();
    }
</script>
</body>
</html>