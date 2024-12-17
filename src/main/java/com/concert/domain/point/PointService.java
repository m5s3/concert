package com.concert.domain.point;

import com.concert.domain.point.dto.PointInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PointService {

    private final PointReaderRepository pointReaderRepository;
    private final PointStoreRepository pointStoreRepository;
    private final PointValidator pointValidator;

    public PointInfoDto charge(PointInfoDto pointInfoDto) {
        pointValidator.validateCharge(pointInfoDto.amount());
        PointEntity point = pointReaderRepository.getPointWithLock(pointInfoDto.memberId());
        if (Objects.isNull(point)) {
            return pointStoreRepository.save(pointInfoDto.toEntity());
        }
        return pointStoreRepository.charge(point, pointInfoDto.amount());
    }

    public PointInfoDto use(PointInfoDto pointInfoDto) {
        pointValidator.validateUse(pointInfoDto.amount());
        PointEntity point = pointReaderRepository.getPointWithLock(pointInfoDto.memberId());
        if (Objects.isNull(point)) {
            throw new PointException(PointErrorCode.E20004);
        }
        return pointStoreRepository.use(point, pointInfoDto.amount());
    }

    @Transactional(readOnly = true)
    public PointInfoDto getPoint(Long memberId) {
        return PointInfoDto.from(pointReaderRepository.getPoint(memberId));
    }
}
