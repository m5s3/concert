package com.concert.application.Point.dto;

import com.concert.domain.point.dto.PointInfoDto;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PointDto(
        Long id,
        Long memberId,
        BigDecimal amount
) {

    public static PointDto from(PointInfoDto point) {
        return PointDto.builder()
                .id(point.id())
                .memberId(point.memberId())
                .amount(point.amount())
                .build();
    }
}
