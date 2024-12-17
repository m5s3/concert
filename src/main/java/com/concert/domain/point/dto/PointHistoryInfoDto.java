package com.concert.domain.point.dto;

import com.concert.domain.point.PointHistoryEntity;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record PointHistoryInfoDto(
        Long id,
        Long pointId,
        Long memberId,
        String type,
        BigDecimal amount,
        LocalDateTime createdAt
) {
    public static PointHistoryInfoDto from(PointHistoryEntity pointHistory) {
        return PointHistoryInfoDto.builder()
                .id(pointHistory.getId())
                .pointId(pointHistory.getPointId())
                .memberId(pointHistory.getMemberId())
                .type(pointHistory.getType().name())
                .amount(pointHistory.getAmount())
                .createdAt(pointHistory.getCreatedAt())
                .build();
    }
}
