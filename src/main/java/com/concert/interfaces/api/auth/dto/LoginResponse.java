package com.concert.interfaces.api.auth.dto;

import lombok.Builder;

@Builder
public record LoginResponse(
        boolean isSuccess
) {
}
