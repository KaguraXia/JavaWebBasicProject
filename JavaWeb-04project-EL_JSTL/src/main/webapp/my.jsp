<%--
  Created by IntelliJ IDEA.
  User: 27569
  Date: 2025/3/18
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
用户名：<%= request.getAttribute("username") %>
<br>
密码：<%= request.getAttribute("password") %>
<br>
用户名：${username}
<br>
密码：${password}
<br>
a是否为空：${empty a}
<br>
b是否为空：${empty b}
<br>
</body>
</html>
