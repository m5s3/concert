package com.concert.interfaces.api.concert.dto;

import com.concert.application.concert.dto.ConcertQuery;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.LocalDateTime;

public record ConcertSearchQuery(
        @Nullable Long concertId,
        @Nullable String title,
        @Nullable String description,
        @Nullable Long scheduleId,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
        @Nullable LocalDateTime startDate,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
        @Nullable LocalDateTime endDate,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
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
