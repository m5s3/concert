package com.concert.domain.security;

import lombok.Getter;

@Getter
public enum TokenErrorCode {

    INVALID_TOKEN("E30000", "잘못 토큰입니다"),
    EXPIRED_TOKEN("E30001", "만료된 토큰입니다"),
    UNSUPPORTED_TOKEN("E30002", "지원하지 않는 토큰입니다"),
    EMPTY_TOKEN("E30003", "토큰이 비어 있습니다"),
    NOT_EXIST_MEMBER("E30004", "토큰에 유저 정보가 없습니다");

    private String errorCode;
    private String message;

    TokenErrorCode(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
