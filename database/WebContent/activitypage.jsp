<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Activity Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-image: url('https://drive.google.com/file/d/1JLi461qXGEAIy3p4tK1u8R2flK5SgwJ_/view?usp=sharing');
            background-position: center;
            background-size: cover;
            background-repeat: no-repeat;
            color: #fff;
            height: 100vh;
        }

        h1 {
            text-align: center;
        }

        center {
            text-align: center;
        }

        .welcome-box {
            background: rgba(22, 126, 149, 0.7);
            padding: 20px;
            border-radius: 5px;
        }

        .button-container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            margin-top: 20px;
        }

        .logout-button {
            background-color: #000;
            color: #fff;
            text-decoration: none;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            margin-bottom: 20px;
        }

        button.request-button {
            text-decoration: none;
            background-color: #0077FF;
            color: #fff; /* Changed text color to white */
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            font-size: 18px;
        }
    </style>
</head>

<body>
    <center>
        <div class="welcome-box">
            <h1>Welcome! You have been successfully logged in</h1>
        </div>
        <div class="button-container">
            <a class="logout-button" href="login.jsp" target="_self">Logout</a>
            <button class="request-button"><a href="quote request.jsp" target="_self" style="color: white;">Request Quote</a></button>
        </div>
    </center>
</body>
</html>