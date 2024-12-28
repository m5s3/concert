package com.concert.domain.concert.dto;

import com.concert.domain.concert.ConcertEntity;
import com.concert.domain.concert.ConcertScheduleEntity;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ConcertInfoDto(
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

    public static ConcertInfoDto fromEntities(ConcertEntity concert, ConcertScheduleEntity schedule) {
        return ConcertInfoDto.builder()
                .id(concert.getId())
                .title(concert.getTitle())
                .description(concert.getDescription())
                .scheduleId(schedule.getId())
                .startDate(schedule.getStartDate())
                .endDate(schedule.getEndDate())
                .reservationStartDate(schedule.getReservationStartDate())
                .countOfSeat(schedule.getCountOfSeat())
                .countOfRemainSeat(schedule.getCountOfRemainSeat())
                .build();
    }
}
