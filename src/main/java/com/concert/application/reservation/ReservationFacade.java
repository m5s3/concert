package com.concert.application.reservation;

import com.concert.application.reservation.dto.ReservationDto;
import com.concert.application.reservation.dto.ReserveCommand;
import com.concert.domain.concert.ConcertScheduleService;
import com.concert.domain.core.lock.annotation.DistributedLockOperation;
import com.concert.domain.reservation.ReservationService;
import com.concert.domain.reservation.dto.ReservationInfo;
import com.concert.domain.seat.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationFacade {

    private final ConcertScheduleService concertScheduleService;
    private final ReservationService reservationService;
    private final SeatService seatService;

    @DistributedLockOperation(
            key = "reservation",
            prefix = "#command.seatId",
            waitTime = 5,
            leaseTime = 3
    )
    public ReservationDto reserve(ReserveCommand command) {
        // 1. 콘서트 스케줄 조회
        concertScheduleService.decreaseRemainSeat(command.concertScheduleId());

        // 2. 좌석 조회
        seatService.reserve(command.seatId());

        // 3. 예약 처리
        ReservationInfo reservationInfo = reservationService.create(command.memberId(), command.concertScheduleId(), command.seatId());
        return ReservationDto.from(reservationInfo);
    }
}