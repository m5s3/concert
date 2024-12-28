package com.concert.domain.concert;

import com.concert.domain.concert.dto.NewConcertDto;
import com.concert.domain.concert.dto.NewConcertScheduleDto;

public interface ConcertStoreRepository {

    ConcertEntity save(NewConcertDto concertDto);
}
