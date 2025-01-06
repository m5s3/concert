package com.concert.domain.concert;

import com.concert.domain.concert.dto.ConcertInfoDto;
import com.concert.domain.concert.dto.NewConcertDto;
import com.concert.domain.concert.dto.NewConcertScheduleDto;
import com.concert.domain.concert.dto.SearchCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ConcertService {

    private final ConcertStoreRepository concertStoreRepository;
    private final ConcertReaderRepository concertReaderRepository;
    private final ConcertScheduleStoreRepository concertScheduleStoreRepository;
    private final ConcertScheduleReaderRepository concertScheduleReaderRepository;
    private final ConcertValidator concertValidator;

    public ConcertInfoDto createConcert(NewConcertDto concertDto, NewConcertScheduleDto concertScheduleDto) {
        concertValidator.validate(concertDto, concertScheduleDto);
        log.info("concertScheduleDto={}", concertScheduleDto);
        ConcertEntity newConcert = concertStoreRepository.save(concertDto);
        ConcertScheduleEntity newConcertSchedule = concertScheduleStoreRepository.save(newConcert.getId(), concertScheduleDto);
        return ConcertInfoDto.fromEntities(newConcert, newConcertSchedule);
    }

    @Transactional(readOnly = true)
    public ConcertInfoDto getConcertWithConcertSchedule(Long concertId) {
        ConcertEntity concert = concertReaderRepository.getConcert(concertId);
        if (Objects.isNull(concert)) {
            throw ConcertException.ofErrorCode(ConcertErrorCode.E40004);
        }
        ConcertScheduleEntity schedule = concertScheduleReaderRepository.getScheduleOfSchedule(concertId);
        if (Objects.isNull(schedule)) {
            throw ConcertException.ofErrorCode(ConcertErrorCode.E40017);
        }
        return ConcertInfoDto.fromEntities(concert, schedule);
    }

    @Transactional(readOnly = true)
    public List<ConcertInfoDto> searchConcerts(SearchCriteria criteria) {
        List<ConcertEntity> concerts = concertReaderRepository.searchConcerts(criteria);
        List<Long> concertIds = concerts.stream().map(ConcertEntity::getId).toList();
        List<ConcertScheduleEntity> schedules = concertScheduleReaderRepository.getSchedules(concertIds);
        return concerts.stream()
                .flatMap(concert -> schedules.stream()
                        .filter(schedule -> schedule.getConcertId().equals(concert.getId()))
                        .map(schedule -> ConcertInfoDto.fromEntities(concert, schedule)))
                .toList();
    }
}
