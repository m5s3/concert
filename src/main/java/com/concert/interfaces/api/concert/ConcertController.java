package com.concert.interfaces.api.concert;

import com.concert.application.concert.ConcertFacade;
import com.concert.application.concert.dto.ConcertDto;
import com.concert.application.concert.dto.ConcertQuery;
import com.concert.application.seat.dto.SeatQuery;
import com.concert.application.seat.SeatFacade;
import com.concert.interfaces.api.common.ApiResponse;
import com.concert.interfaces.api.concert.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/concerts")
public class ConcertController {

    private final ConcertFacade concertFacade;
    private final SeatFacade seatFacade;

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
    public ResponseEntity<ApiResponse<ConcertResponses>> searchConcerts(@RequestBody SearchConcertRequest query) {
        List<ConcertDto> concerts = concertFacade.searchConcerts(query.toConcertQuery());
        List<ConcertResponse> concertResponses = concerts.stream().map(ConcertResponse::from).toList();
        ConcertResponses response = ConcertResponses.builder().concertResponses(concertResponses).totalCount(concertResponses.size()).build();
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/{concertId}/seats")
    public ResponseEntity<ApiResponse<SeatResponses>> getSeats(@RequestParam Long concertScheduleId,
                                                               @RequestParam int size,
                                                               @RequestParam int page
    ) {
        SeatQuery query = SeatQuery.builder()
                .scheduleId(concertScheduleId)
                .page(page)
                .size(size)
                .build();
        List<SeatResponse> seats = seatFacade.getSeats(query).stream().map(SeatResponse::from).toList();
        SeatResponses response = SeatResponses.builder().seats(seats).totalCount(seats.size()).build();
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
