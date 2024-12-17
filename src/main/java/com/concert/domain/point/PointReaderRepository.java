package com.concert.domain.point;

public interface PointReaderRepository {

    PointEntity getPoint(Long memberId);
    PointEntity getPointWithLock(Long memberId);
    boolean existsPoint(Long memberId);
}
