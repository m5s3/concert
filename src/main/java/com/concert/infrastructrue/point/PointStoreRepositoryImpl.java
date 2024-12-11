package com.concert.infrastructrue.point;

import com.concert.domain.point.PointEntity;
import com.concert.domain.point.PointStoreRepository;
import com.concert.domain.point.dto.PointInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
@RequiredArgsConstructor
public class PointStoreRepositoryImpl implements PointStoreRepository {

    private final PointJPARepository pointJPARepository;

    @Override
    public PointInfoDto save(PointEntity point) {
        PointEntity newPoint = pointJPARepository.save(point);
        return PointInfoDto.from(newPoint);
    }

    @Override
    public PointInfoDto use(PointEntity point, BigDecimal amount) {
        point.use(amount);
        return PointInfoDto.from(point);
    }

    @Override
    public PointInfoDto charge(PointEntity point, BigDecimal amount) {
        point.charge(amount);
        return PointInfoDto.from(point);
    }
}
