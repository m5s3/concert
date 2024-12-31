package com.concert.interfaces.api.concert.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ConcertResponses(
        List<ConcertResponse> concertResponses,
        int totalCount
) {
}
