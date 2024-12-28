package com.concert.domain.concert;

import lombok.Getter;

@Getter
public class ConcertException extends RuntimeException {

    private final ConcertErrorCode errorCode;

    public ConcertException(ConcertErrorCode errorCode) {
        super(errorCode.getMessage(), null, false, false); // 스택 트레이스 비활성화 및 단순 메시지만 설정
        this.errorCode = errorCode;
    }

    public static ConcertException ofErrorCode(ConcertErrorCode errorCode) {
        return new ConcertException(errorCode);
    }
}