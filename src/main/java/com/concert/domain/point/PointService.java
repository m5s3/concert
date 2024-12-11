package com.concert.domain.point;

import com.concert.domain.point.dto.PointInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PointService {

    private final PointReaderRepository pointReaderRepository;
    private final PointStoreRepository pointStoreRepository;
    private final PointValidator pointValidator;

    public PointInfoDto charge(PointInfoDto pointInfoDto) {
        pointValidator.validateCharge(pointInfoDto.amount());
        if (!pointReaderRepository.existsPoint(pointInfoDto.memberId())) {
            return pointStoreRepository.save(pointInfoDto.toEntity());
        }
        PointEntity point = pointReaderRepository.getPoint(pointInfoDto.memberId());
        return pointStoreRepository.charge(point, pointInfoDto.amount());
    }

    public PointInfoDto use(PointInfoDto pointInfoDto) {
        pointValidator.validateUse(pointInfoDto.amount());
        if (!pointReaderRepository.existsPoint(pointInfoDto.memberId())) {
            throw new PointException(PointErrorCode.E20004);
        }
        PointEntity point = pointReaderRepository.getPoint(pointInfoDto.memberId());
        return pointStoreRepository.use(point, pointInfoDto.amount());
    }
}
