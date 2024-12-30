package com.concert.domain.concert;

import com.concert.domain.concert.dto.SearchCriteria;

import java.util.List;

public interface ConcertReaderRepository {

    ConcertEntity getConcert(Long id);
    List<ConcertEntity> searchConcerts(SearchCriteria criteria);
}
