package com.example.servlet;

import com.example.dao.DBUtil;
import com.example.model.Bill;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/editBill")
public class EditBillServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        Bill bill = new Bill();
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT id, amount, time, purpose FROM bills WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, Integer.parseInt(id));
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        bill.setId(rs.getInt("id"));
                        bill.setAmount(rs.getDouble("amount"));
                        bill.setTime(rs.getTimestamp("time"));
                        bill.setPurpose(rs.getString("purpose"));
                    }
                }
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }

        request.setAttribute("bill", bill);
        request.getRequestDispatcher("editBill.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String purpose = request.getParameter("purpose");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String time = request.getParameter("time");

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "UPDATE bills SET amount = ?, time = ?, purpose = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setDouble(1, amount);
                stmt.setString(2, time + ":00");
                stmt.setString(3, purpose);
                stmt.setInt(4, Integer.parseInt(id));
                stmt.executeUpdate();
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/viewBills");
    }
}
