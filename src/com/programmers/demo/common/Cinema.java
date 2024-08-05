package com.programmers.demo.common;

import com.programmers.demo.common.domain.Movie;
import com.programmers.demo.common.domain.Reservation;
import com.programmers.demo.common.domain.User;
import com.programmers.demo.common.exception.Exception;
import com.programmers.demo.common.exception.ExceptionCode;
import com.programmers.demo.common.repository.IMovieManager;
import com.programmers.demo.common.repository.IReservationManager;
import com.programmers.demo.common.repository.IUserManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Cinema {
    private final Scanner scanner;
    private final IUserManager userManager;
    private final IMovieManager movieManager;
    private final IReservationManager reservationManager;

    public Cinema(IUserManager userManager, IMovieManager movieManager, IReservationManager reservationManager){
        this.scanner = new Scanner(System.in);
        this.userManager = userManager;
        this.movieManager = movieManager;
        this.reservationManager = reservationManager;
    }

    public void run(){
        showMain();
        User user = mainChoice();
        if(user == null) return;

        if(user.getRole().equals("manager")){
            showManageMovieMenu();
            managerMovieChoice();

        } else if(user.getRole().equals("user")){
            showViewMovieMenu();
            viewMovieChoice();

            showReservationMenu();
            reservationChoice();
        }

    }

    private void showMain(){
        System.out.println("------------ 메인 화면 ------------");
        System.out.println("1. 회원 가입");
        System.out.println("2. 로그인");
        System.out.println("3. 회원 삭제");
        System.out.println("-1. 프로그램 종료");
    }

    private int getUserInput(){
        System.out.print("숫자를 입력해 주세요 : ");
        return Integer.parseInt(scanner.nextLine());
    }

    private User mainChoice(){
        User user = null;
        int choice;

        do{
            choice = getUserInput();

            switch (choice){
                case 1:
                    register(); // 회원 가입
                    run(); // 다시 실행
                    break;
                case 2:
                    user = login(); // 사용자 혹은 관리자 로그인
                    choice = -1;
                    break;

                case 3:
                    delete(); // 회원 탈퇴
                    run(); // 다시 실행
                    break;

                case -1: // 종료
                    System.out.println("종료합니다.");
                    break;

                default: // 그 외의 입력 값
                    Exception.exception(ExceptionCode.INPUT_ERROR);
            }
        } while (choice != -1);

        return user;
    }

    private void register(){
        try {
            System.out.println("------------ 회원 가입 ------------");

            System.out.print("닉네임 : ");
            String nickname = scanner.nextLine();

            System.out.print("비밀번호 : ");
            String password = scanner.nextLine();

            System.out.print("권한 : ");
            String role = scanner.nextLine();

            System.out.print("생년월일 (yyyy-mm-dd) : ");
            LocalDate birthDate = LocalDate.parse(scanner.nextLine());

            // 회원 가입
            userManager.createUser(new User(nickname, password, role, birthDate));
        } catch (DateTimeParseException ex){ // 날짜 형식 예외 처리
            Exception.exception(ExceptionCode.DATE_ERROR);
            register();
        }

    }

    private User login(){
        User user = null;

        while(user == null) {
            System.out.println("------------ 로그인 ------------");

            System.out.print("닉네임 : ");
            String nickname = scanner.nextLine();

            System.out.print("비밀번호 : ");
            String password = scanner.nextLine();

            user = userManager.login(nickname, password);
            if(user == null) Exception.exception(ExceptionCode.LOGIN_ERROR);
        }

        return user;
    }

    private void delete(){
        System.out.println("삭제할 id : ");
        Long id = Long.parseLong(scanner.nextLine());

        userManager.deleteUser(id);
    }

    private void showManageMovieMenu(){
        System.out.println("------------ 영화 관리 프로그램 ------------");
        System.out.println("1. 영화 추가");
        System.out.println("2. 전체 영화 조회");
        System.out.println("3. 제목으로 영화 조회");
        System.out.println("4. 영화 수정");
        System.out.println("5. 영화 삭제");
        System.out.println("-1. 프로그램 종료");
    }

    private void managerMovieChoice() {
        int choice;
       do{
           choice = getUserInput();
            switch (choice) {
                case 1: {
                    createMovie();
                    break;
                }

                case 2: {
                    viewAllMovie();
                    break;
                }

                case 3: {
                    searchMovie();
                    break;
                }

                case 4: {
                    updateMovie();
                    break;
                }

                case 5: {
                    deleteMovie();
                    break;
                }

                case -1: {
                    System.out.println("프로그램이 종료됩니다.");
                    break;
                }

                default: {
                    Exception.exception(ExceptionCode.INPUT_ERROR);
                }
            }

        } while (choice != -1);

    }

    private void createMovie(){
        System.out.print("영화 제목 : ");
        String title = scanner.nextLine();

        System.out.print("영화 감독 : ");
        String director = scanner.nextLine();

        System.out.print("영화 장르 : ");
        String genre = scanner.nextLine();

        System.out.print("영화 상영 시작일 : (ex : 2024-07-31) ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());

        System.out.print("영화 상영 종료일 (ex : 2024-08-31) : ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        System.out.print("심의 등급 : ");
        String rating = scanner.nextLine();

        // 영화 추가
        movieManager.addMovie(new Movie(title, director, genre, startDate, endDate, rating));

    }

    private void viewAllMovie(){
        // 영화 조회
        List<Movie> movieList = movieManager.movieList();
        movieList.forEach(System.out::println);
    }

    private void searchMovie(){
        // 제목으로 영화 조회
        System.out.print("검색 하고 싶은 영화 제목 : ");
        String title = scanner.nextLine();
        System.out.println(movieManager.getMovie(title));
    }

    private void updateMovie(){
        Long movieId = null;
        // 영화 수정
        while (true) {
            System.out.print("수정 하고 싶은 영화 id : ");
            try {
                movieId = Long.parseLong(scanner.nextLine());
                if (movieManager.existMovie(movieId)) {
                    break;
                } else {
                    System.out.println("잘못된 영화 id입니다. 다시 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }

        System.out.print("수정된 영화 상영일 : ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.print("수정된 영화 상영 종료일 : ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        movieManager.updateMovie(movieId, startDate, endDate);
        }

    private void deleteMovie(){
        // 영화 삭제
        System.out.print("삭제 하고 싶은 영화 id : ");
        Long movieId = Long.parseLong(scanner.nextLine());

        movieManager.deleteMovie(movieId);
    }

    private void showViewMovieMenu(){
        System.out.println("------------ 영화 조회 프로그램 ------------");
        System.out.println("1. 전체 영화 조회");
        System.out.println("2. 제목으로 영화 조회");
        System.out.println("-1. 프로그램 종료");
    }

    private void viewMovieChoice(){
        int choice;
        do{
            choice = getUserInput();
            switch (choice) {
                case 1: {
                    viewAllMovie();
                    break;
                }

                case 2: {
                    searchMovie();
                    break;
                }

                case -1: {
                    System.out.println("프로그램이 종료됩니다.");
                    break;
                }
                default: {
                    Exception.exception(ExceptionCode.INPUT_ERROR);
                }
            }

        } while (choice != -1);

    }

    private void showReservationMenu(){
        System.out.println("------------- 예약 관리 프로그램 -------------");
        System.out.println("1. 예약 하기");
        System.out.println("2. 전체 예약 조회");
        System.out.println("3. 이름으로 예약 조회");
        System.out.println("4. 예약 수정 (인원수만)");
        System.out.println("5. 예약 삭제");
        System.out.println("-1. 프로그램 종료");
    }

    private void reservationChoice(){
        int choice;
        do{
            choice = getUserInput();
            switch (choice){
                case 1: {
                    createReservation();
                    break;
                }
                case 2: {
                    viewAllReservation();
                    break;
                }

                case 3: {
                    getReservation();
                    break;
                }

                case 4: {
                    updateReservation();
                    break;
                }

                case 5: {
                    deleteReservation();
                    break;
                }

                case -1:{
                    System.out.println("프로그램이 종료됩니다.");
                    break;
                }
                default: {
                    Exception.exception(ExceptionCode.INPUT_ERROR);
                }
            }

        } while (choice != -1);
    }

    private void createReservation(){
        System.out.print("사용자 아이디 :");
        Long userId_r = Long.parseLong(scanner.nextLine());

        System.out.print("영화 아이디 : ");
        Long movieId_r = Long.parseLong(scanner.nextLine());

        System.out.print("에약 인원 수 : ");
        int headCount =  Integer.parseInt(scanner.nextLine());

        // 1. 예약 추가
        reservationManager.createReservation(new Reservation(LocalDateTime.now(),headCount, userId_r, movieId_r));
    }

    private void viewAllReservation(){
        // 2. 예약 조회
        List<Reservation> reservationList = reservationManager.reservationList();
        reservationList.forEach(System.out::println);
    }

    private void getReservation(){
        // 3. 사용자로 예약 조회
        System.out.print("사용자 이름으로 예약 조회 : ");
        String userName = scanner.nextLine();
        System.out.println(reservationManager.getReservation(userName));
    }

    private void updateReservation(){
        Long reservationId = null;
        // 영화 수정
        while (true) {
            System.out.print("수정 하고 싶은 예약 id : ");
            try {
                reservationId = Long.parseLong(scanner.nextLine());
                if (reservationManager.existReservation(reservationId)) {
                    break;
                } else {
                    System.out.println("잘못된 예약 id입니다. 다시 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
        // 4. 예약 수정
        System.out.print("수정된 인원 수 : ");
        int headCount = Integer.parseInt(scanner.nextLine());

        reservationManager.updateReservation(reservationId, headCount);
    }

    private void deleteReservation(){
        // 5. 예약 삭제
        System.out.print("삭제 하고 싶은 예약 id : ");
        Long reservationId = Long.parseLong(scanner.nextLine());
        reservationManager.deleteReservation(reservationId);
    }

}
