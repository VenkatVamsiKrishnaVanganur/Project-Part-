<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login to Database</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-position: center;
            background-size: cover;
            background-repeat: no-repeat;
            color: #fff;
            height: 100vh;
        }

        h1 {
            text-align: center;
            color: #fff;
            font-size: 32px;
            padding: 20px 0;
        }

        center {
            text-align: center;
        }

        .login-box {
            background: rgba(22, 126, 149, 0.7);
            padding: 40px;
            border-radius: 5px;
            margin: 20px auto;
            width: 400px;
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
        input[type="password"] {
            width: 100%;
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

        .login-button {
            background-color: #008000; /* Green color for the login button */
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            margin-top: 10px;
            cursor: pointer;
            font-size: 18px;
        }
   
        .register-button {
            background-color: #FF0000; /* Add a background color to the "Register Here" button */
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            margin-top: 10px;
            cursor: pointer;
            font-size: 18px;
            text-decoration: none; /* Remove underline */
            display: inline-block; /* Make the "Register Here" button a block element */
        }
    </style>
</head>
<body>
    <center>
        <h1 style="color: black;">Welcome to Login Page</h1>
        <div class="login-box">
            <p>${loginFailedStr}</p>
            <form action="login" method="post">
                <table>
                    <tr>
                        <th>Username:</th>
                        <td>
                            <input type="text" name="email" autofocus>
                        </td>
                    </tr>
                    <tr>
                        <th>Password:</th>
                        <td>
                            <input type="password" name="password">
                        </td>
                    </tr>
                </table>
                <input type="submit" value="Login" class="login-button" />
            </form>
            <div class="button-container">
                <a href="register.jsp" target="_self" class="register-button">Register Here</a>
            </div>
        </div>
    </center>
</body>
</html>