package com.example.servlet;

import com.example.dao.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

import java.sql.SQLException;

@WebServlet("/Register")
public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收客户端的 POST 请求，提取表单参数 username 和 password。
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 链接数据库
        try (Connection conn = DBUtil.getConnection()) {

            // 1. 检查用户名是否存在，
            // 通过 SELECT 语句检查用户名是否存在。
            // 使用预处理语句 (? 占位符)，防止 SQL 注入。
            // 如果存在 (rs.next() 为 true）,设置错误信息 msg，转发到 register.jsp
            String checkSql = "SELECT username FROM users WHERE username = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setString(1, username);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next()) {
                        req.setAttribute("msg", "用户名已存在");
                        req.getRequestDispatcher("register.jsp").forward(req, resp);
                        return;
                    }
                }
            }

            // 2. 插入新用户
            String insertSql = "INSERT INTO users (username, password) VALUES (?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setString(1, username);
                insertStmt.setString(2, password);
                insertStmt.executeUpdate();
            }

            // 注册成功，跳转到登录页
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("msg", "数据库错误，请重试");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }
}
