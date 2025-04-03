import java.sql.*;

public class JdbcUtils {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/my_app_db";
    private static final String USER = "root";
    private static final String PASSWORD = "huge12138";
    public static ResultSet executeQuery(String query) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        return statement.executeQuery(query); // 注意：实际项目中需处理资源释放
    }
}