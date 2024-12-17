package com.concert.domain.point.dto;

import com.concert.domain.point.PointHistoryEntity;
import com.concert.domain.point.PointHistoryType;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record NewPointHistoryInfoDto(
        Long pointId,
        Long memberId,
        PointHistoryType type,
        BigDecimal amount,
        LocalDateTime createdAt
) {

    public PointHistoryEntity toEntity() {
        return PointHistoryEntity.builder()
                .amount(amount)
                .pointId(pointId)
                .memberId(memberId)
                .type(type)
                .build();
    }
}
