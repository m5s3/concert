package com.concert.infrastructrue.reservation;

import com.concert.domain.reservation.ReservationEntity;
import com.concert.domain.reservation.ReservationStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationStoreRepositoryImpl implements ReservationStoreRepository {

    private final ReservationJPAStoreRepository repository;

    @Override
    public ReservationEntity save(final long concertScheduleId,final long seatId,final long memberId) {
        ReservationEntity newReservation = ReservationEntity.builder()
                .concertScheduleId(concertScheduleId)
                .seatId(seatId)
                .memberId(memberId)
                .build();
        return repository.save(newReservation);
    }
}
