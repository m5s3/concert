package com.concert.interfaces.api.concert.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record SeatResponses(
        List<SeatResponse> seats,
        int totalCount
) {
}
