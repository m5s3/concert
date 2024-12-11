package com.concert.domain.point.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record NewPointDto(
        Long memberId,
        BigDecimal amount
) {
}
