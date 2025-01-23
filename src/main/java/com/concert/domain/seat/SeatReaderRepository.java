package com.concert.domain.seat;

import com.concert.domain.seat.dto.SeatSearchCriteria;

import java.util.List;

public interface SeatReaderRepository {

    SeatEntity getSeat(final long seatId);
    List<SeatEntity> getSeats(SeatSearchCriteria criteria);
}

