package com.programmers.demo.common.domain;

import java.time.LocalDate;

public class User {
    private Long user_id;

    private String nickname;

    private String password;

    private String role;

    private LocalDate birth_date;

    public User(Long user_id, String nickname, String password, String role, LocalDate birth_date) {
        this.user_id = user_id;
        this.nickname = nickname;
        this.password = password;
        this.role = role;
        this.birth_date = birth_date;
    }

    public User(String nickname, String password, String role, LocalDate birth_date) {
        this.nickname = nickname;
        this.password = password;
        this.role = role;
        this.birth_date = birth_date;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", birth_date=" + birth_date +
                '}';
    }
}
