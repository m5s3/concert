package com.concert.application.concert;

import com.concert.application.concert.dto.ConcertDto;
import com.concert.application.concert.dto.ConcertQuery;
import com.concert.application.concert.dto.CreateConcertCommend;
import com.concert.domain.concert.ConcertService;
import com.concert.domain.concert.dto.ConcertInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConcertFacade {

    private final ConcertService concertService;

    public ConcertDto createConcert(CreateConcertCommend commend) {
        ConcertInfoDto concert = concertService.createConcert(commend.toConcertDto(), commend.toConcertScheduleDto());
        log.info("concert created : {}", concert);
        return ConcertDto.fromConcertInfo(concert);
    }

    public ConcertDto getConcert(ConcertQuery query) {
        ConcertInfoDto concertWithConcertSchedule = concertService.getConcertWithConcertSchedule(query.concertId());
        return ConcertDto.fromConcertInfo(concertWithConcertSchedule);
    }

    public List<ConcertDto> searchConcerts(ConcertQuery query) {
        List<ConcertInfoDto> concerts = concertService.searchConcerts(query.toSearchCriteria());
        return concerts.stream().map(ConcertDto::fromConcertInfo).collect(Collectors.toList());
    }
}
