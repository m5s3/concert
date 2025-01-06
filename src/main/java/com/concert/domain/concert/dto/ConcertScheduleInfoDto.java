package com.concert.domain.concert.dto;

import com.concert.domain.concert.ConcertScheduleEntity;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ConcertScheduleInfoDto(
        Long id,
        Long concertId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime reservationStartDate,
        Integer countOfSeat,
        Integer countOfRemainSeat
) {

    public static ConcertScheduleInfoDto from(ConcertScheduleEntity schedule) {
        return ConcertScheduleInfoDto.builder()
                .id(schedule.getId())
                .concertId(schedule.getConcertId())
                .startDate(schedule.getStartDate())
                .endDate(schedule.getEndDate())
                .reservationStartDate(schedule.getReservationStartDate())
                .countOfSeat(schedule.getCountOfSeat())
                .countOfRemainSeat(schedule.getCountOfRemainSeat())
                .build();
    }
}
