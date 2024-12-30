package com.concert.interfaces.api.concert;

import com.concert.application.concert.ConcertFacade;
import com.concert.application.concert.dto.ConcertDto;
import com.concert.application.concert.dto.ConcertQuery;
import com.concert.interfaces.api.common.ApiResponse;
import com.concert.interfaces.api.concert.dto.ConcertResponse;
import com.concert.interfaces.api.concert.dto.ConcertSearchQuery;
import com.concert.interfaces.api.concert.dto.CreateConcertRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/concerts")
public class ConcertController {

    private final ConcertFacade concertFacade;

    @PostMapping
    public ResponseEntity<ApiResponse<ConcertResponse>> createConcert(@RequestBody CreateConcertRequest request) {
        log.info("Create concert request: {}", request);
        ConcertDto concert = concertFacade.createConcert(request.toCommend());
        return ResponseEntity.ok(ApiResponse.success(ConcertResponse.from(concert)));
    }

    @GetMapping("/{concertId}")
    public ResponseEntity<ApiResponse<ConcertResponse>> getConcert(@PathVariable Long concertId) {
        ConcertQuery query = ConcertQuery.builder().concertId(concertId).build();
        ConcertDto concert = concertFacade.getConcert(query);
        return ResponseEntity.ok(ApiResponse.success(ConcertResponse.from(concert)));
    }

    @PostMapping("/search")
    public ResponseEntity<ApiResponse<List<ConcertResponse>>> searchConcerts(@RequestBody ConcertSearchQuery query) {
        List<ConcertDto> concerts = concertFacade.searchConcerts(query.toConcertQuery());
        List<ConcertResponse> concertResponses = concerts.stream().map(ConcertResponse::from).toList();
        return ResponseEntity.ok(ApiResponse.success(concertResponses));
    }
}
