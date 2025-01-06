package com.concert.domain.seat;

import lombok.Getter;

@Getter
public enum SeatErrorCode {
    E50000("이미 예약된 좌석입니다"),
    E50001("존재하지 않는 좌석입니다"),
    E50002("유효하지 않은 좌석 번호입니다"),
    E50003("삭제된 좌석입니다");

    private final String message;

    SeatErrorCode(String message) {
        this.message = message;
    }
} 