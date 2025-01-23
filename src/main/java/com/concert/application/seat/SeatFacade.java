package com.concert.application.seat;

import com.concert.application.seat.dto.SeatQuery;
import com.concert.application.seat.dto.SeatDto;
import com.concert.domain.seat.SeatService;
import com.concert.domain.seat.dto.SeatInfoDto;
import com.concert.domain.seat.dto.SeatSearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SeatFacade {

    private final SeatService seatService;

    public List<SeatDto> getSeats(SeatQuery query) {
        SeatSearchCriteria criteria = SeatSearchCriteria.builder()
                .scheduleId(query.scheduleId())
                .page(query.page())
                .size(query.size())
                .build();
        List<SeatInfoDto> seats = seatService.getSeats(criteria);
        return seats.stream().map(SeatDto::from).toList();
    }
}
