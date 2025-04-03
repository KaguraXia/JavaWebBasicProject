package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//DBUtil工具类建立数据库连接
public class DBUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/simple_auth"; // 数据库名 simple_auth
    private static final String DB_USER = "root";  // 数据库用户名 root
    private static final String DB_PASSWORD = "huge12138"; // 数据库密码
    static {
        try {
            // 显式加载MySQL驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("加载数据库驱动失败");
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
