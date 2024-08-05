package com.programmers.demo.common.exception;

import java.io.PrintWriter;

public enum ExceptionCode {

    INPUT_ERROR("잘못된 입력 값 입니다."),
    DATE_ERROR("올바르지 않은 날짜 값 입니다."),
    LOGIN_ERROR("없는 닉네임 혹은 비밀번호 입니다.");



    private final String message;

    ExceptionCode(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ExceptionCode{\'" + message + '\'' + '}';
    }
}
