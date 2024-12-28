package com.concert.interfaces.api.concert.dto;

import com.concert.application.concert.dto.CreateConcertCommend;

import java.time.LocalDateTime;

public record CreateConcertRequest(
        String title,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime reservationStartDate,
        int countOfSeat
) {

    public CreateConcertCommend toCommend() {
        return CreateConcertCommend.builder()
                .title(title)
                .description(description)
                .startDate(startDate)
                .endDate(endDate)
                .reservationStartDate(reservationStartDate)
                .countOfSeat(countOfSeat)
                .countOfRemainSeat(countOfSeat)
                .build();
    }
}
