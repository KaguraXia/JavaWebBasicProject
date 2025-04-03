<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
  <title>添加账单</title>
</head>
<body>
<h1>添加新账单</h1>
<form action="${pageContext.request.contextPath}/addBill" method="post">
  金额：<input type="number" step="0.01" name="amount" required><br>
  时间：<input type="datetime-local" name="time" required><br>
  用途：<input type="text" name="purpose" required><br>
  <input type="submit" value="提交">
</form>
<a href="${pageContext.request.contextPath}/viewBills">返回账单列表</a>
</body>
</html>
