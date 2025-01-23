package com.concert.interfaces.api.concert.dto;

import com.concert.application.seat.dto.SeatDto;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
public record SeatResponse(
        Long id,
        Long scheduleId,
        String seatNumber,
        Boolean isReserved
) {
    public static SeatResponse from(SeatDto seatDto) {
        return SeatResponse.builder()
                .id(seatDto.id())
                .scheduleId(seatDto.scheduleId())
                .seatNumber(seatDto.seatNumber())
                .isReserved(seatDto.isReserved())
                .build();
    }
}
