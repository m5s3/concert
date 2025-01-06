package com.concert.domain.reservation.dto;

import com.concert.domain.reservation.ReservationEntity;
import lombok.Builder;

@Builder
public record ReservationInfo(
        Long id,
        Long concertScheduleId,
        Long seatId,
        Long memberId,
        String status
) {

    public static ReservationInfo from(ReservationEntity reservation) {
        return ReservationInfo.builder()
                .id(reservation.getId())
                .concertScheduleId(reservation.getConcertScheduleId())
                .seatId(reservation.getSeatId())
                .memberId(reservation.getMemberId())
                .status(reservation.getStatus().name())
                .build();
    }
}
