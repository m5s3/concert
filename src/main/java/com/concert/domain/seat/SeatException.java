package com.concert.domain.seat;

import lombok.Getter;

@Getter
public class SeatException extends RuntimeException {
    private final SeatErrorCode errorCode;

    public SeatException(SeatErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
} 