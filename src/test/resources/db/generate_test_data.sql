DELIMITER $$

DROP PROCEDURE IF EXISTS generate_concert_data$$
CREATE PROCEDURE generate_concert_data()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE j INT DEFAULT 1;
    DECLARE row_char CHAR(1);
    DECLARE col_num VARCHAR(2);
    DECLARE concert_id BIGINT;
    DECLARE schedule_id BIGINT;
    DECLARE seat_count INT;
    DECLARE now_time DATETIME DEFAULT NOW();
    
    -- 자동 커밋 비활성화
    SET autocommit = 0;
    
    -- 기존 데이터 삭제
    DELETE FROM reservation;
    DELETE FROM seat;
    DELETE FROM concert_schedule;
    DELETE FROM concert;
    
    -- 100만개 데이터 생성
    WHILE i <= 1000000 DO
        -- concert 테이블 데이터 삽입
        INSERT INTO concert (
            title, 
            description, 
            is_deleted, 
            created_at,
            updated_at
        ) VALUES (
            CONCAT('테스트 콘서트 ', i),
            CONCAT('테스트 콘서트 설명 ', i),
            false,
            now_time,
            now_time
        );
        
        SET concert_id = LAST_INSERT_ID();
        
        -- 랜덤하게 50~10000 사이의 좌석 수 결정
        SET seat_count = 50 + FLOOR(RAND() * 9951);
        
        -- concert_schedule 테이블 데이터 삽입
        INSERT INTO concert_schedule (
            concert_id,
            start_date,
            end_date,
            reservation_start_date,
            count_of_seat,
            count_of_remain_seat,
            is_deleted,
            created_at,
            updated_at
        ) VALUES (
            concert_id,
            DATE_ADD(now_time, INTERVAL i DAY),
            DATE_ADD(now_time, INTERVAL (i + 2) DAY),
            DATE_ADD(now_time, INTERVAL (i - 1) DAY),
            seat_count,
            seat_count,
            false,
            now_time,
            now_time
        );
        
        SET schedule_id = LAST_INSERT_ID();
        
        -- 좌석 생성
        SET j = 1;
        WHILE j <= seat_count DO
            -- 행 번호 계산 (A-Z)
            SET row_char = CHAR(65 + ((j-1) DIV 50));
            -- 열 번호 계산 (01-50)
            SET col_num = LPAD(((j-1) % 50) + 1, 2, '0');
            
            INSERT INTO seat (
                concert_schedule_id,
                seat_number,
                is_reserved,
                is_deleted,
                created_at,
                updated_at
            ) VALUES (
                schedule_id,
                CONCAT(row_char, col_num),
                false,
                false,
                now_time,
                now_time
            );
            
            SET j = j + 1;
            
            -- 1000개의 좌석마다 커밋
            IF j % 1000 = 0 THEN
                COMMIT;
            END IF;
        END WHILE;
        
        -- 10000건의 콘서트마다 커밋
        IF i % 10000 = 0 THEN
            COMMIT;
            SET now_time = NOW();
        END IF;
        
        SET i = i + 1;
    END WHILE;
    
    -- 마지막 커밋
    COMMIT;
    SET autocommit = 1;
END$$

DELIMITER ;

-- 프로시저 실행
CALL generate_concert_data(); 