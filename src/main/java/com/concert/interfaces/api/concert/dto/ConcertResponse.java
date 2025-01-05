package com.concert.interfaces.api.concert.dto;

import com.concert.application.concert.dto.ConcertDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ConcertResponse(
        Long id,
        String title,
        String description,
        Long scheduleId,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
        LocalDateTime startDate,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
        LocalDateTime endDate,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
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
