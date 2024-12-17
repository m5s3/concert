package com.concert.application.Point;

import com.concert.application.Point.dto.PointDto;
import com.concert.application.Point.dto.UpdatePointCommand;
import com.concert.domain.point.PointHistoryService;
import com.concert.domain.point.PointHistoryType;
import com.concert.domain.point.PointService;
import com.concert.domain.point.dto.PointInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PointFacade {

    private final PointService pointService;
    private final PointHistoryService pointHistoryService;

    @Transactional
    public PointDto use(UpdatePointCommand command) {
        PointInfoDto point = pointService.use(command.toPointInfoDto());
        pointHistoryService.create(command.toNewPointHistoryDto(point.id(), PointHistoryType.USE));
        return PointDto.from(point);
    }

    @Transactional
    public PointDto charge(UpdatePointCommand command) {
        PointInfoDto point = pointService.charge(command.toPointInfoDto());
        pointHistoryService.create(command.toNewPointHistoryDto(point.id(), PointHistoryType.CHARGE));
        return PointDto.from(point);
    }

    public PointDto getPoint(Long memberId) {
        return PointDto.from(pointService.getPoint(memberId));
    }
}
