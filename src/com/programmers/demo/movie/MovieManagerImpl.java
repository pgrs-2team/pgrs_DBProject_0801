package com.programmers.demo.movie;

import com.programmers.demo.common.DBUtil;
import com.programmers.demo.common.domain.Movie;
import com.programmers.demo.common.repository.IMovieManager;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieManagerImpl implements IMovieManager {
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


    @Override
    public void addMovie(Movie movie) {
        String SQL = "INSERT INTO movies (name, director, genre, start_date, end_date, rating) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setString(1, movie.getName());
            ps.setString(2, movie.getDirector());
            ps.setString(3, movie.getGenre());
            ps.setDate(4, Date.valueOf(movie.getStart_date()));
            ps.setDate(5, Date.valueOf(movie.getEnd_date()));
            ps.setString(6, movie.getRating());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("insert error");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMovie(Long id) {
        String SQL = "DELETE FROM movies WHERE movie_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("delete error");
            e.printStackTrace();
        }
    }

    @Override
    public void updateMovie(Long id, LocalDate startDate, LocalDate endDate) {
        String SQL = "UPDATE movies SET start_date = ?, end_date = ? WHERE movie_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setDate(1, Date.valueOf(startDate));
            ps.setDate(2, Date.valueOf(endDate));
            ps.setLong(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("update error");
            e.printStackTrace();
        }
    }

    @Override
    public Movie getMovie(String name) {
        Movie movie = null;
        String SQL = "SELECT * FROM movies WHERE name = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                movie = makeMovieSelect(rs);
            }
        } catch (SQLException e) {
            System.out.println("getMovie error");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources");
                e.printStackTrace();
            }
        }
        return movie;
    }

    @Override
    public List<Movie> movieList() {
        List<Movie> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT * FROM movies";
            ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(makeMovieSelect(rs));
            }
        } catch (SQLException e) {
            System.out.println("getMovieList error");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources");
                e.printStackTrace();
            }
        }
        return list;
    }
        private Movie makeMovieSelect (ResultSet rs) throws SQLException {
            Movie movie = new Movie();
            movie.setMovie_id(rs.getLong("movie_id"));
            movie.setName(rs.getString("name"));
            movie.setDirector(rs.getString("director"));
            movie.setGenre(rs.getString("genre"));
            movie.setStart_date(rs.getDate("start_date").toLocalDate());
            movie.setEnd_date(rs.getDate("end_date").toLocalDate());
            movie.setRating(rs.getString("rating"));
            return movie;
        }
}

