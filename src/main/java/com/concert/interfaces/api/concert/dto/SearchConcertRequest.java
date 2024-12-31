package com.concert.interfaces.api.concert.dto;

import com.concert.application.concert.dto.ConcertQuery;
import com.concert.interfaces.api.common.dto.Pagination;

public record SearchConcertRequest(
        Pagination pagination,
        ConcertSearchQuery query
) {
    public ConcertQuery toConcertQuery() {
        return ConcertQuery.builder()
                .concertId(query().concertId())
                .title(query().title())
                .description(query().description())
                .startDate(query().startDate())
                .endDate(query().endDate())
                .reservationStartDate(query().reservationStartDate())
                .page(pagination().page())
                .size(pagination().size())
                .build();
    }
}
