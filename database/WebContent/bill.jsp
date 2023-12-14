<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Bill Details</title>

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
        .pay-button, .reject-button {
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
            <caption><h2>Bill Details</h2></caption>
            <tr>
                <th>Bill Id</th>
                <th>Order Id</th>
                <th>Bill Generated Date</th>
                <th>Bill Paid Date</th>
                <th>Total Amount</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <c:forEach var="bill" items="${ListBill}">
                <tr style="text-align:center">
                    <form action="payment" method="post">
                    <td><c:out value="${bill.bill_id}" /></td>
                    <td><c:out value="${bill.order_id}" /></td>
                    <td><c:out value="${bill.generated_bill_date}" /></td>
                    <td><c:out value="${bill.bill_paid_date}" /></td>
                    <td><c:out value="${bill.total_amount}" /></td>
                    <td><c:out value="${bill.status}" /></td>
                    <td><button type="submit" class="pay-button">Pay</button></td>
                    <input type="hidden" name="bill_id" value="${bill.bill_id}" />
                    
                    </form>
                    <form action="reject" method="post">
                     <td><button type="submit" class="reject-button">Reject</button></td>
                     <input type="hidden" name="bill_id" value="${bill.bill_id}" />
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