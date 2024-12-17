package com.concert.domain.point;

import com.concert.domain.point.dto.PointHistoryInfoDto;

public interface PointHistoryStoreRepository {

    PointHistoryInfoDto save(PointHistoryEntity pointHistory);
}
