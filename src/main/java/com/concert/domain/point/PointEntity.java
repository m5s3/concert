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
@Table(name = "point")
public class PointEntity extends BaseEntity {

    @Id
    @Column(name = "point_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    public PointEntity(Long id, BigDecimal amount) {
        this(id, amount, null);
    }

    @Builder
    public PointEntity(Long id, BigDecimal amount, Long memberId) {
        this.id = id;
        this.amount = amount;
        this.memberId = memberId;
    }

    public void charge(BigDecimal point) {
        if (this.amount.add(point).compareTo(PointConst.MAX) > 0) {
            throw new PointException(PointErrorCode.E20001);
        }
        this.amount = this.amount.add(point);
    }

    public void use(BigDecimal point) {
        if (this.amount.subtract(point).compareTo(BigDecimal.ZERO) < 0) {
            throw new PointException(PointErrorCode.E20000);
        }
        this.amount = this.amount.subtract(point);
    }

    public void delete() {
        this.isDeleted = true;
    }
}
