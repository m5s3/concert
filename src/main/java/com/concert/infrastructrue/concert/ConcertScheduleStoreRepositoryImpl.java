package com.concert.infrastructrue.concert;

import com.concert.domain.concert.ConcertScheduleEntity;
import com.concert.domain.concert.ConcertScheduleStoreRepository;
import com.concert.domain.concert.dto.NewConcertScheduleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ConcertScheduleStoreRepositoryImpl implements ConcertScheduleStoreRepository {
    private final ConcertScheduleJPAStoreRepository repository;

    @Override
    public ConcertScheduleEntity save(Long concertId, NewConcertScheduleDto concertScheduleDto) {
        log.info("concertScheduleDto.reservationStartDate()={}", concertScheduleDto.reservationStartDate());
        ConcertScheduleEntity concertScheduleEntity = ConcertScheduleEntity.builder().concertId(concertId)
                .startDate(concertScheduleDto.startDate())
                .endDate(concertScheduleDto.endDate())
                .reservationStartDate(concertScheduleDto.reservationStartDate())
                .countOfSeat(concertScheduleDto.countOfSeat())
                .countOfRemainSeat(concertScheduleDto.countOfRemainSeat())
                .build();
        log.info("concertScheduleEntity={}", concertScheduleEntity.getReservationStartDate());
        return repository.save(concertScheduleEntity);
    }
}
