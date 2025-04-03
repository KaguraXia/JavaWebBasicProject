package com.example.servlet;

import com.example.dao.DBUtil;
import com.example.model.Bill;
import com.example.model.DailyTotal;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/viewBills")
public class ViewBillsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        List<Bill> bills = new ArrayList<>();
        List<DailyTotal> dailyTotals = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection()) {
            // ---查询原始账单数据---
            String billsSql = "SELECT id, amount, time, purpose FROM bills " +
                    "WHERE username = ? ORDER BY time DESC";
            try (PreparedStatement stmt = conn.prepareStatement(billsSql)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Bill bill = new Bill();
                    bill.setId(rs.getInt("id"));
                    bill.setAmount(rs.getDouble("amount"));
                    bill.setTime(rs.getTimestamp("time"));
                    bill.setPurpose(rs.getString("purpose"));
                    bills.add(bill);
                }
            }

            // ---查询每日汇总数据---
            String dailySql = "SELECT DATE(time) as day, SUM(amount) as total " +
                    "FROM bills WHERE username = ? GROUP BY DATE(time) " +
                    "ORDER BY day DESC";
            try (PreparedStatement stmt = conn.prepareStatement(dailySql)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Date day = rs.getDate("day");
                    double total = rs.getDouble("total");
                    dailyTotals.add(new DailyTotal(day, total));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "获取数据失败");
        }

        // 传递数据到JSP
        request.setAttribute("bills", bills);
        request.setAttribute("dailyTotals", dailyTotals);
        request.getRequestDispatcher("viewBills.jsp").forward(request, response);
    }
}
