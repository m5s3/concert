package com.concert.domain.point;

import com.concert.domain.point.dto.PointHistoryInfoDto;
import java.util.List;

public interface PointHistoryReaderRepository {

    List<PointHistoryInfoDto> getHistories(Long memberId, int offset, int limit);
    long countHistories(Long memberId);
}
