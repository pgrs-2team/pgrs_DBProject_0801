package com.programmers.demo.common.repository;

import com.programmers.demo.common.domain.Movie;

import java.time.LocalDate;
import java.util.List;

public interface IMovieManager {
    void addMovie(Movie movie); // 영화 등록

    void deleteMovie(Long id); // 영화 삭제

    void updateMovie(Long id, LocalDate startDate, LocalDate endDate); // 영화 수정(날짜만 가능)

    Movie getMovie(String title); //제목으로 영화 검색

    List<Movie> movieList(); //영화 전체 목록

    boolean existMovie(Long id);
}
