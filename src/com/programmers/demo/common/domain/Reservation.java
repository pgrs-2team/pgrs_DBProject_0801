package com.programmers.demo.common.domain;

import java.time.LocalDateTime;

public class Reservation {
    private Long reservation_id;

    private LocalDateTime reservation_date;

    private int head_count;

    private Long user_id;

    private Long movie_id;

    public Reservation() {}

    public Reservation(Long reservation_id, LocalDateTime reservation_date, int head_count, Long user_id, Long movie_id) {
        this.reservation_id = reservation_id;
        this.reservation_date = reservation_date;
        this.head_count = head_count;
        this.user_id = user_id;
        this.movie_id = movie_id;
    }

    public Reservation(LocalDateTime reservation_date, int head_count, Long user_id, Long movie_id) {
        this.reservation_date = reservation_date;
        this.head_count = head_count;
        this.user_id = user_id;
        this.movie_id = movie_id;
    }

    public Long getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(Long reservation_id) {
        this.reservation_id = reservation_id;
    }

    public LocalDateTime getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(LocalDateTime reservation_date) {
        this.reservation_date = reservation_date;
    }

    public int getHead_count() {
        return head_count;
    }

    public void setHead_count(int head_count) {
        this.head_count = head_count;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Long movie_id) {
        this.movie_id = movie_id;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservation_id=" + reservation_id +
                ", reservation_date=" + reservation_date +
                ", head_count=" + head_count +
                ", user_id=" + user_id +
                ", movie_id=" + movie_id +
                '}';
    }
}
