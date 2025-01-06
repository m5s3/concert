package com.concert.domain.seat;

import org.springframework.stereotype.Component;

@Component
public class SeatValidator {

    public void validateSeatAvailable(SeatEntity seat) {
        if (seat == null) {
            throw new SeatException(SeatErrorCode.E50001);
        }
        if (seat.getIsDeleted()) {
            throw new SeatException(SeatErrorCode.E50003);
        }
        if (seat.getIsReserved()) {
            throw new SeatException(SeatErrorCode.E50000);
        }
    }
}
