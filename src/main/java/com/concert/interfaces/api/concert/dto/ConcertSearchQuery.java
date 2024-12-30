package com.concert.interfaces.api.concert.dto;

import com.concert.application.concert.dto.ConcertQuery;
import jakarta.annotation.Nullable;

import java.time.LocalDateTime;

public record ConcertSearchQuery(
        @Nullable Long concertId,
        @Nullable String title,
        @Nullable String description,
        @Nullable Long scheduleId,
        @Nullable LocalDateTime startDate,
        @Nullable LocalDateTime endDate,
        @Nullable LocalDateTime reservationStartDate
) {

    public ConcertQuery toConcertQuery() {
        return ConcertQuery.builder()
                .concertId(concertId)
                .title(title)
                .description(description)
                .scheduleId(scheduleId)
                .startDate(startDate)
                .endDate(endDate)
                .reservationStartDate(reservationStartDate)
                .build();
    }
}
