package com.concert.application.concert.dto;

import com.concert.domain.concert.dto.ConcertInfoDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ConcertDto(
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

    public static ConcertDto fromConcertInfo(ConcertInfoDto concertInfoDto) {
        return ConcertDto.builder()
                .id(concertInfoDto.id())
                .title(concertInfoDto.title())
                .description(concertInfoDto.description())
                .scheduleId(concertInfoDto.scheduleId())
                .startDate(concertInfoDto.startDate())
                .endDate(concertInfoDto.endDate())
                .reservationStartDate(concertInfoDto.reservationStartDate())
                .countOfSeat(concertInfoDto.countOfSeat())
                .countOfRemainSeat(concertInfoDto.countOfRemainSeat())
                .build();
    }
}
