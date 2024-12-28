package com.concert.domain.concert;

import com.concert.domain.concert.dto.ConcertInfoDto;
import com.concert.domain.concert.dto.NewConcertDto;
import com.concert.domain.concert.dto.NewConcertScheduleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ConcertService {

    private final ConcertStoreRepository concertStoreRepository;
    private final ConcertScheduleStoreRepository concertScheduleStoreRepository;
    private final ConcertValidator concertValidator;

    public ConcertInfoDto createConcert(NewConcertDto concertDto, NewConcertScheduleDto concertScheduleDto) {
        concertValidator.validate(concertDto, concertScheduleDto);
        log.info("concertScheduleDto={}", concertScheduleDto);
        ConcertEntity newConcert = concertStoreRepository.save(concertDto);
        ConcertScheduleEntity newConcertSchedule = concertScheduleStoreRepository.save(newConcert.getId(), concertScheduleDto);
        return ConcertInfoDto.fromEntities(newConcert, newConcertSchedule);
    }
}
