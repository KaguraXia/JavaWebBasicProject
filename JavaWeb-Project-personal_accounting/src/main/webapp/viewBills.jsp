<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>账单管理</title>
    <style>
        table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }
        th, td { padding: 10px; border: 1px solid #ddd; text-align: left; }
        th { background-color: #f8f9fa; }
        .add-button { margin: 20px 0; display: inline-block; }
        .section { margin-bottom: 30px; }
        .amount { text-align: right; }
    </style>
</head>
<body>
<h1>我的账单</h1>
<div class="section">
    <a href="${pageContext.request.contextPath}/addBill.jsp" class="add-button">
        <button>添加账单</button>
    </a>
    <table>
        <tr>
            <th>金额</th>
            <th>时间</th>
            <th>用途</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${bills}" var="bill">
            <tr>
                <!-- 格式化金额为两位小数 -->
                <td class="amount">¥<fmt:formatNumber value="${bill.amount}" pattern="#,##0.00" /></td>
                <!-- 格式化日期时间 -->
                <td><fmt:formatDate value="${bill.time}" pattern="yyyy-MM-dd HH:mm" /></td>
                <td>${bill.purpose}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/editBill?id=${bill.id}">编辑</a>
                    <a href="${pageContext.request.contextPath}/deleteBill?id=${bill.id}"
                       onclick="return confirm('确定删除这条账单吗？')">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<div class="section">
    <h3>每日金额汇总</h3>
    <table>
        <tr>
            <th>日期</th>
            <th class="amount">总金额</th>
        </tr>
        <c:forEach items="${dailyTotals}" var="daily">
            <tr>
                <!-- 显示日期 -->
                <td><fmt:formatDate value="${daily.day}" pattern="yyyy-MM-dd" /></td>
                <!-- 显示总金额，右侧对齐 -->
                <td class="amount">¥<fmt:formatNumber value="${daily.total}" pattern="#,##0.00" /></td>
            </tr>
        </c:forEach>
    </table>
</div>

<!-- 显示错误信息 -->
<c:if test="${not empty error}">
    <div style="color: red; margin-top: 20px;">错误：${error}</div>
</c:if>

</body>
</html>
