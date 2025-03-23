<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%--声明--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>购物车</title>
</head>
<body>
<h1>购物车</h1>
<c:forEach items="${cart}" var="book">
    <div>
        <h3>${book.name} - ${book.price}元</h3>
    </div>
</c:forEach>
</body>
</html>