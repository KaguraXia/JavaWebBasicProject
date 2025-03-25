import com.example.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/products")
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Product> products = new ArrayList<>();

        try {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 连接数据库（请替换为您的数据库信息）
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/shop", "root", "huge12138"
            );
            statement = connection.createStatement();

            // 执行SQL查询
            String sql = "SELECT ID, Name, Description, Price FROM products";
            resultSet = statement.executeQuery(sql);

            // 将查询结果转换为Product对象列表
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");
                double price = resultSet.getDouble("Price");
                products.add(new Product(id, name, description, price));
            }

            // 将产品列表存入request属性
            req.setAttribute("products", products);

            // 转发到JSP页面
            req.getRequestDispatcher("/products.jsp").forward(req, resp);

        } catch (ClassNotFoundException e) {
            throw new ServletException("数据库驱动未找到", e);
        } catch (SQLException e) {
            throw new ServletException("数据库错误", e);
        } finally {
            // 关闭资源
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new ServletException("关闭资源时出错", e);
            }
        }
    }
}