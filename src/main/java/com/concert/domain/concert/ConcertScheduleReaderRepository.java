package com.concert.domain.concert;

import java.util.List;

public interface ConcertScheduleReaderRepository {

    ConcertScheduleEntity getScheduleOfSchedule(Long concertId);
    ConcertScheduleEntity getSchedule(Long concertScheduleId);
    List<ConcertScheduleEntity> getSchedules(List<Long> concertIds);
}
