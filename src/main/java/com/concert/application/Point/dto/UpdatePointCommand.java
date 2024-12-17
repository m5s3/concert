package com.concert.application.Point.dto;

import com.concert.domain.point.PointHistoryType;
import com.concert.domain.point.dto.NewPointHistoryInfoDto;
import com.concert.domain.point.dto.PointInfoDto;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    public NewPointHistoryInfoDto toNewPointHistoryDto(Long pointId, PointHistoryType type) {
        return NewPointHistoryInfoDto.builder()
                .pointId(pointId)
                .type(type)
                .amount(amount)
                .memberId(memberId)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
