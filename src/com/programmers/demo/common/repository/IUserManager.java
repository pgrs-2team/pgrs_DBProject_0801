package com.programmers.demo.common.repository;

import com.programmers.demo.common.domain.User;

public interface IUserManager {
    User login(String nickname, String password); // 로그인

    void createUser(User user); // 회원 가입

    void deleteUser(int id); // 회원 삭제
}
