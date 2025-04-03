<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>实时校验用户名</title>
    <style>
        .success { color: green; }
        .error { color: red; }
    </style>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>
<form>
    <label>用户名：</label>
    <input type="text" id="username" name="username">
    <span id="usernameTip"></span>
</form>

<script>
    $(document).ready(function() {
        // 当用户名输入框失去焦点时触发
        $("#username").blur(function() {
            var username = $(this).val();

            // 发送AJAX POST请求（参考网页4/8/9）
            $.ajax({
                url: "CheckUsernameServlet",
                type: "POST",
                data: { username: username },  // 发送的数据
                dataType: "json",               // 预期返回JSON格式
                success: function(response) {
                    if (response.exists) {
                        $("#usernameTip").html("❌ 用户名已存在").addClass("error");
                    } else {
                        $("#usernameTip").html("✅ 用户名可用").addClass("success");
                    }
                },
                error: function() {
                    alert("请求失败，请重试！");
                }
            });
        });
    });
</script>
</body>
</html>