package com.concert.domain.concert;

import com.concert.domain.concert.dto.NewConcertScheduleDto;

public interface ConcertScheduleStoreRepository {

    ConcertScheduleEntity save(Long concertId, NewConcertScheduleDto concertScheduleDto);
}
