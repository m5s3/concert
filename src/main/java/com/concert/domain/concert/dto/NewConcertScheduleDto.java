package com.concert.domain.concert.dto;

import com.concert.domain.concert.ConcertScheduleEntity;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record NewConcertScheduleDto(
        Long concertId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime reservationStartDate,
        int countOfSeat,
        int countOfRemainSeat
) {
}
