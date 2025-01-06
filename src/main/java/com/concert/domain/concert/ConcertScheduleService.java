package com.concert.domain.concert;

import com.concert.domain.concert.dto.ConcertScheduleInfoDto;
import com.concert.domain.reservation.ReservationException;
import com.concert.domain.reservation.ReservationErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class ConcertScheduleService {

    private final ConcertScheduleReaderRepository concertScheduleReader;
    private final ConcertScheduleValidator concertScheduleValidator;

    public ConcertScheduleInfoDto decreaseRemainSeat(Long scheduleId) {
        ConcertScheduleEntity schedule = concertScheduleReader.getSchedule(scheduleId);
        concertScheduleValidator.validateReservationAvailable(schedule);
        schedule.decreaseRemainSeat();
        return ConcertScheduleInfoDto.from(schedule);
    }
} 