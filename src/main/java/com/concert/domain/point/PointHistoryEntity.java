package com.concert.domain.point;

import com.concert.domain.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "point_history")
public class PointHistoryEntity extends BaseEntity {

    @Id
    @Column(name = "point_history_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long pointId;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PointHistoryType type;

    private BigDecimal amount;

    @Builder
    public PointHistoryEntity(Long id, Long pointId, Long memberId, PointHistoryType type, BigDecimal amount) {
        this.id = id;
        this.pointId = pointId;
        this.memberId = memberId;
        this.type = type;
        this.amount = amount;
    }
}
