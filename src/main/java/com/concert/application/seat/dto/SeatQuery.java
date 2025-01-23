package com.concert.application.seat.dto;

import com.concert.domain.seat.dto.SeatSearchCriteria;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Builder
public record SeatQuery(
        @Nullable Long seatId,
        @Nullable Long scheduleId,
        @Nullable String seatNumber,
        @Nullable Boolean isReserved,
        @Nullable LocalDateTime startDate,
        long page,
        long size
) {

    public SeatSearchCriteria toCriteria() {
        return SeatSearchCriteria.builder()
                .seatId(seatId)
                .scheduleId(scheduleId)
                .seatNumber(seatNumber)
                .isReserved(isReserved)
                .page(page)
                .size(size)
                .build();
    }
}
