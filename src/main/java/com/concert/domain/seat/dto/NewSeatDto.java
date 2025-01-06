package com.concert.domain.seat.dto;

import com.concert.domain.seat.SeatEntity;
import lombok.Builder;

@Builder
public record NewSeatDto(
        Long concertScheduleId,
        String seatNumber
) {

    public SeatEntity toEntity() {
        return SeatEntity.builder()
                .concertScheduleId(concertScheduleId)
                .seatNumber(seatNumber)
                .build();
    }
}
