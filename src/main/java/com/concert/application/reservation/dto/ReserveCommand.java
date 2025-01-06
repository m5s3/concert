package com.concert.application.reservation.dto;

import lombok.Builder;

@Builder
public record ReserveCommand(
        Long seatId,
        Long concertScheduleId,
        Long memberId
) {
}
