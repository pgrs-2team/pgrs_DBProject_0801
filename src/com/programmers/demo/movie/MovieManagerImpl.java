package com.programmers.demo.movie;

import java.sql.Connection;

public class MovieManager {
    private static MovieManagerImpl instance;
    private Connection conn;

    private MovieManagerImpl() {
        conn = DBUtil.getInstance().getConnection();
    }

    public static MovieManagerImpl getInstance() {
        if (instance == null) {
            instance = new MovieManagerImpl();
        }
        return instance;
    }
}
