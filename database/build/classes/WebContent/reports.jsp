<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

 <meta charset="ISO-8859-1">
    <title>View Page</title>
    <style>
        body {
            background: #F0F8FF;
        }

        .back {
            position: absolute;
            left: 1300px;
            top: 50px;
        }

        * {
            font-family: sans-serif; /* Change your font family */
        }

        .content-table {
            border-collapse: collapse;
            margin: 25px 0;
            font-size: 0.9em;
            min-width: 400px;
            border-radius: 5px 5px 0 0;
            overflow: hidden;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
        }

        .content-table thead tr {
            background-color: #007BFF; /* Updated background color */
            color: #ffffff;
            text-align: left;
            font-weight: bold;
        }

        .content-table th, .content-table td {
            padding: 12px 15px;
        }

        .content-table tbody tr {
            border-bottom: 1px solid #dddddd;
        }

        .content-table tbody tr:nth-of-type(even) {
            background-color: #f3f3f3;
        }

        .content-table tbody tr:last-of-type {
            border-bottom: 2px solid #007BFF; /* Updated border color */
        }

        .content-table tbody tr.active-row {
            font-weight: bold;
            color: #007BFF; /* Updated text color */
        }
    </style>
</head>


<body>

    <div align="center">
        <a class="back" href="#" onclick="goBack();" target="_self">Back</a><br><br>
        <p style="color:red">${message}</p>

        <div align="center">
            <!-- Big Clients Table -->
            <table border="1" cellpadding="6" class="content-table">
                <caption><h2>Big Clients</h2></caption>
                <thead>
                    <tr>
                        <th>Client Id</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email </th>
                        <th>Phone Number</th>
                        <th>Tree Count</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="client" items="${bigClients}">
                        <tr style="text-align:center">
                            <td><c:out value="${client.client_id}" /></td>
                            <td><c:out value="${client.firstName}" /></td>
                            <td><c:out value="${client.lastName}" /></td>
                            <td><c:out value="${client.email}" /></td>
                            <td><c:out value="${client.phone}" /></td>
                            <td><c:out value="${client.credit_card_info}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Repeat similar blocks for other tables -->

        <div align="center">
            <table border="1" cellpadding="6" class="content-table">
                <caption><h2>Easy Clients</h2></caption>
                <thead>
                    <tr>
                        <th>Client Id</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email </th>
                        <th>Phone Number</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="client" items="${easyClients}">
                        <tr style="text-align:center">
                            <td><c:out value="${client.client_id}" /></td>
                            <td><c:out value="${client.firstName}" /></td>
                            <td><c:out value="${client.lastName}" /></td>
                            <td><c:out value="${client.email}" /></td>
                            <td><c:out value="${client.phone}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div align="center">
            <table border="1" cellpadding="6" class="content-table">
                <caption><h2>One Tree Quotes</h2></caption>
                <thead>
                    <tr>
                        <th>Quote Id</th>
                        <th>Request Date</th>
                        <th>Price</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Status</th>
                        <th>Note</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="quote" items="${oneTreeQuote}">
                        <tr style="text-align:center">
                            <td><c:out value="${quote.quote_id}" /></td>
                            <td><c:out value="${quote.propose_date}" /></td>
                            <td><c:out value="${quote.price}" /></td>
                            <td><c:out value="${quote.start_date}" /></td>
                            <td><c:out value="${quote.end_date}" /></td>
                            <td><c:out value="${quote.status}" /></td>
                            <td><c:out value="${quote.note}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Repeat similar blocks for other tables -->

    </div>

    <div align="center">
        <table border="1" cellpadding="6" class="content-table">
            <caption><h2>Prospective Clients</h2></caption>
            <thead>
                <tr>
                    <th>Client Id</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email </th>
                    <th>Phone Number</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="client" items="${prospectiveClients}">
                    <tr style="text-align:center">
                        <td><c:out value="${client.client_id}" /></td>
                        <td><c:out value="${client.firstName}" /></td>
                        <td><c:out value="${client.lastName}" /></td>
                        <td><c:out value="${client.email}" /></td>
                        <td><c:out value="${client.phone}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Repeat similar blocks for other tables -->

    <div align="center">
        <table border="1" cellpadding="6" class="content-table">
            <caption><h2>Overdue Bills</h2></caption>
            <thead>
                <tr>
                    <th>Bill Id</th>
                    <th>Order Id</th>
                    <th>Total Amount</th>
                    <th>Payment Status</th>
                    <th>Bill Generated Date</th>
                    <th>Bill Paid Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="bill" items="${overDueBills}">
                    <tr style="text-align:center">
                        <td><c:out value="${bill.bill_id}" /></td>
                        <td><c:out value="${bill.order_id}" /></td>
                        <td><c:out value="${bill.total_amount}" /></td>
                        <td><c:out value="${bill.status}" /></td>
                        <td><c:out value="${bill.generated_bill_date}" /></td>
                        <td><c:out value="${bill.bill_paid_date}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Repeat similar blocks for other tables -->

    <div align="center">
        <table border="1" cellpadding="6" class="content-table">
            <caption><h2>Bad Clients</h2></caption>
            <thead>
                <tr>
                    <th>Client Id</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email </th>
                    <th>Phone Number</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="client" items="${badClients}">
                    <tr style="text-align:center">
                        <td><c:out value="${client.client_id}" /></td>
                        <td><c:out value="${client.firstName}" /></td>
                        <td><c:out value="${client.lastName}" /></td>
                        <td><c:out value="${client.email}" /></td>
                        <td><c:out value="${client.phone}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>


    <div align="center">
        <table border="1" cellpadding="6" class="content-table">
            <caption><h2>Good Clients</h2></caption>
            <thead>
                <tr>
                    <th>Client Id</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email </th>
                    <th>Phone Number</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="client" items="${goodClients}">
                    <tr style="text-align:center">
                        <td><c:out value="${client.client_id}" /></td>
                        <td><c:out value="${client.firstName}" /></td>
                        <td><c:out value="${client.lastName}" /></td>
                        <td><c:out value="${client.email}" /></td>
                        <td><c:out value="${client.phone}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>


    <div align="center">
        <table border="1" cellpadding="6" class="content-table">
            <caption><h2>Highest Trees</h2></caption>
            <thead>
                <tr>
                    <th>Tree Id</th>
                    <th>Size</th>
                    <th>Height</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="tree" items="${highestTrees}">
                    <tr style="text-align:center">
                        <td><c:out value="${tree.tree_id}" /></td>
                        <td><c:out value="${tree.size}" /></td>
                        <td><c:out value="${tree.height}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <div align="center">
        <table border="1" cellpadding="6" class="content-table">
            <caption><h2>Statistics</h2></caption>
            <thead>
                <tr>
                    <th>Client Id</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>total trees </th>
                    <th>Total Due Amount</th>
                    <th>Total paid Amount</th>
                    <th>Work Done Date</th>   
                    
                </tr>
            </thead>
            <tbody>
                <c:forEach var="client" items="${Statistics}">
                    <tr style="text-align:center">
                        <td><c:out value="${client.client_id}" /></td>
                        <td><c:out value="${client.firstName}" /></td>
                        <td><c:out value="${client.lastName}" /></td>
                        <td><c:out value="${client.address}" /></td>
                        <td><c:out value="${client.email}" /></td>
                        <td><c:out value="${client.phone}" /></td>
                        <td><c:out value="${client.credit_card_info}" /></td>  
                        
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    

    <!-- JavaScript function -->
    <script>
        function goBack() {
            window.history.back();
        }
    </script>

</body>

</html>
