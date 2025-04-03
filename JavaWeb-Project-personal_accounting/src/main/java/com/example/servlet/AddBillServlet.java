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
import java.sql.SQLException;

@WebServlet("/addBill")
public class AddBillServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String purpose = request.getParameter("purpose");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String time = request.getParameter("time"); // 格式：yyyy-MM-dd'T'HH:mm

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO bills (username, amount, time, purpose) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setDouble(2, amount);
                stmt.setString(3, time + ":00"); // 补全秒数（如：2024-05-20T15:30 → 2024-05-20 15:30:00）
                stmt.setString(4, purpose);
                stmt.executeUpdate();
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/viewBills");
    }
}
