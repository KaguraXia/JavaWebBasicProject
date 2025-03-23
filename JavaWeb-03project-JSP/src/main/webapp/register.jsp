<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2025/3/2
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 50px;
        }
        .register-container {
            width: 300px;
            margin: auto;
            background: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .register-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .register-container input[type="text"],
        .register-container input[type="password"],
        .register-container input[type="captcha"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
        }
        .register-container input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #5fbae9;
            border: none;
            color: white;
            cursor: pointer;
        }
        .register-container input[type="submit"]:hover {
            background-color: #4a9ad4;
        }
        .register-container label {
            margin-left: 5px;
        }
        .captcha-container {
            position: relative;
            display: inline-block;
        }
        .captcha-container img {
            cursor: pointer;
        }
        .captcha-container input[type="text"] {
            width: calc(100% - 20px); /* Adjust based on the width of the captcha image */
        }
    </style>
</head>
<body>
<div class="register-container">
    <h2>注册</h2>
    <span style="color: red">${msg}</span>
    <form action="${pageContext.request.contextPath}/Register" method="post">
        <label for="username">用户名:</label>
        <input type="text" id="username" name="username" required>

        <label for="password">密码:</label>
        <input type="password" id="password" name="password" required>

        <div class="captcha-container">
            <label for="captcha">验证码:</label>
            <img src="${pageContext.request.contextPath}/vcode" style="width: 50px;height: 30px" alt="Captcha" onclick="this.src='${pageContext.request.contextPath}/vcode?' + new Date().getTime()"> <!-- Refresh captcha image when clicked -->
            <input type="text" id="captcha" name="vcode" required>
        </div>

        <input type="submit" value="注册">
    </form>
</div>
</body>
</html>

