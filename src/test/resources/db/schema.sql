-- member 테이블
CREATE TABLE IF NOT EXISTS member (
                                      member_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(50) NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE,
    created_at DATETIME NOT NULL,
    updated_at DATETIME
    );

-- point 테이블
CREATE TABLE IF NOT EXISTS point (
    point_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id BIGINT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE,
    created_at DATETIME NOT NULL,
    updated_at DATETIME
);

-- point_history 테이블
CREATE TABLE IF NOT EXISTS point_history (
    point_history_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    point_id BIGINT NOT NULL,
    member_id BIGINT NOT NULL,
    type VARCHAR(20) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE,
    created_at DATETIME NOT NULL,
    updated_at DATETIME
);

-- concert 테이블
CREATE TABLE IF NOT EXISTS concert (
                                       concert_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       title VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    is_deleted BOOLEAN DEFAULT FALSE,
    created_at DATETIME NOT NULL,
    updated_at DATETIME
    );

-- concert_schedule 테이블 추가
CREATE TABLE IF NOT EXISTS concert_schedule (
                                                concert_schedule_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                concert_id BIGINT NOT NULL,
                                                start_date DATETIME NOT NULL,
                                                end_date DATETIME NOT NULL,
                                                reservation_start_date DATETIME NOT NULL,
                                                count_of_seat INT NOT NULL,
                                                count_of_remain_seat INT NOT NULL,
                                                is_deleted BOOLEAN DEFAULT FALSE,
                                                created_at DATETIME NOT NULL,
                                                updated_at DATETIME,
                                                FOREIGN KEY (concert_id) REFERENCES concert(concert_id)  -- 외래 키 추가
    );