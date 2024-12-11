package com.concert.interfaces.api.point.dto;

import com.concert.application.Point.dto.UpdatePointCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record UpdatePointRequest(
        @JsonProperty("member_id")
        Long memberId,
        Long amount
) {
    public UpdatePointCommand toCommand() {
        return UpdatePointCommand.builder()
                .memberId(memberId)
                .amount(BigDecimal.valueOf(amount))
                .build();
    }
}
