package com.concert.domain.seat.dto;

import com.concert.domain.seat.SeatEntity;
import lombok.Builder;

@Builder
public record SeatInfoDto(
        Long id,
        Long scheduleId,
        String seatNumber,
        Boolean isReserved
) {

    public static SeatInfoDto from(SeatEntity newSeat) {
        return SeatInfoDto.builder()
                .id(newSeat.getId())
                .scheduleId(newSeat.getConcertScheduleId())
                .seatNumber(newSeat.getSeatNumber())
                .isReserved(newSeat.getIsReserved())
                .build();
    }
}
