package com.concert.application.Point.dto;

import com.concert.domain.point.dto.PointInfoDto;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record UpdatePointCommand(
        Long memberId,
        BigDecimal amount
) {

    public PointInfoDto toPointInfoDto() {
        return PointInfoDto.builder()
                .memberId(memberId)
                .amount(amount)
                .build();
    }
}
