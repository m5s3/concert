package com.concert.application.reservation.dto;

import com.concert.domain.reservation.dto.ReservationInfo;
import lombok.Builder;

@Builder
public record ReservationDto(
        Long concertSchedule,
        Long seatId,
        Long memberId,
        String status
) {

    public static ReservationDto from(ReservationInfo reservationInfo) {
        return ReservationDto.builder()
                .concertSchedule(reservationInfo.concertScheduleId())
                .seatId(reservationInfo.seatId())
                .memberId(reservationInfo.memberId())
                .status(reservationInfo.status())
                .build();
    }
}
