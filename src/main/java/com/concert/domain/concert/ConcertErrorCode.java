package com.concert.domain.concert;

import lombok.Getter;

@Getter
public enum ConcertErrorCode {
    E40000("제목을 입력해 주세요"),
    E40001("제목은 2자 이상 20자 이하로 입력해 주세요"),
    E40002("설명을 입력해 주세요"),
    E40003("설명은 2자 이상 255자 이하로 입력해 주세요"),
    E40010("날짜를 설정해 주세요"),
    E40011("현재 시점 이후로만 설정 가능합니다."),
    E40012("콘서트 시작인을 종료일 이후여야 합니다"),
    E40013("콘서트 예약일은 시작일 이후여야 합니다"),
    E40014("좌석 수가 최소값 보다 적습니다"),
    E40015("좌석 수가 취대값 보다 큽니다"),
    E40016("좌석 수는 잔여 좌석 수 보다 더 커야 합나디");

    private String message;

    ConcertErrorCode(String message) {
        this.message = message;
    }
}