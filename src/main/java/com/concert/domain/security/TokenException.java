package com.concert.domain.security;

public class TokenException extends RuntimeException {

    private final TokenErrorCode errorCode;
    private final String message;

    public TokenException(TokenErrorCode errorCode) {
        this(errorCode, errorCode.getMessage());
    }

    public TokenException(TokenErrorCode errorCode, String message) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.message = message;
    }
}
