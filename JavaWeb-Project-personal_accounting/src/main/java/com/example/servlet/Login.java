package com.example.servlet;

import com.example.dao.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 映射URL地址为 /login
@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");// 账号
        String password = req.getParameter("password");// 密码

        //获取数据库连接：使用DBUtil工具类建立数据库连接，try-with-resources语法确保连接自动关闭。
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT password FROM users WHERE username = ?";

            // 创建PreparedStatement：定义SQL查询，使用占位符?防止SQL注入，并创建预处理语句对象。
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                // 设置参数：将用户输入的username替换SQL中的第一个占位符，避免恶意输入破坏查询结构。
                stmt.setString(1, username);

                // 执行查询：执行SQL查询并获取结果集，try-with-resources确保结果集自动关闭。
                try (ResultSet rs = stmt.executeQuery()) {

                    // 处理结果集：检查是否存在匹配的用户记录，rs.next()移动光标到第一行数据
                    if (rs.next()) {

                        // 提取数据库密码：从结果集中获取该用户在数据库存储的密码（字段名为password）
                        String dbPassword = rs.getString("password");

                        // 简单比较
                        if (password.equals(dbPassword)) {

                            // 创建用户会话：若密码正确，获取或创建会话对象，并将用户名存入会话属性，后续请求通过会话验证用 户身份。
                            HttpSession session = req.getSession();
                            session.setAttribute("username", username);

                            // 跳转到查看页面
                            resp.sendRedirect(req.getContextPath() + "/viewBills");
                            return;
                        }
                    }
                }
            }
            // 用户名密码错误登录失败
            req.setAttribute("errorMessage", "用户名或密码错误");
            req.getRequestDispatcher("login.jsp").forward(req, resp);

            // 数据库异常登录失败
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "数据库错误，请重试");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}