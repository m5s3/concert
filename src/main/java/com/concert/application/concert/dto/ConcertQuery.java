package com.concert.application.concert.dto;

import com.concert.domain.concert.dto.SearchCriteria;
import jakarta.annotation.Nullable;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ConcertQuery(
        @Nullable Long concertId,
        @Nullable String title,
        @Nullable String description,
        @Nullable Long scheduleId,
        @Nullable LocalDateTime startDate,
        @Nullable LocalDateTime endDate,
        @Nullable LocalDateTime reservationStartDate
) {

    public SearchCriteria toSearchCriteria() {
        return SearchCriteria.builder()
                .title(title)
                .description(description)
                .startDate(startDate)
                .endDate(endDate)
                .reservationStartDate(reservationStartDate)
                .build();
    }
}
