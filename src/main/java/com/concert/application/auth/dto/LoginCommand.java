package com.concert.application.auth.dto;

import lombok.Builder;

@Builder
public record LoginCommand(
        String name
) {
}
