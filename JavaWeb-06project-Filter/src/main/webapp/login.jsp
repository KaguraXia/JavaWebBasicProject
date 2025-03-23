<%--
  Created by IntelliJ IDEA.
  User: 27569
  Date: 2025/3/15
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <html>
    <head>
        <title>Login</title>
    </head>
    <body>
    <h2>Login</h2>
    <%--
    标题是：login
    --%>

    <%-- 显示错误信息 --%>
    <% if (request.getAttribute("errorMessage") != null) { %>
    <p style="color: #ff0900;">
        <%= request.getAttribute("errorMessage") %>
    </p>
    <% } %>
    <%--
    <% ... %>：这是 JSP 脚本 的语法，用于嵌入 Java 代码
    request.getAttribute("errorMessage")：从 request 对象中获取名为 "errorMessage"
    != null：判断 errorMessage 是否存在（即是否不为 null）
    <p style="color: #ff0900;">...: 将错误信息显示为一个段落（<p>），并设置文字颜色为红色
    --%>

    <form action="login" method="post">

        <%--
        定义一个form表单,表单数据将通过post请求发送到“Login”中
        <form>:定义一个form表单
        action = "login"，表示提交表单时，数据会发送到"login"中
        method = "post"，表示提交表单的方式是"post"(post适合提交敏感数据，比如密码，不会显示在url中）%
        --%>
            <%-- 表单的 action 属性指向 Register Servlet --%>


        Username: <input type="text" name="username"><br>
        <%--
        <input> 是 HTML 中的一个标签，用于在网页中创建用户输入的控件
        Username:文本标签，提示用户输入用户名
        创建一个文本框，用于输入用户名
        type = "text"：表示一个文本框输入框
        name = "username": 该输入框的名称，后端可以通过这个名称获取用户输入的值
        <br>换行符
        --%>

        Password: <input type="password" name="password"><br>

        <input type="submit" value="Login">
            <%--
            type="submit": 一个提交按钮
            value=“Login‘：表示按钮上的字符Login
            --%>
    </form>
    <%--
    结束表单标签，表示表单定义的范围到此为止
    --%>

    <form action="register.jsp" method="get">
        <button type="submit">Register</button>
    </form>

    </body>
    </html>
