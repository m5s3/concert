package com.concert.application.member.dto;

import lombok.Builder;

@Builder
public record MemberQuery(
        Long id,
        String name
) {
}
