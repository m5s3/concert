package com.concert.domain.concert;

import com.concert.domain.concert.dto.SearchCriteria;

import java.util.List;

public interface ConcertScheduleReaderRepository {

    ConcertScheduleEntity getSchedule(Long concertId);
    List<ConcertScheduleEntity> getSchedules(List<Long> concertIds);
}
