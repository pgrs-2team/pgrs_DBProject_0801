package com.programmers.demo.common.domain;

import java.time.LocalDate;

public class Movie {
    private Long movie_id;

    private String name;

    private String director;

    private String genre;

    private LocalDate start_date;

    private LocalDate end_date;

    private String rating;

    public Movie(Long movie_id, String name, String director, String genre, LocalDate start_date, LocalDate end_date, String rating) {
        this.movie_id = movie_id;
        this.name = name;
        this.director = director;
        this.genre = genre;
        this.start_date = start_date;
        this.end_date = end_date;
        this.rating = rating;
    }

    public Movie(String name, String director, String genre, LocalDate start_date, LocalDate end_date, String rating) {
        this.name = name;
        this.director = director;
        this.genre = genre;
        this.start_date = start_date;
        this.end_date = end_date;
        this.rating = rating;
    }

    public Long getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Long movie_id) {
        this.movie_id = movie_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movie_id=" + movie_id +
                ", name='" + name + '\'' +
                ", director='" + director + '\'' +
                ", genre='" + genre + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", rating='" + rating + '\'' +
                '}';
    }
}
