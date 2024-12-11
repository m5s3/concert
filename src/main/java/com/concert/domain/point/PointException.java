package com.concert.domain.point;

import lombok.Getter;

@Getter
public class PointException extends RuntimeException {

    private final PointErrorCode errorCode;
    private final String message;

    public PointException(PointErrorCode errorCode) {
        this(errorCode, errorCode.getMessage());
    }

    public PointException(PointErrorCode errorCode, String message) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.message = message;
    }
}
