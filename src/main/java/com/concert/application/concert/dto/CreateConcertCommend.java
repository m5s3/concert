package com.concert.application.concert.dto;

import com.concert.domain.concert.dto.NewConcertDto;
import com.concert.domain.concert.dto.NewConcertScheduleDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CreateConcertCommend(
        String title,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime reservationStartDate,
        int countOfSeat,
        int countOfRemainSeat
) {

    public NewConcertDto toConcertDto() {
        return NewConcertDto.builder()
                .title(title)
                .description(description)
                .build();
    }

    public NewConcertScheduleDto toConcertScheduleDto() {
        return NewConcertScheduleDto.builder()
                .startDate(startDate)
                .endDate(endDate)
                .reservationStartDate(reservationStartDate)
                .countOfSeat(countOfSeat)
                .countOfRemainSeat(countOfRemainSeat)
                .build();
    }
}
