DELIMITER $$

DROP PROCEDURE IF EXISTS generate_concert_data$$
CREATE PROCEDURE generate_concert_data()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE concert_id BIGINT;
    DECLARE now_time DATETIME DEFAULT NOW();
    
    -- 자동 커밋 비활성화로 성능 향상
    SET autocommit = 0;
    
    -- 기존 데이터 삭제
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
            100,
            100,
            false,
            now_time,
            now_time
        );
        
        -- 10000건마다 커밋
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