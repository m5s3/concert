package com.concert.application.seat.dto;

import com.concert.domain.seat.dto.SeatInfoDto;
import lombok.Builder;

@Builder
public record SeatDto(
        Long id,
        Long scheduleId,
        String seatNumber,
        Boolean isReserved
) {

    public static SeatDto from(SeatInfoDto seatInfoDto) {
        return SeatDto.builder()
                .id(seatInfoDto.id())
                .scheduleId(seatInfoDto.scheduleId())
                .seatNumber(seatInfoDto.seatNumber())
                .isReserved(seatInfoDto.isReserved())
                .build();
    }
}
