package com.concert.domain.reservation;

import com.concert.domain.reservation.dto.ReservationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationStoreRepository reservationStoreRepository;

    public ReservationInfo create(final Long memberId, final Long scheduleId, final Long seatId) {
        ReservationEntity newReservation = reservationStoreRepository.save(memberId, scheduleId, seatId);
        return ReservationInfo.from(newReservation);
    }
}
