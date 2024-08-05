package com.programmers.demo.reservation;

import com.programmers.demo.common.domain.Reservation;
import com.programmers.demo.common.repository.IReservationManager;
import com.programmers.demo.common.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationManagerImpl implements IReservationManager {

    @Override
    public void createReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (reservation_date, head_count, user_id, movie_id) VALUES (?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtil.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setTimestamp(1, Timestamp.valueOf(reservation.getReservation_date()));
            ps.setInt(2, reservation.getHead_count());
            ps.setLong(3, reservation.getUser_id());
            ps.setLong(4, reservation.getMovie_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Reservation getReservation(String userName) {
        String sql = "SELECT r.* FROM reservations r JOIN users u ON r.user_id = u.user_id WHERE u.nickname = ?";
        Reservation reservation = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            if (rs.next()) {
                reservation = new Reservation(
                        rs.getLong("reservation_id"),
                        rs.getTimestamp("reservation_date").toLocalDateTime(),
                        rs.getInt("head_count"),
                        rs.getLong("user_id"),
                        rs.getLong("movie_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return reservation;
    }

    @Override
    public List<Reservation> reservationList() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                reservations.add(new Reservation(
                        rs.getLong("reservation_id"),
                        rs.getTimestamp("reservation_date").toLocalDateTime(),
                        rs.getInt("head_count"),
                        rs.getLong("user_id"),
                        rs.getLong("movie_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return reservations;
    }

    @Override
    public void deleteReservation(Long id) {
        String sql = "DELETE FROM reservations WHERE reservation_id = ?";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtil.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void updateReservation(Long id, int headCount) {
        String sql = "UPDATE reservations SET head_count = ? WHERE reservation_id = ?";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtil.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, headCount);
            ps.setLong(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
