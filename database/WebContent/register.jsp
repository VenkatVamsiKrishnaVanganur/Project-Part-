<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Registration</title>
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

        .registration-box {
            background: rgba(22, 126, 149, 0.7);
            padding: 40px;
            border-radius: 5px;
            margin: 20px auto;
            width: 600px;
            text-align: center;
        }

        table {
            width: 100%;
        }

        table th {
            text-align: right;
            padding-right: 10px;
        }

        table td {
            padding-left: 10px;
        }

        input[type="text"],
        input[type="password"],
        input[type="phone"],
        input[type="address"],
        input[type="credit card info"],
        select {
            width: calc(100% - 20px); /* Adjusted width */
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 10px;
        }

        .button-container {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .register-button {
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

        .return-link {
            display: block;
            margin-top: 10px;
            text-decoration: none;
            background-color: #FF0000;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            display: block;
            margin: 0 auto;
        }
    </style>
</head>
<body>
    <center>
        <h1>Welcome to Registration Page</h1>
        <div class="registration-box">
            <p>${errorOne }</p>
            <p>${errorTwo }</p>
            <form action="register">
                <table border="1" cellpadding="5">
                    <tr>
                        <th>Username: </th>
                        <td colspan="3">
                            <input type="text" name="email" size="30" value="example@gmail.com" onfocus="this.value=''">
                        </td>
                    </tr>
                    <tr>
                        <th>First Name: </th>
                        <td colspan="3">
                            <input type="text" name="firstName" size="30" value="FirstName" onfocus="this.value=''">
                        </td>
                    </tr>
                    <tr>
                        <th>Last Name: </th>
                        <td colspan="3">
                            <input type="text" name="lastName" size="30" value="LastName" onfocus="this.value=''">
                        </td>
                    </tr>
                    <tr>
                        <th>Password: </th>
                        <td colspan="3"> 
                            <input type="password" name="password" size="30" value="password" onfocus="this.value=''">
                        </td>
                    </tr>
                    <tr>
                        <th>Password Confirmation: </th>
                        <td colspan="3">
                            <input type="password" name="confirm" size="30" value="confirm" onfocus="this.value=''">
                        </td>
                    </tr>
                    <tr>
                        <th>Phone: </th>
                        <td colspan="3">
                            <input type="phone" name="phone" size="50" value="phone" onfocus="this.value=''">
                        </td>
                    </tr>
                    <tr>
                        <th>Address: </th>
                        <td colspan="3">
                            <input type="address" name="address" size="50" value="address" onfocus="this.value=''">
                        </td>
                    </tr>
                    <tr>
                        <th>Credit card info: </th>
                        <td colspan="3">
                            <input type="credit card info" name="credit_card_info" size="50" value="credit_card_info" onfocus="this.value=''">
                        </td>
                    </tr>
                    <tr>
                        <th>Role: </th>
                        <td colspan="3">
                            <select id="role" name="role">
                                <option value="root">Root</option>
                                <option value="clients">Client</option>
                                <option value="david_smith">David Smith</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="5">
                            <input type="submit" value="Register" class="register-button">
                        </td>
                    </tr>
                </table>
            </form>
            <a href="login.jsp" target="_self" class="return-link">Return to Login Page</a>
        </div>
    </center>
</body>
</html>