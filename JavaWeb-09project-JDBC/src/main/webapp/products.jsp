<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Product" %>
<html>
<head>
    <title>产品列表</title>
</head>
<body>
<h1>产品列表</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>名称</th>
        <th>描述</th>
        <th>价格</th>
    </tr>
    <%
        List<Product> products = (List<Product>) request.getAttribute("products");
        if (products != null) {
            for (Product product : products) {
    %>
    <tr>
        <td><%= product.getId() %></td>
        <td><%= product.getName() %></td>
        <td><%= product.getDescription() %></td>
        <td><%= product.getPrice() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="4">没有产品数据</td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>