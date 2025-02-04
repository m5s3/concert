package com.concert.interfaces.api.point;

import com.concert.application.Point.PointFacade;
import com.concert.interfaces.api.point.dto.PointResponse;
import com.concert.interfaces.api.point.dto.UpdatePointRequest;
import com.concert.support.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/points")
public class PointController {

    private final PointFacade pointFacade;

    @PostMapping("/charge")
    public ApiResponse<PointResponse> charge(@RequestBody UpdatePointRequest request) {
        return ApiResponse.success(PointResponse.from(pointFacade.charge(request.toCommand())));
    }

    @PostMapping("/use")
    public ApiResponse<PointResponse> use(@RequestBody UpdatePointRequest request) {
        return ApiResponse.success(PointResponse.from(pointFacade.use(request.toCommand())));
    }
}
