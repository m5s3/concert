package com.concert.domain.reservation;

import lombok.Getter;

@Getter
public enum ReservationErrorCode {
    E60000("이미 취소된 예약입니다"),
    E60001("존재하지 않는 예약입니다"),
    E60002("예약 가능한 시간이 아닙니다"),
    E60003("예약 취소 가능한 시간이 지났습니다"),
    E60004("다른 사용자의 예약입니다"),
    E60005("이미 삭제된 예약입니다"),
    E60006("잔여 좌석이 없습니다");

    private final String message;

    ReservationErrorCode(String message) {
        this.message = message;
    }
} 