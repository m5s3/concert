package com.concert.domain.point;

import lombok.Getter;

@Getter
public enum PointErrorCode {
    E20000("잔여 포인트보다 더 사용하 실 수 없습니다"),
    E20001("최대 충전 포인트는 %s 입니다".formatted(PointConst.MAX.toString())),
    E20002("최소 충전 포인트는 %s 입니다".formatted(PointConst.MIN.toString())),
    E20003("최소 사용 포인트는 %s 입니다".formatted(PointConst.MIN.toString())),
    E20004("해당 유저는 포인트가 없습니다");

    private final String message;

    PointErrorCode(String message) {
        this.message = message;
    }
}
