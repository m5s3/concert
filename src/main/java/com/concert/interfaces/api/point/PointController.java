package com.concert.interfaces.api.point;

import com.concert.application.Point.PointFacade;
import com.concert.interfaces.api.common.ApiResponse;
import com.concert.interfaces.api.point.dto.PointResponse;
import com.concert.interfaces.api.point.dto.UpdatePointRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<PointResponse>> charge(@RequestBody UpdatePointRequest request) {
        try {
            return ResponseEntity.ok(ApiResponse.success(PointResponse.from(pointFacade.charge(request.toCommand()))));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.success(null));
        }
    }

    @PostMapping("/use")
    public ResponseEntity<ApiResponse<PointResponse>> use(@RequestBody UpdatePointRequest request) {
        return ResponseEntity.ok(ApiResponse.success(PointResponse.from(pointFacade.use(request.toCommand()))));
    }
}
