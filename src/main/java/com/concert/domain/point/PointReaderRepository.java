package com.concert.domain.point;

public interface PointReaderRepository {

    PointEntity getPoint(Long memberId);
    boolean existsPoint(Long memberId);
}
