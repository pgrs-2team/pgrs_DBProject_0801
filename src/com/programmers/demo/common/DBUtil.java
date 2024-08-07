package com.programmers.demo.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/programmers";
    private static final String USER = "";
    private static final String PASSWORD = "";
    private static DBUtil instance;
    private Connection connection;

    private DBUtil() {
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("데이터베이스 연결 실패", e);
        }
    }

    public static DBUtil getInstance() {
        if (instance == null) instance = new DBUtil();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) {
        DBUtil db = DBUtil.getInstance();
    }
}


