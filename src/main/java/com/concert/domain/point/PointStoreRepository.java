package com.concert.domain.point;

import com.concert.domain.point.dto.PointInfoDto;

import java.math.BigDecimal;

public interface PointStoreRepository {

    PointInfoDto save(PointEntity point);
    PointInfoDto use(PointEntity point, BigDecimal amount);
    PointInfoDto charge(PointEntity point, BigDecimal amount);
}
