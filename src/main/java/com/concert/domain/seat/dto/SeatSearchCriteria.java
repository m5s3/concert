package com.concert.domain.seat.dto;

import lombok.Builder;
import org.springframework.lang.Nullable;

@Builder
public record SeatSearchCriteria(
        @Nullable Long seatId,
        @Nullable Long concertId,
        @Nullable Long scheduleId,
        @Nullable String seatNumber,
        @Nullable Boolean isReserved,
        long page,
        long size
) {
}
