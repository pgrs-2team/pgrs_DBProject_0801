package com.programmers.demo.common.repository;

import com.programmers.demo.common.domain.Reservation;

import java.util.List;

public interface IReservationManager {
    void createReservation (Reservation reservation); //예약하기

    Reservation getReservation (String userName); // 이름으로 예약 검색

    List<Reservation> reservationList(); // 예약 전체 목록

    void deleteReservation(Long id); // 예약 삭제하기

    void updateReservation(Long id, int headCount); // 예약 인원수 수정
}
