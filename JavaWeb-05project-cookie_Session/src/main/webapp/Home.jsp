<%--
  Created by IntelliJ IDEA.
  User: 27569
  Date: 2025/3/20
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%--声明--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>商品列表</title>
</head>
<body>
<h1>商品列表</h1>
<c:forEach items="${books}" var = "book">
    <div>
        <h3>${book.name} - ${book.price}元</h3>
        <form action="books?action=add" method="post">
            <input type="hidden" name="id" value="${book.id}">
            <input type="submit" value="加入购物车">
        </form>
    </div>
</c:forEach>
<a href="${pageContext.request.contextPath}/cart">查看购物车</a>
</body>
</html>
