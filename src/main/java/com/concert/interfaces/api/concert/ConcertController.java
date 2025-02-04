package com.concert.interfaces.api.concert;

import com.concert.application.concert.ConcertFacade;
import com.concert.application.concert.dto.ConcertDto;
import com.concert.application.concert.dto.ConcertQuery;
import com.concert.application.seat.dto.SeatQuery;
import com.concert.application.seat.SeatFacade;
import com.concert.interfaces.api.concert.dto.*;
import com.concert.support.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public ApiResponse<ConcertResponse> createConcert(@RequestBody CreateConcertRequest request) {
        log.info("Create concert request: {}", request);
        ConcertDto concert = concertFacade.createConcert(request.toCommend());
        return ApiResponse.success(ConcertResponse.from(concert));
    }

    @GetMapping("/{concertId}")
    public ApiResponse<ConcertResponse> getConcert(@PathVariable Long concertId) {
        ConcertQuery query = ConcertQuery.builder().concertId(concertId).build();
        ConcertDto concert = concertFacade.getConcert(query);
        return ApiResponse.success(ConcertResponse.from(concert));
    }

    @PostMapping("/search")
    public ApiResponse<ConcertResponses> searchConcerts(@RequestBody SearchConcertRequest query) {
        List<ConcertDto> concerts = concertFacade.searchConcerts(query.toConcertQuery());
        List<ConcertResponse> concertResponses = concerts.stream().map(ConcertResponse::from).toList();
        ConcertResponses response = ConcertResponses.builder().concertResponses(concertResponses).totalCount(concertResponses.size()).build();
        return ApiResponse.success(response);
    }

    @GetMapping("/{concertId}/seats")
    public ApiResponse<SeatResponses> getSeats(@RequestParam Long concertScheduleId,
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
        return ApiResponse.success(response);
    }
}
