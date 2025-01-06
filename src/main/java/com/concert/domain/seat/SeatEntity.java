package com.concert.domain.seat;

import com.concert.domain.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "seat")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SeatEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long id;

    @Column(nullable = false)
    private Long concertScheduleId;

    @Column(nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    private Boolean isReserved = false;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @Builder
    public SeatEntity(Long concertScheduleId, String seatNumber) {
        this.concertScheduleId = concertScheduleId;
        this.seatNumber = seatNumber;
    }

    public void reserve() {
        if (this.isReserved) {
            throw new SeatException(SeatErrorCode.E50000);
        }
        this.isReserved = true;
    }

    public void cancel() {
        this.isReserved = false;
    }

    public void delete() {
        this.isDeleted = true;
    }
}
