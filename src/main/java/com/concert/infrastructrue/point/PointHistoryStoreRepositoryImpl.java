package com.concert.infrastructrue.point;

import com.concert.domain.point.PointHistoryEntity;
import com.concert.domain.point.PointHistoryStoreRepository;
import com.concert.domain.point.dto.PointHistoryInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PointHistoryStoreRepositoryImpl implements PointHistoryStoreRepository {

    private final PointHistoryJPARepository pointHistoryJPARepository;

    @Override
    public PointHistoryInfoDto save(PointHistoryEntity pointHistory) {
        return PointHistoryInfoDto.from(pointHistoryJPARepository.save(pointHistory));
    }
}
