package com.concert.domain.point.dto;

import com.concert.domain.point.PointEntity;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PointInfoDto(
        Long id,
        Long memberId,
        BigDecimal amount
) {

    public static PointInfoDto from(PointEntity pointEntity) {
        return PointInfoDto.builder()
                .id(pointEntity.getId())
                .memberId(pointEntity.getMemberId())
                .amount(pointEntity.getAmount())
                .build();
    }

    public PointEntity toEntity() {
        return PointEntity.builder()
                .memberId(memberId)
                .amount(amount)
                .build();
    }
}
