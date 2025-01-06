package com.concert.domain.reservation;

public interface ReservationStoreRepository {

    ReservationEntity save(final long concertScheduleId, final long seatId, final long memberId);
}
