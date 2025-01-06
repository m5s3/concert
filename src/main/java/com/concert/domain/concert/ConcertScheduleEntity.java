package com.concert.domain.concert;

import com.concert.domain.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "concert_schedule")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConcertScheduleEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "concert_schedule_id")
    private Long id;

    @Column(nullable = false)
    private Long concertId;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private LocalDateTime reservationStartDate;

    @Column(nullable = false)
    private Integer countOfSeat;

    @Column(nullable = false)
    private Integer countOfRemainSeat;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @Builder
    public ConcertScheduleEntity(Long id, Long concertId, LocalDateTime startDate, LocalDateTime endDate,
                                 LocalDateTime reservationStartDate, Integer countOfSeat, Integer countOfRemainSeat) {
        this.id = id;
        this.concertId = concertId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reservationStartDate = reservationStartDate;
        this.countOfSeat = countOfSeat;
        this.countOfRemainSeat = countOfRemainSeat;
    }

    public void delete() {
        this.isDeleted = true;
    }

    public void decreaseRemainSeat() {
        if (this.countOfRemainSeat < 0) {
            throw new ConcertException(ConcertErrorCode.E40020);
        }
        this.countOfRemainSeat--;
    }
}
