<%--
  Created by IntelliJ IDEA.
  User: 27569
  Date: 2025/3/24
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<h2>文件上传页面</h2>
<form action= "upload" method = "post" enctype="multipart/form-data">
    选择文件：<input type="file" name="file" required>
    <input type="submit" value="上传">
</form>
<p>服务器状态：
    <%= request.getAttribute("message") != null ?
            request.getAttribute("message") : "等待上传" %>
</p>
</body>
</html>
