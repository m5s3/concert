package com.concert.interfaces.api.concert.dto;

import com.concert.application.concert.dto.ConcertDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ConcertResponse(
        Long id,
        String title,
        String description,
        Long scheduleId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime reservationStartDate,
        int countOfSeat,
        int countOfRemainSeat
) {

    public static ConcertResponse from(ConcertDto dto) {
        return ConcertResponse.builder()
                .id(dto.id())
                .title(dto.title())
                .description(dto.description())
                .scheduleId(dto.scheduleId())
                .startDate(dto.startDate())
                .endDate(dto.endDate())
                .reservationStartDate(dto.reservationStartDate())
                .countOfSeat(dto.countOfSeat())
                .countOfRemainSeat(dto.countOfRemainSeat())
                .build();
    }
}
