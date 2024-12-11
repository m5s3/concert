package com.concert.application.Point;

import com.concert.application.Point.dto.PointDto;
import com.concert.application.Point.dto.UpdatePointCommand;
import com.concert.domain.point.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PointFacade {

    private final PointService pointService;

    public PointDto use(UpdatePointCommand command) {
        return PointDto.from(pointService.use(command.toPointInfoDto()));
    }

    public PointDto charge(UpdatePointCommand command) {
        return PointDto.from(pointService.charge(command.toPointInfoDto()));
    }
}
