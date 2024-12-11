package com.concert.domain.point.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PointInfo(
        Long id,
        BigDecimal point
) {
}
