<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户登录</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 400px;
            margin: 50px auto;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 { text-align: center; color: #333; }
        .error { color: red; margin: 10px 0; text-align: center; }
        .login-form { margin-top: 20px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover { background-color: #0056b3; }
        .register-link { text-align: center; margin-top: 15px; }
    </style>
</head>
<body>
<h2>用户登录</h2>

<%-- 错误提示（支持request属性或URL参数传递） --%>
<c:if test="${not empty requestScope.errorMessage || not empty param.error}">
    <div class="error">
        <c:out value="${requestScope.errorMessage}" default="${param.error}" />
    </div>
</c:if>

<form class="login-form" action="login" method="post">
    <div class="form-group">
        <label for="username">用户名：</label>
        <input type="text" id="username" name="username" required>
    </div>

    <div class="form-group">
        <label for="password">密码：</label>
        <input type="password" id="password" name="password" required>
    </div>

    <button type="submit">登录</button>
</form>

<div class="register-link">
    没有账号？<a href="register.jsp">立即注册</a>
</div>
</body>
</html>
