package com.concert.domain.concert.dto;

import com.concert.domain.concert.ConcertEntity;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record NewConcertDto(
        String title,
        String description
) {

    public ConcertEntity toEntity() {
        return ConcertEntity.builder()
                .title(title)
                .description(description)
                .build();
    }
}
