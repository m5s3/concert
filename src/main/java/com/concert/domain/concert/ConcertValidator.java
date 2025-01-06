package com.concert.domain.concert;

import com.concert.domain.concert.dto.NewConcertDto;
import com.concert.domain.concert.dto.NewConcertScheduleDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class ConcertValidator {

    public void validate(final NewConcertDto concertDto, final NewConcertScheduleDto concertScheduleDto) {
        validateConcert(concertDto);
        validateConcertSchedule(concertScheduleDto);
    }

    private void validateConcert(final NewConcertDto concertDto) {
        if (concertDto.title().isBlank()) {
            throw ConcertException.ofErrorCode(ConcertErrorCode.E40000);
        }

        if (concertDto.title().length() < ConcertConst.MIN_TITLE_LENGTH ||
                concertDto.title().length() > ConcertConst.MAX_TITLE_LENGTH) {
            throw ConcertException.ofErrorCode(ConcertErrorCode.E40001);
        }

        if (concertDto.description().isBlank()) {
            throw ConcertException.ofErrorCode(ConcertErrorCode.E40002);
        }

        if (concertDto.description().length() < ConcertConst.MIN_DESCRIPTION_LENGTH ||
                concertDto.description().length() > ConcertConst.MAX_DESCRIPTION_LENGTH) {
            throw ConcertException.ofErrorCode(ConcertErrorCode.E40003);
        }
    }

    private void validateConcertSchedule(final NewConcertScheduleDto concertScheduleDto) {
        validateDate(concertScheduleDto);
        validateCountOfSeat(concertScheduleDto);
    }

    private void validateCountOfSeat(NewConcertScheduleDto concertScheduleDto) {
        if (concertScheduleDto.countOfSeat() < ConcertConst.MIN_COUNT_OF_SEAT ||
                concertScheduleDto.countOfRemainSeat() < ConcertConst.MIN_COUNT_OF_SEAT) {
            throw ConcertException.ofErrorCode(ConcertErrorCode.E40014);
        }
        if (concertScheduleDto.countOfSeat() > ConcertConst.MAX_COUNT_OF_SEAT ||
                concertScheduleDto.countOfRemainSeat() > ConcertConst.MAX_COUNT_OF_SEAT
        ) {
            throw ConcertException.ofErrorCode(ConcertErrorCode.E40015);
        }
        if (concertScheduleDto.countOfSeat() < concertScheduleDto.countOfRemainSeat()) {
            throw ConcertException.ofErrorCode(ConcertErrorCode.E40016);
        }
    }

    private void validateDate(NewConcertScheduleDto concertScheduleDto) {
        if (Objects.isNull(concertScheduleDto.startDate()) ||
            Objects.isNull(concertScheduleDto.endDate()) ||
            Objects.isNull(concertScheduleDto.reservationStartDate())
        ) {
            throw ConcertException.ofErrorCode(ConcertErrorCode.E40010);
        }

        if (concertScheduleDto.startDate().isAfter(concertScheduleDto.endDate())) {
            throw ConcertException.ofErrorCode(ConcertErrorCode.E40012);
        }

        if (concertScheduleDto.reservationStartDate().isAfter(concertScheduleDto.startDate())) {
            throw ConcertException.ofErrorCode(ConcertErrorCode.E40013);
        }
    }
}
