package com.concert.domain.security.dto;

import lombok.Builder;

@Builder
public record NewTokenDto(
        Long memberId
) {

    public static NewTokenDto ofMemberId(Long memberId) {
        return NewTokenDto.builder()
                .memberId(memberId)
                .build();
    }
}
