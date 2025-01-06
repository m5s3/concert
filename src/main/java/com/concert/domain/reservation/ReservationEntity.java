package com.concert.domain.reservation;

import com.concert.domain.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "reservation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @Column(nullable = false)
    private Long concertScheduleId;

    @Column(nullable = false)
    private Long seatId;

    @Column(nullable = false)
    private Long memberId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @Builder
    public ReservationEntity(Long memberId, Long concertScheduleId, Long seatId) {
        this.memberId = memberId;
        this.concertScheduleId = concertScheduleId;
        this.seatId = seatId;
        this.status = ReservationStatus.RESERVED;
    }

    public void cancel() {
        if (this.status != ReservationStatus.RESERVED) {
            throw new ReservationException(ReservationErrorCode.E60000);
        }
        this.status = ReservationStatus.CANCELED;
    }

    public void delete() {
        this.isDeleted = true;
    }

    public void updateStatus(ReservationStatus status) {
        this.status = status;
    }
} 