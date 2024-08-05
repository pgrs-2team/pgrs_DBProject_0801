package com.programmers.demo.user;

import com.programmers.demo.common.DBUtil;
import com.programmers.demo.common.domain.User;
import com.programmers.demo.common.repository.IUserManager;

import java.sql.*;

public class UserManagerImpl implements IUserManager {
    private static UserManagerImpl instance;
    Connection conn;
    ResultSet rs;

    private UserManagerImpl() {
         conn = DBUtil.getInstance().getConnection();
    }

    public static UserManagerImpl getInstance(){
        if (instance == null) {
            instance = new UserManagerImpl();
        }
        return instance;
    }


    @Override
    public User login(String nickname, String password) {
        String sql = "SELECT user_id, nickname, password, role, birth_date FROM users WHERE nickname=? AND password=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nickname);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getLong("user_id"),
                        rs.getString("nickname"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getDate("birth_date").toLocalDate()
                );
            }
        } catch (SQLException e) {
            System.out.println("user select error");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createUser(User user) {
        String sql = "INSERT INTO users (nickname, password, role, birth_date) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getNickname());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.setDate(4, Date.valueOf(user.getBirth_date()));
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("user insert error");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(Long id) {
        String sql = "DELETE FROM users WHERE user_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            int res = ps.executeUpdate();
            if (res != 1) {
                System.out.println("존재하지 않는 아이디입니다.");
            }
        } catch (SQLException e) {
            System.out.println("users delete error");
            e.printStackTrace();
        }
    }
}
