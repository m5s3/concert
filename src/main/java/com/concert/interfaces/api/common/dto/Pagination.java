package com.concert.interfaces.api.common.dto;

import jakarta.validation.constraints.NotNull;

public record Pagination(
        @NotNull(message = "페이지는 필수입니다")
        long page,
        @NotNull(message = "사이즈는 필수입니다")
        long size
) {
}
