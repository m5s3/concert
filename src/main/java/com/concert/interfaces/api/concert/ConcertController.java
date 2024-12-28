package com.concert.interfaces.api.concert;

import com.concert.application.concert.ConcertFacade;
import com.concert.application.concert.dto.ConcertDto;
import com.concert.interfaces.api.common.ApiResponse;
import com.concert.interfaces.api.concert.dto.ConcertResponse;
import com.concert.interfaces.api.concert.dto.CreateConcertRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/concert")
public class ConcertController {

    private final ConcertFacade concertFacade;

    @PostMapping
    public ResponseEntity<ApiResponse<ConcertResponse>> createConcert(@RequestBody CreateConcertRequest request) {
        log.info("Create concert request: {}", request);
        ConcertDto concert = concertFacade.createConcert(request.toCommend());
        return ResponseEntity.ok(ApiResponse.success(ConcertResponse.from(concert)));
    }
}
