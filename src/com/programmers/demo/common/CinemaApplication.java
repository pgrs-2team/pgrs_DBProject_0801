package com.programmers.demo.common;

import com.programmers.demo.movie.MovieManagerImpl;
import com.programmers.demo.reservation.ReservationManagerImpl;
import com.programmers.demo.user.UserManagerImpl;

public class CinemaApplication {
    public static void main(String[] args) {
        new Cinema(UserManagerImpl.getInstance(), MovieManagerImpl.getInstance(), new ReservationManagerImpl()).run();
    }
}
