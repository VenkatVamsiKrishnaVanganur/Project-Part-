<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Order Details</title>

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
        .Bill-button {
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
    <a class="back" onclick="goBack();" target="_self">Back</a><br><br>

    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Order Details</h2></caption>
            <tr>
                <th>Order Id</th>
                <th>Client Id</th>
                <th>Quote Id</th>
                <th>Total Amount</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Action</th>
                
            </tr>
            <c:forEach var="order" items="${ListOrders}">
                <tr style="text-align:center">
                    <td><c:out value="${order.order_id}" /></td>
                    <td><c:out value="${order.client_id}" /></td>
                    <td><c:out value="${order.quote_id}" /></td>
                    <td><c:out value="${order.total_amount}" /></td>
                    <td><c:out value="${order.start_date}" /></td>
                    <td><c:out value="${order.end_date}" /></td>
                    <td><form method="post" action="generateBill"><button type="submit" class="Bill-button">Generate Bill</button>
                     <input type="hidden" name="order_id" value="${order.order_id}" />
                     <input type="hidden" name="client_id" value="${order.client_id}" />
                     <input type="hidden" name="quote_id" value="${order.quote_id}" />
                     <input type="hidden" name="total_amount" value="${order.total_amount}" />
                     <input type="hidden" name="start_date" value="${order.start_date}" />
                     <input type="hidden" name="end_date" value="${order.end_date}" />
                     </form>
                    </td>
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