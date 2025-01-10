package com.concert.application.reservation;

import com.concert.application.reservation.dto.ReserveCommand;
import com.concert.config.TestContainerConfig;
import com.concert.domain.concert.ConcertException;
import com.concert.domain.concert.ConcertService;
import com.concert.domain.concert.dto.ConcertInfoDto;
import com.concert.domain.concert.dto.NewConcertDto;
import com.concert.domain.concert.dto.NewConcertScheduleDto;
import com.concert.domain.core.lock.exception.LockAcquisitionException;
import com.concert.domain.seat.SeatException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestContainerConfig.class)
@DisplayName("예약 동시성 테스트")
class ReservationFacadeConcurrencyTest {

    @Autowired
    private ReservationFacade reservationFacade;

    @Autowired
    private ConcertService concertService;

    @Autowired
    private SeatService seatService;

    private Long concertScheduleId;
    private List<Long> seatIds;

    @BeforeEach
    void setUp() {
        // 테스트용 콘서트 및 스케줄 생성
        NewConcertDto concertDto = NewConcertDto.builder()
                .title("동시성 테스트 콘서트")
                .description("동시성 테스트를 위한 콘서트입니다")
                .build();

        int countOfSeat = 10;

        NewConcertScheduleDto scheduleDto = NewConcertScheduleDto.builder()
                .startDate(LocalDateTime.now().plusDays(1))
                .endDate(LocalDateTime.now().plusDays(2))
                .reservationStartDate(LocalDateTime.now().minusMinutes(1))
                .countOfSeat(countOfSeat) // 테스트를 위해 적은 수의 좌석
                .countOfRemainSeat(countOfSeat)
                .build();

        ConcertInfoDto concertInfo = concertService.createConcert(concertDto, scheduleDto);
        concertScheduleId = concertInfo.scheduleId();

        seatIds = new ArrayList<>();
        for (int i = 0; i < countOfSeat; i++) {
            SeatInfoDto seatInfoDto = seatService.create(NewSeatDto.builder().seatNumber("A" + i).concertScheduleId(concertInfo.scheduleId()).build());
            seatIds.add(seatInfoDto.id());
        }
    }

    @Test
    @DisplayName("동일한 좌석에 대해 동시에 여러 예약 요청이 들어올 경우 하나만 성공해야 한다")
    void concurrentReservationTest() throws InterruptedException {
        // Given
        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        AtomicInteger successCount = new AtomicInteger();
        ConcurrentLinkedQueue<Exception> exceptions = new ConcurrentLinkedQueue<>();

        // When
        for (int i = 0; i < numberOfThreads; i++) {
            long memberId = i + 1L;
            executorService.submit(() -> {
                try {
                    ReserveCommand command = ReserveCommand.builder()
                            .memberId(memberId)
                            .concertScheduleId(concertScheduleId)
                            .seatId(seatIds.getFirst())
                            .build();

                    reservationFacade.reserve(command);
                    successCount.incrementAndGet();
                } catch (Exception e) {
                    exceptions.add(e);
                } finally {
                    latch.countDown();
                }
            });
        }

        // Then
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();

        assertThat(successCount.get()).isEqualTo(1);
        assertThat(exceptions).hasSize(numberOfThreads - 1);
        assertThat(exceptions)
                .allSatisfy(exception ->
                        assertThat(exception)
                                .isInstanceOfAny(
                                        SeatException.class,
                                        LockAcquisitionException.class
                                )
                );
    }

    @Test
    @DisplayName("서로 다른 좌석에 대한 동시 예약은 모두 성공해야 한다")
    void concurrentDifferentSeatsReservationTest() throws InterruptedException {
        // Given
        int numberOfThreads = 5; // 좌석 수보다 적은 수의 스레드
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        AtomicInteger successCount = new AtomicInteger();
        ConcurrentLinkedQueue<Exception> exceptions = new ConcurrentLinkedQueue<>();

        // When
        for (int i = 0; i < numberOfThreads; i++) {
            long memberId = i + 1L;
            long currentSeatId = seatIds.get(i); // 각각 다른 좌석
            executorService.submit(() -> {
                try {
                    ReserveCommand command = ReserveCommand.builder()
                            .memberId(memberId)
                            .concertScheduleId(concertScheduleId)
                            .seatId(currentSeatId)
                            .build();

                    reservationFacade.reserve(command);
                    successCount.incrementAndGet();
                } catch (Exception e) {
                    exceptions.add(e);
                } finally {
                    latch.countDown();
                }
            });
        }

        // Then
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();

        assertThat(successCount.get()).isEqualTo(numberOfThreads);
        assertThat(exceptions).isEmpty();
    }

    @Test
    @DisplayName("남은 좌석 수가 0이 되면 더 이상 예약할 수 없다")
    void noMoreSeatsAvailableTest() throws InterruptedException {
        // Given
        int numberOfThreads = 15; // 총 좌석 수보다 많은 수의 스레드
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        AtomicInteger successCount = new AtomicInteger();
        ConcurrentLinkedQueue<Exception> exceptions = new ConcurrentLinkedQueue<>();

        // When
        for (int i = 0; i < numberOfThreads; i++) {
            long memberId = i + 1L;
            long currentSeatId = seatIds.get(i % 10); // 10개의 좌석을 순환하면서 시도
            executorService.submit(() -> {
                try {
                    ReserveCommand command = ReserveCommand.builder()
                            .memberId(memberId)
                            .concertScheduleId(concertScheduleId)
                            .seatId(currentSeatId)
                            .build();

                    reservationFacade.reserve(command);
                    successCount.incrementAndGet();
                } catch (Exception e) {
                    exceptions.add(e);
                } finally {
                    latch.countDown();
                }
            });
        }

        // Then
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();

        assertThat(successCount.get()).isEqualTo(10); // 총 좌석 수만큼만 성공
        assertThat(exceptions).hasSize(5); // 나머지는 실패
    }
}