package com.concert.domain.concert;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ConcertScheduleValidator {

    public void validateReservationAvailable(ConcertScheduleEntity schedule) {
        validateConcertSchedule(schedule);
        validateReservationTime(schedule);
    }

    private void validateConcertSchedule(ConcertScheduleEntity schedule) {
        if (schedule == null) {
            throw new ConcertException(ConcertErrorCode.E40017);
        }
    }

    private void validateReservationTime(ConcertScheduleEntity schedule) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(schedule.getReservationStartDate())) {
            throw new ConcertException(ConcertErrorCode.E40018);
        }
        if (now.isAfter(schedule.getEndDate())) {
            throw new ConcertException(ConcertErrorCode.E40019);
        }
    }
}
