<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Tree Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            height: 100vh;
            background-color: #f4f4f4;
        }

        h1 {
            text-align: center;
            color: #002f87;
            font-size: 32px;
            padding: 20px 0;
        }

        .errormsg {
            color: #FF0000;
            margin-bottom: 10px;
        }

        .form-container {
            width: 60%;
            margin: 0 auto;
            background-color: rgba(173, 216, 230, 0.7);
            padding: 20px;
            border-radius: 10px;
        }

        .input-container {
            width: 100%;
            margin-bottom: 15px;
        }

        .user_name {
            width: calc(100% - 22px);
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 10px;
            box-sizing: border-box;
        }

        .file-input-container {
            text-align: center;
            margin-bottom: 10px;
        }

        .file-input {
            width: calc(100% - 22px);
            margin-bottom: 10px;
            display: inline-block;
        }

        .note {
            width: calc(100% - 22px);
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 10px;
            box-sizing: border-box;
        }

        .button {
            background-color: #add8e6;
            color: #000;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 18px;
            display: block;
            margin: 0 auto;
        }

        .register_button {
            background-color: #FF0000;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 18px;
            text-decoration: none;
            display: block;
            margin: 0 auto;
            width: fit-content;
        }
    </style>
</head>

<body>
    <h1>Tree Details</h1>
    <div class="form-container">
        <p class="errormsg">${error}</p>
        <form action="addTree" method="post" ">
            <div class="input-container">
                <input class="user_name" type="text" name="size" placeholder="Size" autofocus>
            </div>
            <div class="input-container">
                <input class="user_name" type="text" name="height" placeholder="Height">
            </div>
            <div class="input-container">
                <input class="user_name" type="text" name="nearHouse" placeholder="Near House">
            </div>
            <div class="input-container">
                <input class="user_name" type="text" name="location" placeholder="Location">
            </div>
            <div class="input-container file-input-container">
                <label for="image1">Add Images:</label><br>
                <input class="file-input" type="file" id="image1" name="treeImage1" accept="image/*">
                <input class="file-input" type="file" id="image2" name="treeImage2" accept="image/*">
                <input class="file-input" type="file" id="image3" name="treeImage3" accept="image/*">
            </div>
            <div class="input-container">
                <label for="note">Note:</label><br>
                <textarea class="note" id="note" type="text" name="note" rows="4" placeholder="Note" autofocus></textarea>
            </div>
            <input class="button" type="submit" value="Add" />
        </form>
        <p class="register_button"><a href="quote request.jsp" target="_self">BACK</a></p>
    </div>
</body>

</html>