# pgrs_DBProject_0801
8월 1일 토이 프로젝트

## 1. 어떤 서비스를 제안하는지 기획 개요

- 영화 예매 서비스
    - 매니저 - 영화 등록
    - 일반 유저 - 영화 예매

## 2. ERD

- 유저(users)
    - user_id, nickname, password, role, birth_date
- 영화(movies)
    - movie_id, movie_name, genre, start_date, end_date, director, rating
- 예매(reservations)
    - reservation_id, reservation_date, head_count, user_id(FK), movie_id(FK)
- ERD
    
![스크린샷 2024-08-05 오전 10 20 04](https://github.com/user-attachments/assets/db1bb874-183e-47eb-8437-dc8890c2c02b)

    
- DDL

```sql
-- 유저 테이블 생성
CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nickname VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50),
    birth_date DATE
);

-- 영화 테이블 생성
CREATE TABLE movies (
    movie_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    movie_name VARCHAR(255) NOT NULL,
    genre VARCHAR(100),
    start_date DATE,
    end_date DATE,
    director VARCHAR(255),
    rating INT
);

-- 예매 테이블 생성
CREATE TABLE reservations (
    reservation_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    reservation_date DATE NOT NULL,
    head_count INT NOT NULL,
    user_id BIGINT,
    movie_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (movie_id) REFERENCES movies(movie_id)
);

```

## 3. 인터페이스 구현

```java
public interface IMovieManager {
	
	void addMovie(Movie movie); // 영화 등록
	
	void deleteMovie(int id); // 영화 삭제
	
	void updateMovie(int id, LocalDate startDate, LocalDate endDate); // 영화 수정(날짜만 가능)
	
	Movie getMovie(String title); //제목으로 영화 검색
	
	List<Movie> movieList(); //영화 전체 목록
```

```java
public interface IReservationManager {

void createReservation (Reservation reservation); //예약하기 

Reservation getReservation (String userName); // 이름으로 예약 검색

List<Reservation> reservationList(); // 예약 전체 목록

void deleteReservation(int id); // 예약 삭제하기 

void updateReservation(int id, int headCount); // 예약 인원수 수정

}
```

```java
public interface IUserManager {

	User login(String nickname, password); // 로그인
	
	void createUser(User user); // 회원 가입
	
	void deleteUser(int id); // 회원 삭제
}
```

- Main.java
    
  1. 유저 로그인 / 회원 가입
    
  2. 역할이 관리자일 시 영화 추가, 삭제, 수정 등 영화 관리 프로그램 실행

  3. 역할이 사용자일 시 영화 조회, 검색 등 영화 조회 프로그램 실행

  4. 영화 조회 후 사용자는 예매 프로그램으로 넘어감.

  5. 예매 프로그램에서는 예매, 예매 조회, 수정, 삭제가 가능한 예매 프로그램 실행

  5-1. 예매할 때 영화의 상영 날짜와 일치하지 않을 시 예매 불가능

  5-2. 예매할 때 영화의 등급과 사용자의 나이가 맞지 않으면 예매 불가능

- 코드 컨벤션
    - 변수/함수: camelCase
    - 클래스: PascalCase
    - 패키지: alllowercase
    - 상수: UPPER_SNAKE_CASE
