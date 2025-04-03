<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>编辑账单</title>
</head>
<body>
<h1>编辑账单</h1>
<form action="${pageContext.request.contextPath}/editBill" method="post">
    <input type="hidden" name="id" value="${bill.id}">
    金额：<input type="number" step="0.01" name="amount" value="${bill.amount}" required><br>
    时间：<input type="datetime-local" name="time"
                value="<fmt:formatDate value="${bill.time}" pattern="yyyy-MM-dd'T'HH:mm" />" required><br>
    用途：<input type="text" name="purpose" value="${bill.purpose}" required><br>
    <input type="submit" value="保存修改">
</form>
<a href="${pageContext.request.contextPath}/viewBills">返回账单列表</a>
</body>
</html>
