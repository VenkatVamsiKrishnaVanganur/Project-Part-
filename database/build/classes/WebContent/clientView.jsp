<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Client View</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #fff;
        color: #000;
        height: 100vh;
    }

    h1 {
        text-align: center;
        color: #000;
        font-size: 32px;
    }

    center {
        text-align: center;
    }

    .button-container {
        display: flex;
        align-items: center;
        justify-content: center;
        flex-direction: column;
        margin-top: 10px;
    }

    .request-button, .viewQuotes-button, .logout-button, .orders-button, .bills-button{
        text-decoration: none;
        color: #fff;
        padding: 10px 40px;
        border: none;
        border-radius: 5px;
        margin: 10px;
        display: inline-block;
        font-size: 15px;
    }

    .request-button {
        background-color: #0033FF;
    }

    .viewQuotes-button {
        background-color: #0033FF;
    }
    
    .bills-button {
        background-color: #0033FF;
    }
    .logout-button {
        background-color: #FF0000;
    }
</style>
</head>
<body>
    <center>
        <div class="welcome-box">
            <h1>Welcome to Client View</h1>
        </div>
        <div class="button-container">
            <a class="viewQuotes-button" href="Quotes" target="_self">View Quotes</a>
            <a class="request-button" href="createQuote" target="_self">Request Quote</a>
            <a class="bills-button" href="bill" target="_self">Bills</a>
            <a class="logout-button" href="login.jsp" target="_self">Logout</a>
        </div>
    </center>
</body>
</html>