-- member 테이블 초기 데이터
INSERT INTO member (name, is_deleted, created_at)
VALUES
    ('test_user1', false, NOW()),
    ('test_user2', false, NOW());

-- point 테이블 초기 데이터
INSERT INTO point (member_id, amount, is_deleted, created_at)
VALUES
    (1, 10000.00, false, NOW()),
    (2, 20000.00, false, NOW());

-- point_history 테이블 초기 데이터
INSERT INTO point_history (
    point_id,
    type,
    amount,
    is_deleted,
    created_at
)
VALUES
    (1, 'CHARGE', 10000.00, false, NOW()),
    (2, 'CHARGE', 20000.00, false, NOW());

-- concert 테이블 초기 데이터
INSERT INTO concert (title, description, is_deleted, created_at)
VALUES
    ('테스트 콘서트1', '테스트 설명1', false, NOW()),
    ('테스트 콘서트2', '테스트 설명2', false, NOW());

-- concert_schedule 테이블 초기 데이터
INSERT INTO concert_schedule (
    concert_id,
    start_date,
    end_date,
    reservation_start_date,
    count_of_seat,
    count_of_remain_seat,
    is_deleted,
    created_at
)
VALUES
    (1, DATE_ADD(NOW(), INTERVAL 1 DAY), DATE_ADD(NOW(), INTERVAL 2 DAY), NOW(), 100, 100, false, NOW()),
    (2, DATE_ADD(NOW(), INTERVAL 3 DAY), DATE_ADD(NOW(), INTERVAL 4 DAY), NOW(), 200, 200, false, NOW());


-- concert_reservation 테이블 초기 데이터
INSERT INTO concert_reservation (
    concert_schedule_id,
    member_id,
    seat_id,
    status,
    is_deleted,
    created_at
)
VALUES
    (1, 1, 1, 'RESERVED', false, NOW()),
    (2, 2, 3, 'RESERVED', false, NOW());