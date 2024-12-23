package com.concert.domain.member;

import lombok.Getter;

@Getter
public enum MemberErrorCode {
    E10000("이름을 입력해 주세요"),
    E10001("이름은 2자 이상 20자 이하로 입력해 주세요"),
    E10002("해당 유저의 이름은 존재합니다. 다른 이름을 입력해 주세요"),
    E10003("해당 유저는 존재하지 않습니다");

    private String message;

    MemberErrorCode(String message) {
        this.message = message;
    }
}
