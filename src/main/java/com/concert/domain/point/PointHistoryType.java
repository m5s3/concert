package com.concert.domain.point;

import java.util.Arrays;

public enum PointHistoryType {
    CHARGE, USE;

    public static PointHistoryType fromString(String type) {
        return Arrays.stream(PointHistoryType.values())
                .filter(pointHistoryType -> pointHistoryType.toString().equalsIgnoreCase(type))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(type + "은 포인트 히스토리 타입이 아닙니다"));
    }
}
