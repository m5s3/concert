package com.concert.application.reservation;

import com.concert.application.reservation.dto.ReservationDto;
import com.concert.application.reservation.dto.ReserveCommand;
import com.concert.config.TestContainerConfig;
import com.concert.domain.concert.ConcertService;
import com.concert.domain.concert.dto.ConcertInfoDto;
import com.concert.domain.concert.dto.NewConcertDto;
import com.concert.domain.concert.dto.NewConcertScheduleDto;
import com.concert.domain.reservation.ReservationStatus;
import com.concert.domain.seat.SeatService;
import com.concert.domain.seat.dto.NewSeatDto;
import com.concert.domain.seat.dto.SeatInfoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestContainerConfig.class)
@DisplayName("예약 통합 테스트")
class ReservationFacadeTest {

    @Autowired
    private ReservationFacade reservationFacade;

    @Autowired
    private ConcertService concertService;

    @Autowired
    private SeatService seatService;

    private Long concertScheduleId;
    private Long seatId;

    @BeforeEach
    void setUp() {
        // 콘서트 및 스케줄 생성
        NewConcertDto concertDto = NewConcertDto.builder()
                .title("테스트 콘서트")
                .description("테스트 콘서트입니다")
                .build();

        NewConcertScheduleDto scheduleDto = NewConcertScheduleDto.builder()
                .startDate(LocalDateTime.now().plusDays(1))
                .endDate(LocalDateTime.now().plusDays(2))
                .reservationStartDate(LocalDateTime.now().minusMinutes(1))
                .countOfSeat(100)
                .countOfRemainSeat(100)
                .build();

        ConcertInfoDto concertInfo = concertService.createConcert(concertDto, scheduleDto);
        concertScheduleId = concertInfo.scheduleId();
        seatId = seatService.create(NewSeatDto.builder().seatNumber("A1").concertScheduleId(concertInfo.scheduleId()).build()).id();
    }
    
    @Test
    @DisplayName("좌석 예약이 성공적으로 이루어져야 한다")
    void reservationSuccess() {
        // Given
        ReserveCommand command = ReserveCommand.builder()
                .memberId(1L)
                .concertScheduleId(concertScheduleId)
                .seatId(seatId)
                .build();
        
        // When
        ReservationDto result = reservationFacade.reserve(command);
        SeatInfoDto seat = seatService.findSeat(seatId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.status()).isEqualTo(ReservationStatus.RESERVED.name());
        assertThat(seat.isReserved()).isTrue();
    }
} 