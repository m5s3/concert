package com.concert.domain.concert;

import com.concert.domain.concert.dto.NewConcertDto;
import com.concert.domain.concert.dto.NewConcertScheduleDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("콘서트 검증 테스트")
class ConcertValidatorTest {

    private ConcertValidator concertValidator;
    private NewConcertDto validConcertDto;
    private NewConcertScheduleDto validScheduleDto;

    @BeforeEach
    void setUp() {
        concertValidator = new ConcertValidator();

        // Valid Concert DTO
        validConcertDto = NewConcertDto.builder()
                .title("Valid Concert")
                .description("Valid Description")
                .build();

        // Valid Schedule DTO
        validScheduleDto = NewConcertScheduleDto.builder()
                .startDate(LocalDateTime.now().plusDays(1))
                .endDate(LocalDateTime.now().plusDays(2))
                .reservationStartDate(LocalDateTime.now().plusMinutes(1))
                .countOfSeat(100)
                .countOfRemainSeat(100)
                .build();
    }

    @Test
    void validate_shouldThrowException_whenTitleIsBlank() {
        NewConcertDto invalidConcertDto = NewConcertDto.builder()
                .title("")
                .description("Valid Description")
                .build();

        assertThatThrownBy(() -> concertValidator.validate(invalidConcertDto, validScheduleDto))
                .isInstanceOf(ConcertException.class)
                .hasMessage(ConcertErrorCode.E40000.getMessage());
    }

    @Test
    void validate_shouldThrowException_whenTitleLengthIsInvalid() {
        NewConcertDto invalidConcertDto = NewConcertDto.builder()
                .title("A")
                .description("Valid Description")
                .build();

        assertThatThrownBy(() -> concertValidator.validate(invalidConcertDto, validScheduleDto))
                .isInstanceOf(ConcertException.class)
                .hasMessage(ConcertErrorCode.E40001.getMessage());
    }

    @Test
    void validate_shouldThrowException_whenDescriptionIsBlank() {
        NewConcertDto invalidConcertDto = NewConcertDto.builder()
                .title("Valid Title")
                .description("")
                .build();

        assertThatThrownBy(() -> concertValidator.validate(invalidConcertDto, validScheduleDto))
                .isInstanceOf(ConcertException.class)
                .hasMessage(ConcertErrorCode.E40002.getMessage());
    }

    @Test
    void validate_shouldThrowException_whenSeatCountIsTooLow() {
        NewConcertScheduleDto invalidScheduleDto = NewConcertScheduleDto.builder()
                .startDate(validScheduleDto.startDate())
                .endDate(validScheduleDto.endDate())
                .reservationStartDate(validScheduleDto.reservationStartDate())
                .countOfSeat(-1)
                .countOfRemainSeat(-1)
                .build();

        assertThatThrownBy(() -> concertValidator.validate(validConcertDto, invalidScheduleDto))
                .isInstanceOf(ConcertException.class)
                .hasMessage(ConcertErrorCode.E40014.getMessage());
    }

    @Test
    void validate_shouldThrowException_whenReservationDateIsInPast() {
        NewConcertScheduleDto invalidScheduleDto = NewConcertScheduleDto.builder()
                .startDate(validScheduleDto.startDate())
                .endDate(validScheduleDto.endDate())
                .reservationStartDate(LocalDateTime.now().minusDays(1))
                .countOfSeat(validScheduleDto.countOfSeat())
                .countOfRemainSeat(validScheduleDto.countOfRemainSeat())
                .build();

        assertThatThrownBy(() -> concertValidator.validate(validConcertDto, invalidScheduleDto))
                .isInstanceOf(ConcertException.class)
                .hasMessage(ConcertErrorCode.E40011.getMessage());
    }

    @Test
    void validate_shouldNotThrowException_whenAllDataIsValid() {
        assertThatNoException().isThrownBy(() -> concertValidator.validate(validConcertDto, validScheduleDto));
    }
}