package com.concert.interfaces.api.point.dto;

import com.concert.application.Point.dto.PointDto;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PointResponse(
        Long id,
        Long memberId,
        BigDecimal amount
) {

    public static PointResponse from(PointDto point) {
        return PointResponse
                .builder()
                .id(point.id())
                .memberId(point.memberId())
                .amount(point.amount())
                .build();
    }
}
